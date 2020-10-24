package ddd.application.company.find;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;
import ddd.application.company.find.usecase.CompanyFindByIdInputData;
import ddd.application.company.find.usecase.CompanyFindByIdOutputData;
import ddd.application.company.find.usecase.CompanyFindByIdUseCaseIF;
import ddd.interfaces.ResponseStatus;
import ddd.interfaces.common.JsonConverter;

public class CompanyFindByIdInteractorMock implements CompanyFindByIdUseCaseIF {

    @Override
    public String handle(CompanyFindByIdInputData inputData) throws DDDItemCheckException, DDDApplicationException, DDDSystemException {
        if (inputData.getCompanyId() == 2) {
            throw new DDDItemCheckException("error.test.usecase.mock.001", ResponseStatus.BAD_REQUEST.value());
        }
        if (inputData.getCompanyId() == 3) {
            throw new DDDApplicationException("error.test.usecase.mock.002", ResponseStatus.BAD_REQUEST.value());
        }
        if (inputData.getCompanyId() == 4) {
            throw new DDDSystemException("error.test.usecase.mock.003", ResponseStatus.BAD_REQUEST.value());
        }
        if (inputData.getCompanyId() == 5) {
            String hoge = null;
            hoge.chars();
        }
        return JsonConverter.toJson(new CompanyFindByIdOutputData(1, "hoge", "hogekana", "1234567", "13", "hogeadd", "0312345678", "open"));
    }

}
