#!/usr/bin/env bash
set -u

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
SRC_MAIN="$ROOT_DIR/src/main/java"

# 1. 루트 패키지(예: src/main/java/*) 목록 수집. 없으면 종료.
ROOT_PKGS=()
while IFS= read -r -d '' dir; do
  ROOT_PKGS+=("$dir")
done < <(find "$SRC_MAIN" -mindepth 1 -maxdepth 1 -type d -print0 2>/dev/null)

[ ${#ROOT_PKGS[@]} -gt 0 ] || { echo "No root packages found in $SRC_MAIN"; exit 1; }

# 2. 파일 생성 헬퍼
create_if_absent() {
  local path="$1"
  if [ -e "$path" ]; then
    echo "skip: $path"
    return 0
  fi
  mkdir -p "$(dirname "$path")"
  cat > "$path"
  echo "create: $path"
}

create_and_overwrite() {
  local path="$1"
  mkdir -p "$(dirname "$path")"
  cat > "$path"
  echo "overwrite: $path"
}

# 3. 각 패키지에 대해 스캐폴드 수행
for PKG_PATH in "${ROOT_PKGS[@]}"; do
  [ -d "$PKG_PATH" ] || continue
  PKG_NAME="$(basename "$PKG_PATH")"

  p_root="$PKG_PATH"
  p_model_in="$PKG_PATH/model/in"
  p_model_out="$PKG_PATH/model/out"
  p_view="$PKG_PATH/view"
  p_service="$PKG_PATH/service"
  p_util="$PKG_PATH/util"

  # ---------------- root ----------------
  create_and_overwrite "$p_root/Application.java" <<EOF
package $PKG_NAME;

public class Application {
    public static void main(String[] args) {
        try {
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
EOF

  create_if_absent "$p_root/Constant.java" <<EOF
package $PKG_NAME;

import $PKG_NAME.util.ExceptionUtil;

public final class Constant {
    public static final String ERROR_PREFIX = "[ERROR]";
    static {
        ExceptionUtil.setErrorPrefix(ERROR_PREFIX);
    }

    private Constant() {
    }
}
EOF

  create_if_absent "$p_root/ErrorCode.java" <<EOF
package $PKG_NAME;

import $PKG_NAME.util.ExceptionUtil;

public enum ErrorCode {
    // SCOPE_(REASON)
    INPUT_INVALID("유효하지 않은 입력 값입니다.", IllegalArgumentException.class),
    ;

    private final String message;
    private final Class<? extends RuntimeException> exceptionClass;

    ErrorCode(String message, Class<? extends RuntimeException> exceptionClass) {
        this.message = message;
        this.exceptionClass = exceptionClass;
    }

    public RuntimeException exception() {
        return ExceptionUtil.instantiate(exceptionClass, message);
    }

    public RuntimeException exception(String detail) {
        return ExceptionUtil.instantiate(exceptionClass, message + ": " + detail);
    }
}
EOF

  # ---------------- util ----------------
  create_if_absent "$p_util/ExceptionUtil.java" <<EOF
package $PKG_NAME.util;

public final class ExceptionUtil {
    private static String ERROR_PREFIX = "[ERROR]";

    private ExceptionUtil() {
    }

    public static void setErrorPrefix(String prefix) {
        ERROR_PREFIX = prefix;
    }

    public static IllegalArgumentException exception(String message) {
        return new IllegalArgumentException(ERROR_PREFIX + " " + message);
    }

    public static UnsupportedOperationException unsupported(String scope, String message) {
        return new UnsupportedOperationException(ERROR_PREFIX + " " + scope + ": " + message);
    }

    public static <T> T tryUntilValid(java.util.function.Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static RuntimeException instantiate(
            Class<? extends RuntimeException> exceptionClass,
            String message
    ) {
        try {
            return exceptionClass
                    .getConstructor(String.class)
                    .newInstance(ERROR_PREFIX + " " + message);
        } catch (Exception e) {
            throw new RuntimeException("ExceptionHandler::instantiate", e);
        }
    }
}
EOF

  # ---------------- view ----------------
  create_if_absent "$p_view/Message.java" <<EOF
package $PKG_NAME.view;

import java.util.IllegalFormatException;

import $PKG_NAME.util.ExceptionUtil;

enum Message {
    // (IN/OUT)_SCOPE_(FORMAT)
    IN_("input foo: "),
    ;

    private final String text;

    Message(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    String format(Object... args) {
        try {
            return String.format(text, args);
        } catch (IllegalFormatException e) {
            throw ExceptionUtil.unsupported("Message::format", this.name());
        }
    }
}
EOF

  create_if_absent "$p_view/InputView.java" <<EOF
package $PKG_NAME.view;

public class InputView {

}
EOF

  create_if_absent "$p_view/OutputView.java" <<EOF
package $PKG_NAME.view;

public class OutputView {

}
EOF

  # ---------------- service ----------------
  create_if_absent "$p_service/WhatEverService.java" <<EOF
package $PKG_NAME.service;

public class WhatEverService {
    private WhatEverService(){}

    // TODO write static methods 

}
EOF

done

echo "Scaffold completed."