package ddd.application.company.add;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;
import ddd.application.company.add.usecase.CompanyAddInputData;
import ddd.application.company.add.usecase.CompanyAddOutputData;
import ddd.application.company.add.usecase.CompanyAddUseCaseIF;
import ddd.interfaces.ResponseStatus;
import ddd.interfaces.common.JsonConverter;

public class CompanyAddInteractorMock implements CompanyAddUseCaseIF {

    @Override
    public String handle(CompanyAddInputData inputData) throws DDDItemCheckException, DDDApplicationException, DDDSystemException {
        if (inputData.getCompanyName().equals("2")) {
            throw new DDDItemCheckException("error.test.usecase.mock.001", ResponseStatus.BAD_REQUEST.value());
        }
        if (inputData.getCompanyName().equals("3")) {
            throw new DDDApplicationException("error.test.usecase.mock.002", ResponseStatus.BAD_REQUEST.value());
        }
        if (inputData.getCompanyName().equals("4")) {
            throw new DDDSystemException("error.test.usecase.mock.003", ResponseStatus.BAD_REQUEST.value());
        }
        if (inputData.getCompanyName().equals("5")) {
            String hoge = null;
            hoge.chars();
        }
        return JsonConverter.toJson(new CompanyAddOutputData(1, "hoge", "hogekana", "1234567", "13", "hogeadd", "0312345678", "open"));
    }

}
