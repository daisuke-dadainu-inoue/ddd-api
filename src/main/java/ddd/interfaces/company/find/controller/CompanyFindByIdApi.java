package ddd.interfaces.company.find.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;
import ddd.application.common.exception.ErrorResponse;
import ddd.application.company.find.usecase.CompanyFindByIdInputData;
import ddd.application.company.find.usecase.CompanyFindByIdUseCaseIF;
import ddd.infrastructure.common.config.DDDConfig;
import ddd.interfaces.ResponseStatus;
import ddd.interfaces.common.JsonConverter;

@Path("/company/{companyId}")
public class CompanyFindByIdApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyFindByIdApi.class);

    private static final String ERROR_EXCEPTION_001 = "error.controller.exception.001";

    private final CompanyFindByIdUseCaseIF useCase;

    @Inject
    public CompanyFindByIdApi(CompanyFindByIdUseCaseIF useCase) {
        this.useCase = useCase;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response exec(@PathParam("companyId") int companyId) {
        try {
            CompanyFindByIdInputData inputData = new CompanyFindByIdInputData(companyId);
            String resultJson = useCase.handle(inputData);
            return Response.status(ResponseStatus.OK.value()).entity(resultJson).build();
        } catch (DDDItemCheckException e) {
            LOGGER.info(e.getMessage(), e);
            return Response.status(e.getStatus()).entity(e.responseMessage()).build();
        } catch (DDDApplicationException e) {
            LOGGER.warn(e.getMessage(), e);
            return Response.status(e.getStatus()).entity(e.responseMessage()).build();
        } catch (DDDSystemException e) {
            LOGGER.error(e.getMessage(), e);
            return Response.status(e.getStatus()).entity(e.responseMessage()).build();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            ErrorResponse errorResponse = new ErrorResponse(ERROR_EXCEPTION_001, ResponseStatus.INTERNAL_SERVER_ERROR.value(),
                    DDDConfig.getErrorPropertyValue(ERROR_EXCEPTION_001));
            return Response.status(errorResponse.getStatus()).entity(JsonConverter.toJson(errorResponse)).build();
        }
    }
}
