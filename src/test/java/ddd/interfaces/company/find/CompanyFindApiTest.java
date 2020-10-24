package ddd.interfaces.company.find;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ddd.application.company.find.CompanyFindInteractorMock;
import ddd.application.company.find.usecase.CompanyFindUseCaseIF;
import ddd.interfaces.company.find.controller.CompanyFindApi;

/**
 * ユーザー情報取得APIテスト
 */
public class CompanyFindApiTest extends JerseyTest {

    private static final String REQUESTPATH = "/company";

    @BeforeAll
    static void initAll() {
        // unimplemented
    }

    @BeforeEach
    void init() throws Exception {
        super.setUp();
    }

    @AfterEach
    void tearDownOwn() throws Exception {
        super.tearDown();
    }

    @AfterAll
    static void tearDownAll() {
        // unimplemented
    }

    /**
     * @see org.glassfish.jersey.test.JerseyTest#configure()
     */
    @Override
    protected Application configure() {
        return new ResourceConfig().register(CompanyFindApi.class).register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(CompanyFindInteractorMock.class).to(CompanyFindUseCaseIF.class);
            }
        });
    }

    /**
     * 正常系テスト001
     *
     * @param inputValue 入力値
     *
     */
    @DisplayName("SuccessTest")
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "1",
    })
    void success_001(String inputValue) {
        try {
            String result = target(REQUESTPATH)
                    .queryParam("embed", inputValue)
                    .queryParam("status", "")
                    .queryParam("sort", "")
                    .request()
                    .get(String.class);
            JsonObject responseJsonObject = new Gson().fromJson(result, JsonObject.class);
            JsonObject jsonObject = responseJsonObject.get("items").getAsJsonArray().get(0).getAsJsonObject();
            assertAll("Company",
                    () -> assertEquals("1", jsonObject.get("company_id").getAsString(), "事業ID"),
                    () -> assertEquals("hoge", jsonObject.get("company_name").getAsString(), "事業名"),
                    () -> assertEquals("hogekana", jsonObject.get("company_name_kana").getAsString(), "事業名かな"),
                    () -> assertEquals("1234567", jsonObject.get("postal_code").getAsString(), "郵便番号"),
                    () -> assertEquals("13", jsonObject.get("address_code").getAsString(), "住所コード"),
                    () -> assertEquals("hogeadd", jsonObject.get("address_name").getAsString(), "住所名"),
                    () -> assertEquals("0312345678", jsonObject.get("phone_number").getAsString(), "電話番号"),
                    () -> assertEquals("open", jsonObject.get("status").getAsString(), "ステータス"));

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * 異常系テスト001
     *
     * @param inputValue 入力値
     *
     */
    @DisplayName("ErrorTest_項目チェック例外")
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "2",
    })
    void error_001(String inputValue) {
        try (Response result = target(REQUESTPATH)
                .queryParam("embed", inputValue)
                .queryParam("status", "")
                .queryParam("sort", "")
                .request()
                .get();) {
            JsonObject errorJsonObject = new Gson().fromJson(result.readEntity(String.class), JsonObject.class);
            assertAll("",
                    () -> assertEquals("error.test.usecase.mock.001", errorJsonObject.get("error_id").getAsString(), "エラーID"),
                    () -> assertEquals("400", errorJsonObject.get("status").getAsString(), "エラーステータス"),
                    () -> assertEquals("項目チェック例外が発生しました", errorJsonObject.get("error_message").getAsString(), "エラーメッセージ"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * 異常系テスト002
     *
     * @param inputValue 入力値
     *
     */
    @DisplayName("ErrorTest_アプリケーション例外")
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "3",
    })
    void error_002(String inputValue) {
        try (Response result = target(REQUESTPATH)
                .queryParam("embed", inputValue)
                .queryParam("status", "")
                .queryParam("sort", "")
                .request()
                .get();) {
            JsonObject errorJsonObject = new Gson().fromJson(result.readEntity(String.class), JsonObject.class);
            assertAll("",
                    () -> assertEquals("error.test.usecase.mock.002", errorJsonObject.get("error_id").getAsString(), "エラーID"),
                    () -> assertEquals("400", errorJsonObject.get("status").getAsString(), "エラーステータス"),
                    () -> assertEquals("アプリケーション例外が発生しました", errorJsonObject.get("error_message").getAsString(), "エラーメッセージ"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * 異常系テスト003
     *
     * @param inputValue 入力値
     *
     */
    @DisplayName("ErrorTest_システム例外")
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "4",
    })
    void error_003(String inputValue) {
        try (Response result = target(REQUESTPATH)
                .queryParam("embed", inputValue)
                .queryParam("status", "")
                .queryParam("sort", "")
                .request()
                .get();) {
            JsonObject errorJsonObject = new Gson().fromJson(result.readEntity(String.class), JsonObject.class);
            assertAll("",
                    () -> assertEquals("error.test.usecase.mock.003", errorJsonObject.get("error_id").getAsString(), "エラーID"),
                    () -> assertEquals("400", errorJsonObject.get("status").getAsString(), "エラーステータス"),
                    () -> assertEquals("システム例外が発生しました", errorJsonObject.get("error_message").getAsString(), "エラーメッセージ"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * 異常系テスト004
     *
     * @param inputValue 入力値
     *
     */
    @DisplayName("ErrorTest_想定していない例外")
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "5",
    })
    void error_004(String inputValue) {
        try (Response result = target(REQUESTPATH)
                .queryParam("embed", inputValue)
                .queryParam("status", "")
                .queryParam("sort", "")
                .request()
                .get();) {
            JsonObject errorJsonObject = new Gson().fromJson(result.readEntity(String.class), JsonObject.class);
            assertAll("",
                    () -> assertEquals("error.controller.exception.001", errorJsonObject.get("error_id").getAsString(), "エラーID"),
                    () -> assertEquals("500", errorJsonObject.get("status").getAsString(), "エラーステータス"),
                    () -> assertEquals("想定外の例外が発生しました", errorJsonObject.get("error_message").getAsString(), "エラーメッセージ"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
