package ddd.interfaces;

/**
 * レスポンスステータス定義
 */
public enum ResponseStatus {
    /**
     * リクエストは成功しレスポンスとともに要求に応じたリソースが返される。
     */
    OK(200),
    /**
     * リクエストは完了し新たにリソースが作成された。 Locationヘッダには新たに作成されたリソースのURIが含まれる。 POSTで利用される。
     */
    CREATED(201),
    /**
     * 内容なし。 リクエストを受理したが、返すべきレスポンスエンティティが存在しない場合に返される。 PUT、POST、DELETE等で利用される。
     */
    NO_CONTENT(204),
    /**
     * 他を参照せよ。 リクエストに対するレスポンスが他のURIとして存在するときに返される。 Locationヘッダに移動先のURIが示されている。
     */
    SEE_OTHER(303),
    /**
     * リクエストが不正である。 定義されていないメソッドを使うなど、クライアントのリクエストがおかしい場合に返される。
     */
    BAD_REQUEST(400),
    /**
     * 認証が必要である。 Basic認証やDigest認証などを行うときに使用される。
     */
    UNAUTHORIZED(401),
    /**
     * 未検出。リソースが見つからなかった。
     */
    NOT_FOUND(404),
    /**
     * 許可されていないメソッド。許可されていないメソッドを使用しようとした。
     */
    METHOD_NOT_ALLOWED(405),
    /**
     * 競合。リクエストは現在のリソースと競合するので完了出来ない。
     */
    CONFLICT(409),
    /**
     * サーバ内部エラー。サーバ内部にエラーが発生した場合に返される。
     */
    INTERNAL_SERVER_ERROR(500),
    /**
     * サービス利用不可。サービスが一時的に過負荷やメンテナンスで使用不可能である。
     */
    SERVICE_UNAVAILABLE(503),
    ;

    private int mStatus;

    private ResponseStatus(int status) {
        mStatus = status;
    }

    /**
     * @return コード値
     */
    public int value() {
        return mStatus;
    }
}
