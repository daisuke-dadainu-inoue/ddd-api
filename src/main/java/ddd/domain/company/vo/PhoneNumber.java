package ddd.domain.company.vo;

import java.util.Objects;

import ddd.application.common.exception.DDDItemCheckException;
import ddd.interfaces.ResponseStatus;
import lombok.Value;

/**
 * 電話番号
 */
@Value
public class PhoneNumber {

//    private static final String ERROR_USECASE_USER_EDIT_020 = "error.usecase.user.edit.020";
    private static final String ERROR_USECASE_USER_EDIT_021 = "error.usecase.user.edit.021";
    private static final String ERROR_USECASE_USER_EDIT_022 = "error.usecase.user.edit.022";

    private static final int LENGTH_10 = 10;
    private static final int LENGTH_11 = 11;

    private String value;

    /**
     * @param inputValue 入力値
     * @throws DDDItemCheckException 項目チェック例外
     */
    public PhoneNumber(String inputValue) throws DDDItemCheckException {
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
        if (inputValue.length() < LENGTH_10 || inputValue.length() > LENGTH_11) {
            throw new DDDItemCheckException(ERROR_USECASE_USER_EDIT_021, ResponseStatus.BAD_REQUEST.value());
        }
        try {
            Long.parseLong(inputValue);
        } catch (Exception e) {
            throw new DDDItemCheckException(ERROR_USECASE_USER_EDIT_022, ResponseStatus.BAD_REQUEST.value(), e);
        }
    }

}
