package ddd.application.company.update.usecase;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;

public interface CompanyUpdateUseCaseIF {

    public String handle(CompanyUpdateInputData inputData) throws DDDItemCheckException, DDDApplicationException, DDDSystemException;
}
