package ddd.domain.common.vo;

import ddd.application.common.exception.DDDItemCheckException;
import ddd.interfaces.ResponseStatus;

/**
 * ステータス定義
 */
public enum StatusConst {

    /**
     * オープン
     */
    OPEN(0, "open"),
    /**
     * クローズ
     */
    CLOSE(1, "close"),;

    private int id;
    private String name;
    private static final String ERROR_DOMAIN_COMMON_VO_STATUSCONST_01 = "error.domain.common.vo.statusconst.01";
    private static final String ERROR_DOMAIN_COMMON_VO_STATUSCONST_02 = "error.domain.common.vo.statusconst.02";

    private StatusConst(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return ステータスID
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return ステータス名
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param id ステータスID
     * @return ステータス定義
     * @throws DDDItemCheckException 項目チェック例外
     */
    public static StatusConst valueOf(int id) throws DDDItemCheckException {
        for (StatusConst statusConst : StatusConst.values()) {
            if (statusConst.getId() == id) {
                return statusConst;
            }
        }
        throw new DDDItemCheckException(ERROR_DOMAIN_COMMON_VO_STATUSCONST_01, ResponseStatus.BAD_REQUEST.value());
    }

    /**
     * @param status ステータス名
     * @return ステータス定義
     * @throws DDDItemCheckException 項目チェック例外
     */
    public static StatusConst nameOf(String status) throws DDDItemCheckException {
        for (StatusConst statusConst : StatusConst.values()) {
            if (statusConst.getName().equals(status)) {
                return statusConst;
            }
        }
        throw new DDDItemCheckException(ERROR_DOMAIN_COMMON_VO_STATUSCONST_02, ResponseStatus.BAD_REQUEST.value());
    }

}