package ddd.application.company.update;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

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

import ddd.application.company.update.presenter.CompanyUpdatePresenterIF;
import ddd.application.company.update.usecase.CompanyUpdateInputData;
import ddd.application.company.update.usecase.CompanyUpdateInputDataItem;
import ddd.application.company.update.usecase.CompanyUpdateInteractor;
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
import ddd.interfaces.company.update.presenter.CompanyUpdatePresenter;

class CompanyUpdateInteractorTest {

    @Inject
    CompanyUpdateInteractor interactor;

    @BeforeAll
    static void initAll() {
        // unimplemented
    }

    @BeforeEach
    void init() throws Exception {
        try {

            CompanyRepositoryMock.initMap();
            Binder binder = new AbstractBinder() {
                @Override
                protected void configure() {
                    bindAsContract(CompanyUpdateInteractor.class);
                    bind(MyBatisTransaction.class).to(TransactionIF.class).in(Singleton.class);
                    bind(CompanyRepositoryMock.class).to(CompanyRepositoryIF.class);
                    bind(CompanyUpdatePresenter.class).to(CompanyUpdatePresenterIF.class);
                }
            };
            ServiceLocator locator = ServiceLocatorUtilities.bind(binder);
            locator.inject(this);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
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
                    new CompanyId(1), new CompanyName("hogehoge"), new CompanyNameKana("hogekanakana"), new PostalCode("0234567"), new AddressCode("13"),
                    new AddressName("hogeupdate"), new PhoneNumber("1234567890"), new Status(StatusConst.CLOSE)));

            List<CompanyUpdateInputDataItem> items = new ArrayList<>();
            items.add(new CompanyUpdateInputDataItem(1, "hoge", "hogekana", "1234567", "03", "hogeadd", "1234567890", "open"));
            String result = interactor.handle(new CompanyUpdateInputData(items));
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
