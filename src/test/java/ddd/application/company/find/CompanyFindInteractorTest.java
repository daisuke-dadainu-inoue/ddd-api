package ddd.application.company.find;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import ddd.application.company.find.presenter.CompanyFindPresenterIF;
import ddd.application.company.find.usecase.CompanyFindInputData;
import ddd.application.company.find.usecase.CompanyFindInteractor;
import ddd.domain.common.vo.Status;
import ddd.domain.common.vo.StatusConst;
import ddd.domain.company.model.Company;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.domain.company.vo.AddressCode;
import ddd.domain.company.vo.AddressName;
import ddd.domain.company.vo.CompanyId;
import ddd.domain.company.vo.CompanyName;
import ddd.domain.company.vo.CompanyNameKana;
import ddd.domain.company.vo.PhoneNumber;
import ddd.domain.company.vo.PostalCode;
import ddd.infrastructure.common.transaction.MyBatisTransaction;
import ddd.infrastructure.common.transaction.TransactionIF;
import ddd.infrastructure.company.CompanyRepositoryMock;
import ddd.interfaces.company.find.presenter.CompanyFindPresenter;

class CompanyFindInteractorTest {

    @Inject
    CompanyFindInteractor interactor;

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
                bindAsContract(CompanyFindInteractor.class);
                bind(MyBatisTransaction.class).to(TransactionIF.class).in(Singleton.class);
                bind(CompanyRepositoryMock.class).to(CompanyRepositoryIF.class);
                bind(CompanyFindPresenter.class).to(CompanyFindPresenterIF.class);
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
            CompanyRepositoryMock companyRepositoryMock = new CompanyRepositoryMock();
            companyRepositoryMock.add(null, new Company(
                    new CompanyId(1), new CompanyName("hoge"), new CompanyNameKana("hogekana"), new PostalCode("1234567"), new AddressCode("03"),
                    new AddressName("hogeadd"), new PhoneNumber("1234567890"), new Status(StatusConst.OPEN)));

            CompanyFindInputData inputData = new CompanyFindInputData("", "", "");
            String result = interactor.handle(inputData);
            JsonObject jsonItemObject = new Gson().fromJson(result, JsonObject.class);
            JsonObject jsonObject = jsonItemObject.get("items").getAsJsonArray().get(0).getAsJsonObject();
            assertAll("Company",
                    () -> assertEquals("1", jsonObject.get("company_id").getAsString(), "事業ID"),
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
