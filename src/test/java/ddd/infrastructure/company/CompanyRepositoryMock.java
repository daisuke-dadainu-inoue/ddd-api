package ddd.infrastructure.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;
import ddd.domain.company.model.Company;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.domain.company.vo.CompanyId;

public class CompanyRepositoryMock implements CompanyRepositoryIF {

    private static Map<Integer, Company> companyMap = new HashMap<>();

    @Override
    public List<Company> find(SqlSession session, CompanyQueryParam queryParam) throws DDDSystemException {
        return new ArrayList<>(companyMap.values());
    }

    @Override
    public Company findById(SqlSession session, CompanyId companyId) throws DDDSystemException {
        return companyMap.get(companyId.getValue());
    }

    @Override
    public Company add(SqlSession session, Company company) throws DDDSystemException {
        companyMap.put(company.getCompanyId().getValue(), company);
        return company;
    }

    @Override
    public Company update(SqlSession session, Company company) throws DDDSystemException {
        companyMap.put(company.getCompanyId().getValue(), company);
        return company;
    }

    @Override
    public CompanyId maxCompanyId(SqlSession session) throws DDDSystemException, DDDItemCheckException {
        List<Company> companyList = new ArrayList<>(companyMap.values());
        Comparator<Company> comparator = Comparator.comparing((Company company) -> company.getCompanyId().getValue()).reversed();
        int companyId = companyList.size() == 0 ? 1 : companyList.stream().sorted(comparator).findFirst().get().getCompanyId().getValue() + 1;
        return new CompanyId(companyId);
    }

    /**
     * 初期化
     */
    public static void initMap() {
        companyMap = new HashMap<>();
    }
}
