package ddd.application.company.find.usecase;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;

public interface CompanyFindUseCaseIF {

    public String handle(CompanyFindInputData inputData) throws DDDItemCheckException, DDDApplicationException, DDDSystemException;
}
