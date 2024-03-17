package com.bugratasdemir.graduationprojectapi.exceptions;


import com.bugratasdemir.graduationprojectapi.errormessage.BaseErrorMessage;
import com.bugratasdemir.graduationprojectapi.general.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends BusinessException {
    public ItemNotFoundException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
