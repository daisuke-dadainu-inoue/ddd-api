package ddd.infrastructure.common.config;

/**
 * 設定ファイル値定義
 */
public enum DDDConfigConst {

    /**
     * 環境設定
     */
    DDD_CONFIG_ENV("ddd.config.env"),
    /**
     * DB接続ドライバ
     */
    DB_DRIVER("db.driver"),
    /**
     * 参照DB接続URL
     */
    DB_REF_URL("db.ref.url"),
    /**
     * 参照DB接続ユーザー
     */
    DB_REF_USERNAME("db.ref.username"),
    /**
     * 参照DB接続パスワード
     */
    DB_REF_PASSWORD("db.ref.password"),
    /**
     * 更新DB接続URL
     */
    DB_UPD_URL("db.upd.url"),
    /**
     * 更新DB接続ユーザー
     */
    DB_UPD_USERNAME("db.upd.username"),
    /**
     * 更新DB接続パスワード
     */
    DB_PASSWORD("db.upd.password"),
    /**
     * 制限時間
     */
    LIMIT_TIME("ddd.config.limit.time"),
    ;

    /** 定義値 */
    private String value;

    /**
     * デフォルトコンストラクタ
     *
     * @param value 定義値
     */
    private DDDConfigConst(String value) {
        this.value = value;
    }

    /**
     * 定義値取得
     *
     * @return 定義値
     */
    public String value() {
        return this.value;
    }
}
