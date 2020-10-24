package ddd.domain.company.model;

import ddd.application.common.exception.DDDItemCheckException;
import ddd.domain.common.vo.Status;
import ddd.domain.common.vo.StatusConst;
import ddd.domain.company.vo.AddressCode;
import ddd.domain.company.vo.AddressName;
import ddd.domain.company.vo.CompanyId;
import ddd.domain.company.vo.CompanyName;
import ddd.domain.company.vo.CompanyNameKana;
import ddd.domain.company.vo.PhoneNumber;
import ddd.domain.company.vo.PostalCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 事業
 */
@Data
@AllArgsConstructor
public class Company {
    private CompanyId companyId;
    private CompanyName companyName;
    private CompanyNameKana companyNameKana;
    private PostalCode postalCode;
    private AddressCode addressCode;
    private AddressName addressName;
    private PhoneNumber phoneNumber;
    private Status status;

    public void changeCompanyName(String companyName) throws DDDItemCheckException {
        if (companyName.isEmpty()) {
            return;
        }
        this.companyName = new CompanyName(companyName);
    }

    public void changeCompanyNameKana(String companyNameKana) throws DDDItemCheckException {
        if (companyNameKana.isEmpty()) {
            return;
        }
        this.companyNameKana = new CompanyNameKana(companyNameKana);
    }

    public void changePostalCode(String postalCode) throws DDDItemCheckException {
        if (postalCode.isEmpty()) {
            return;
        }
        this.postalCode = new PostalCode(postalCode);
    }

    public void changeAddressCode(String addressCode) throws DDDItemCheckException {
        if (addressCode.isEmpty()) {
            return;
        }
        this.addressCode = new AddressCode(addressCode);
    }

    public void changeAddressName(String addressName) throws DDDItemCheckException {
        if (addressName.isEmpty()) {
            return;
        }
        this.addressName = new AddressName(addressName);
    }

    public void changePhoneNumber(String phoneNumber) throws DDDItemCheckException {
        if (phoneNumber.isEmpty()) {
            return;
        }
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }

    public void changeStatus(String status) throws DDDItemCheckException {
        if (status.isEmpty()) {
            return;
        }
        this.status = new Status(StatusConst.nameOf(status));
    }
}
