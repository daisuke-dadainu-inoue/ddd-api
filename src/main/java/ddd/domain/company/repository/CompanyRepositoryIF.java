package ddd.domain.company.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;
import ddd.domain.company.model.Company;
import ddd.domain.company.vo.CompanyId;
import ddd.infrastructure.company.CompanyQueryParam;

public interface CompanyRepositoryIF {

    List<Company> find(SqlSession session, CompanyQueryParam queryParam) throws DDDSystemException;

    Company findById(SqlSession session, CompanyId companyId) throws DDDSystemException;

    Company add(SqlSession session, Company company) throws DDDSystemException;

    Company update(SqlSession session, Company company) throws DDDSystemException;

    CompanyId maxCompanyId(SqlSession session) throws DDDSystemException, DDDItemCheckException;
}
