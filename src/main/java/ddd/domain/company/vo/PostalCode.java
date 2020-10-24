package ddd.domain.company.vo;

import java.util.Objects;

import ddd.application.common.exception.DDDItemCheckException;
import ddd.interfaces.ResponseStatus;
import lombok.Value;

/**
 * 郵便番号
 */
@Value
public class PostalCode {

//    private static final String ERROR_USECASE_USER_EDIT_013 = "error.usecase.user.edit.013";
    private static final String ERROR_USECASE_USER_EDIT_014 = "error.usecase.user.edit.014";
    private static final String ERROR_USECASE_USER_EDIT_015 = "error.usecase.user.edit.015";

    private static final int LENGTH_7 = 7;

    private String value;

    /**
     * @param inputValue 入力値
     * @throws DDDItemCheckException 項目チェック例外
     */
    public PostalCode(String inputValue) throws DDDItemCheckException {
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
        if (inputValue.length() != LENGTH_7) {
            throw new DDDItemCheckException(ERROR_USECASE_USER_EDIT_014, ResponseStatus.BAD_REQUEST.value());
        }
        try {
            Long.parseLong(inputValue);
        } catch (Exception e) {
            throw new DDDItemCheckException(ERROR_USECASE_USER_EDIT_015, ResponseStatus.BAD_REQUEST.value(), e);
        }
    }

}
