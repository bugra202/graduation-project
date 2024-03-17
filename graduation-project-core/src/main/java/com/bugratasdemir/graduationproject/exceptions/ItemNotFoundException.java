package com.bugratasdemir.graduationproject.exceptions;

import com.bugratasdemir.graduationproject.errormessage.BaseErrorMessage;
import com.bugratasdemir.graduationproject.general.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends BusinessException {
    public ItemNotFoundException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
