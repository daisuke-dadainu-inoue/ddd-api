package ddd.application.company.update;

import java.util.ArrayList;
import java.util.List;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;
import ddd.application.company.update.usecase.CompanyUpdateInputData;
import ddd.application.company.update.usecase.CompanyUpdateOutputData;
import ddd.application.company.update.usecase.CompanyUpdateOutputDataItem;
import ddd.application.company.update.usecase.CompanyUpdateUseCaseIF;
import ddd.interfaces.ResponseStatus;
import ddd.interfaces.common.JsonConverter;

public class CompanyUpdateInteractorMock implements CompanyUpdateUseCaseIF {

    @Override
    public String handle(CompanyUpdateInputData inputData) throws DDDItemCheckException, DDDApplicationException, DDDSystemException {
        if (inputData.getItems().get(0).getCompanyId() == 2) {
            throw new DDDItemCheckException("error.test.usecase.mock.001", ResponseStatus.BAD_REQUEST.value());
        }
        if (inputData.getItems().get(0).getCompanyId() == 3) {
            throw new DDDApplicationException("error.test.usecase.mock.002", ResponseStatus.BAD_REQUEST.value());
        }
        if (inputData.getItems().get(0).getCompanyId() == 4) {
            throw new DDDSystemException("error.test.usecase.mock.003", ResponseStatus.BAD_REQUEST.value());
        }
        if (inputData.getItems().get(0).getCompanyId() == 5) {
            String hoge = null;
            hoge.chars();
        }
        List<CompanyUpdateOutputDataItem> items = new ArrayList<>();
        items.add(new CompanyUpdateOutputDataItem(1, "hoge", "hogekana", "1234567", "13", "hogeadd", "0312345678", "open"));
        return JsonConverter.toJson(new CompanyUpdateOutputData(items));
    }

}
