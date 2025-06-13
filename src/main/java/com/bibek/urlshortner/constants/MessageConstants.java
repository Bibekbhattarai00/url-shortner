package com.bibek.urlshortner.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageConstants {
    SUCCESS("response.success"),
    FAIL("response.failed"),
    NOT_FOUND("not.found"),
    REQUIRED("required"),
    METHOD_INVALID("method.invalid"),
    NOT_FOUND_EXCEPTION("exception.not.found"),
    EXCEPTION("exception.thrown"),
    ;
    final String code;
}
