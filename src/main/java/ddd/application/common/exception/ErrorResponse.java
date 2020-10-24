package ddd.application.common.exception;

import lombok.Value;

/**
 * エラーレスポンス
 */
@Value
public class ErrorResponse {
    private String errorId;
    private int status;
    private String errorMessage;
}
