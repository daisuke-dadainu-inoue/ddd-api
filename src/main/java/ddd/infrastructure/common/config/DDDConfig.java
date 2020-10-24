package ddd.infrastructure.common.config;

import java.io.InputStreamReader;
import java.util.Properties;

import org.glassfish.grizzly.utils.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 設定
 */
public class DDDConfig {

    /** ログ出力オブジェクト */
    private static final Logger LOGGER = LoggerFactory.getLogger(DDDConfig.class);

    /** DDDプロパティ名 */
    private static final String PROPERTY_DDD_NAME = "ddd.properties";

    /** エラー文言プロパティ名 */
    private static final String PROPERTY_ERROR_NAME = "error.properties";

    /** DDDプロパティ */
    private static Properties PROPERTIES_DDD;

    /** エラー文言プロパティ */
    private static Properties PROPERTIES_ERROR;

    /** プロパティ読込 */
    static {
        try (InputStreamReader userPropertiesStream = new InputStreamReader(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_DDD_NAME),
                Charsets.UTF8_CHARSET);
                InputStreamReader errorPropertiesStream = new InputStreamReader(
                        Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_ERROR_NAME),
                        Charsets.UTF8_CHARSET);) {
            // DDDプロパティ読み込み
            PROPERTIES_DDD = new Properties();
            PROPERTIES_DDD.load(userPropertiesStream);
            // エラー文言プロパティ読み込み
            PROPERTIES_ERROR = new Properties();
            PROPERTIES_ERROR.load(errorPropertiesStream);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /** デフォルトコンストラクタ */
    private DDDConfig() {
    }

    /**
     * DDDプロパティ取得
     *
     * @return プロパティ
     */
    public static Properties getDDDProperties() {
        return PROPERTIES_DDD;
    }

    /**
     * DDDプロパティ値取得
     *
     * @param key プロパティキー
     * @return プロパティバリュー
     */
    public static String getDDDPropertyValue(String key) {
        return PROPERTIES_DDD.getProperty(key);
    }

    /**
     * エラー文言取得
     *
     * @param key プロパティキー
     * @return プロパティバリュー
     */
    public static String getErrorPropertyValue(String key) {
        return PROPERTIES_ERROR.getProperty(key);
    }
}
