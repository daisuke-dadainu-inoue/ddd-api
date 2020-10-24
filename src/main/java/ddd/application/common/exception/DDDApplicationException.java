package ddd.application.common.exception;

import ddd.infrastructure.common.config.DDDConfig;
import ddd.interfaces.common.JsonConverter;

/**
 * アプリケーションのビジネスロジックでエラーが発生したことを示す例外として作成します。
 * Webアプリケーションの場合、該当の処理をやり直すことができるエラーがこれに当たります。
 * 例えば、画面から入力したデータがシステムの期待するフォーマットにあっていない場合などに、
 * ApplicationIllegalFormatExceptioなどのように具象クラスを作成したりします。
 */
public class DDDApplicationException extends Exception {

    private static final long serialVersionUID = 1L;

    private String errorId;

    private int status;

    /**
     * @param errorId エラーID
     * @param status  ステータス
     */
    public DDDApplicationException(String errorId, int status) {
        super(DDDConfig.getErrorPropertyValue(errorId));
        this.errorId = errorId;
        this.status = status;
    }

    /**
     * @param errorId エラーID
     * @param status  ステータス
     * @param cause   スローされた例外
     */
    public DDDApplicationException(String errorId, int status, Throwable cause) {
        super(DDDConfig.getErrorPropertyValue(errorId), cause);
        this.errorId = errorId;
        this.status = status;
    }

    /**
     * @return エラーレスポンス
     */
    public String responseMessage() {
        ErrorResponse errorResponse = new ErrorResponse(errorId, status, DDDConfig.getErrorPropertyValue(errorId));
        return JsonConverter.toJson(errorResponse);
    }

    /**
     * @return エラーID
     */
    public String getErrorId() {
        return errorId;
    }

    /**
     * @return ステータス
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return エラーメッセージ
     */
    public String getErrorMessage() {
        return DDDConfig.getErrorPropertyValue(errorId);
    }
}
