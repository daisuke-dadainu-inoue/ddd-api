package ddd.application.company.add;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ddd.application.company.add.presenter.CompanyAddPresenterIF;
import ddd.application.company.add.usecase.CompanyAddInputData;
import ddd.application.company.add.usecase.CompanyAddInteractor;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.infrastructure.common.transaction.MyBatisTransaction;
import ddd.infrastructure.common.transaction.TransactionIF;
import ddd.infrastructure.company.CompanyRepositoryMock;
import ddd.interfaces.company.add.presenter.CompanyAddPresenter;

class CompanyAddInteractorTest {

    @Inject
    CompanyAddInteractor interactor;

    @BeforeAll
    static void initAll() {
        // unimplemented
    }

    @BeforeEach
    void init() throws Exception {
        CompanyRepositoryMock.initMap();
        Binder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                bindAsContract(CompanyAddInteractor.class);
                bind(MyBatisTransaction.class).to(TransactionIF.class).in(Singleton.class);
                bind(CompanyRepositoryMock.class).to(CompanyRepositoryIF.class);
                bind(CompanyAddPresenter.class).to(CompanyAddPresenterIF.class);
            }
        };
        ServiceLocator locator = ServiceLocatorUtilities.bind(binder);
        locator.inject(this);
    }

    @AfterEach
    void tearDownOwn() throws Exception {
        // unimplemented
    }

    @AfterAll
    static void tearDownAll() {
        // unimplemented
    }

    /**
     * 正常系テスト001
     */
    @Test
    @DisplayName("SuccessTest001")

    void success_001() {
        try {
            CompanyAddInputData inputData = new CompanyAddInputData("hoge", "hogekana", "1234567", "03", "hogeadd", "1234567890", "open");
            String result = interactor.handle(inputData);
            JsonObject jsonObject = new Gson().fromJson(result, JsonObject.class);
            assertAll("Company",
                    () -> assertNotEquals("", jsonObject.get("company_id").getAsString(), "事業ID"),
                    () -> assertEquals("hoge", jsonObject.get("company_name").getAsString(), "事業名"),
                    () -> assertEquals("hogekana", jsonObject.get("company_name_kana").getAsString(), "事業名かな"),
                    () -> assertEquals("1234567", jsonObject.get("postal_code").getAsString(), "郵便番号"),
                    () -> assertEquals("03", jsonObject.get("address_code").getAsString(), "住所コード"),
                    () -> assertEquals("hogeadd", jsonObject.get("address_name").getAsString(), "住所名"),
                    () -> assertEquals("1234567890", jsonObject.get("phone_number").getAsString(), "電話番号"),
                    () -> assertEquals("open", jsonObject.get("status").getAsString(), "ステータス"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
