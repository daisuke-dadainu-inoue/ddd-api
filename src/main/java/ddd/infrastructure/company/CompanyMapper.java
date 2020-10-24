package ddd.infrastructure.company;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 事業テーブルマッパー
 */
public interface CompanyMapper {

    List<CompanyDTO> find(@Param("status") List<String> status, @Param("sort") List<String> sort);

    CompanyDTO findById(@Param("companyId") int companyId);

    int add(@Param("company") CompanyDTO company);

    int update(@Param("company") CompanyDTO company);

    int maxCompanyId();

}
