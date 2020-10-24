package ddd.infrastructure.company;

import java.time.LocalDateTime;

import ddd.application.common.exception.DDDItemCheckException;
import ddd.domain.common.vo.Status;
import ddd.domain.common.vo.StatusConst;
import ddd.domain.company.model.Company;
import ddd.domain.company.vo.AddressCode;
import ddd.domain.company.vo.AddressName;
import ddd.domain.company.vo.CompanyId;
import ddd.domain.company.vo.CompanyName;
import ddd.domain.company.vo.CompanyNameKana;
import ddd.domain.company.vo.PhoneNumber;
import ddd.domain.company.vo.PostalCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyDTO {
    private int companyId;
    private LocalDateTime registerDateTime;
    private LocalDateTime updateDateTime;
    private String companyName;
    private String companyNameKana;
    private String postalCode;
    private String addressCode;
    private String addressName;
    private Double lat;
    private Double lon;
    private String mainPhoneNumber;
    private int deleteFlag;

    public CompanyDTO(Company company) {
        this.companyId = company.getCompanyId().getValue();
        this.registerDateTime = LocalDateTime.now();
        this.updateDateTime = LocalDateTime.now();
        this.companyName = company.getCompanyName().getValue();
        this.companyNameKana = company.getCompanyNameKana().getValue();
        this.postalCode = company.getPostalCode().getValue();
        this.addressCode = company.getAddressCode().getValue();
        this.addressName = company.getAddressName().getValue();
        this.lat = 0.0;
        this.lon = 0.0;
        this.mainPhoneNumber = company.getPhoneNumber().getValue();
    }

    public Company adaptCompany() throws DDDItemCheckException {
        return new Company(
                new CompanyId(companyId), new CompanyName(companyName),
                new CompanyNameKana(companyNameKana), new PostalCode(postalCode),
                new AddressCode(addressCode), new AddressName(addressName),
                new PhoneNumber(mainPhoneNumber), new Status(StatusConst.valueOf(deleteFlag)));
    }
}
