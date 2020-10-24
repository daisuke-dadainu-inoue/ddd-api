package ddd.domain.company.vo;

import java.util.Objects;

import ddd.application.common.exception.DDDItemCheckException;
import ddd.interfaces.ResponseStatus;
import lombok.Value;

/**
 * 事業名
 */
@Value
public class CompanyName {

    private static final String ERROR_USECASE_USER_EDIT_005 = "error.usecase.user.edit.005";
    private static final String ERROR_USECASE_USER_EDIT_006 = "error.usecase.user.edit.006";

    private static final int LENGTH_128 = 128;

    private String value;

    /**
     * @param inputValue 入力値
     * @throws DDDItemCheckException 項目チェック例外
     */
    public CompanyName(String inputValue) throws DDDItemCheckException {
        checkValue(inputValue);
        this.value = inputValue;
    }

    /**
     * @param inputValue 入力値
     * @throws DDDItemCheckException 項目チェック例外
     */
    public void checkValue(String inputValue) throws DDDItemCheckException {
        if (Objects.isNull(inputValue) || inputValue.isEmpty()) {
            throw new DDDItemCheckException(ERROR_USECASE_USER_EDIT_005, ResponseStatus.BAD_REQUEST.value());
        }
        if (inputValue.length() >= LENGTH_128) {
            throw new DDDItemCheckException(ERROR_USECASE_USER_EDIT_006, ResponseStatus.BAD_REQUEST.value());
        }
    }

}
