package ddd.domain.company.vo;

import java.util.Objects;

import ddd.application.common.exception.DDDItemCheckException;
import ddd.interfaces.ResponseStatus;
import lombok.Value;

/**
 * 住所コード
 */
@Value
public class AddressCode {

//    private static final String ERROR_USECASE_USER_EDIT_016 = "error.usecase.user.edit.016";
    private static final String ERROR_USECASE_USER_EDIT_017 = "error.usecase.user.edit.017";

    private static final int LENGTH_128 = 128;

    private String value;

    /**
     * @param inputValue 入力値
     * @throws DDDItemCheckException 項目チェック例外
     */
    public AddressCode(String inputValue) throws DDDItemCheckException {
        checkValue(inputValue);
        this.value = inputValue;
    }

    /**
     * @param inputValue 入力値
     * @throws DDDItemCheckException 項目チェック例外
     */
    public void checkValue(String inputValue) throws DDDItemCheckException {
        if (Objects.isNull(inputValue) || inputValue.isEmpty()) {
            return;
        }
        if (inputValue.length() >= LENGTH_128) {
            throw new DDDItemCheckException(ERROR_USECASE_USER_EDIT_017, ResponseStatus.BAD_REQUEST.value());
        }
    }

}
