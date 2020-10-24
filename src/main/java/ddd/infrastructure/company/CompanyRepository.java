package ddd.infrastructure.company;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import ddd.application.common.exception.DDDSystemException;
import ddd.domain.company.model.Company;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.domain.company.vo.CompanyId;
import ddd.interfaces.ResponseStatus;

public class CompanyRepository implements CompanyRepositoryIF {

    @Override
    public List<Company> find(SqlSession session, CompanyQueryParam queryParam) throws DDDSystemException {
        try {
            CompanyMapper mapper = session.getMapper(CompanyMapper.class);
            List<CompanyDTO> companyDTOs = mapper.find(queryParam.getStatus(), queryParam.getSort());
            List<Company> companies = new ArrayList<>();
            for (CompanyDTO companyDTO : companyDTOs) {
                companies.add(companyDTO.adaptCompany());
            }
            return companies;
        } catch (Exception e) {
            throw new DDDSystemException("", ResponseStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    @Override
    public Company findById(SqlSession session, CompanyId companyId) throws DDDSystemException {
        try {
            CompanyMapper mapper = session.getMapper(CompanyMapper.class);
            return mapper.findById(companyId.getValue()).adaptCompany();
        } catch (Exception e) {
            throw new DDDSystemException("", ResponseStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    @Override
    public Company add(SqlSession session, Company company) throws DDDSystemException {
        try {
            CompanyMapper mapper = session.getMapper(CompanyMapper.class);
            mapper.add(new CompanyDTO(company));
            return findById(session, company.getCompanyId());
        } catch (Exception e) {
            throw new DDDSystemException("", ResponseStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    @Override
    public Company update(SqlSession session, Company company) throws DDDSystemException {
        try {
            CompanyMapper mapper = session.getMapper(CompanyMapper.class);
            mapper.update(new CompanyDTO(company));
            return findById(session, company.getCompanyId());
        } catch (Exception e) {
            throw new DDDSystemException("", ResponseStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    @Override
    public CompanyId maxCompanyId(SqlSession session) throws DDDSystemException {
        try {
            CompanyMapper mapper = session.getMapper(CompanyMapper.class);
            int result = mapper.maxCompanyId();
            return new CompanyId(result == 0 ? 1 : result + 1);
        } catch (Exception e) {
            throw new DDDSystemException("", ResponseStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

}
