package ddd.infrastructure.common.config;

/**
 * BasilDB定義
 */
public enum DDDDB {

    /**
     * 参照DB
     */
    DDD_REF("dddRef"),
    /**
     * 更新DB
     */
    DDD_UPD("dddUpd"),
    ;

    /** 定義値 */
    private String mValue;

    /**
     * デフォルトコンストラクタ
     *
     * @param value 定義値
     */
    private DDDDB(String value) {
        mValue = value;
    }

    /**
     * 定義値取得
     *
     * @return 定義値
     */
    public String value() {
        return mValue;
    }
}
