package ddd.application.company.find.usecase;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;

public interface CompanyFindByIdUseCaseIF {

    public String handle(CompanyFindByIdInputData inputData) throws DDDItemCheckException, DDDApplicationException, DDDSystemException;
}
