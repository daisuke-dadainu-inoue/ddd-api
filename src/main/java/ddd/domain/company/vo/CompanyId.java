package ddd.domain.company.vo;

import ddd.application.common.exception.DDDItemCheckException;
import ddd.interfaces.ResponseStatus;
import lombok.Value;

/**
 * 事業ID
 */
@Value
public class CompanyId {

    private static final String ERROR_USECASE_USER_CHANGEPASS_004 = "error.usecase.user.changepass.004";

    private int value;

    /**
     * @param inputValue 入力値
     * @throws DDDItemCheckException 項目チェック例外
     */
    public CompanyId(int inputValue) throws DDDItemCheckException {
        checkValue(inputValue);
        this.value = inputValue;
    }

    /**
     * @param inputValue 入力値
     * @throws DDDItemCheckException 項目チェック例外
     */
    public void checkValue(int inputValue) throws DDDItemCheckException {
        if (inputValue == 0) {
            throw new DDDItemCheckException(ERROR_USECASE_USER_CHANGEPASS_004, ResponseStatus.BAD_REQUEST.value());
        }
    }

}
