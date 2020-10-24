package ddd.domain.common.vo;

import ddd.application.common.exception.DDDItemCheckException;
import ddd.interfaces.ResponseStatus;

/**
 * 削除フラグ定義
 */
public enum DeleteFlagConst {

    /**
     * 未削除
     */
    NO_DELETE(0),
    /**
     * 削除
     */
    DELETE(1),
    ;

    private int value;

    private static final String ERROR_DOMAIN_COMMON_VO_DELETEFLAGCONST_01 = "error.domain.common.vo.deleteflagconst.01";

    private DeleteFlagConst(int inputValue) {
        this.value = inputValue;
    }

    /**
     * @param value 設定値
     * @return 削除フラグオブジェクト
     * @throws DDDItemCheckException 項目チェック例外
     */
    public static DeleteFlagConst valueOf(int value) throws DDDItemCheckException {
        for (DeleteFlagConst status : DeleteFlagConst.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new DDDItemCheckException(ERROR_DOMAIN_COMMON_VO_DELETEFLAGCONST_01, ResponseStatus.BAD_REQUEST.value());
    }

    /**
     * @return ステータス
     */
    public int getValue() {
        return this.value;
    }
}
