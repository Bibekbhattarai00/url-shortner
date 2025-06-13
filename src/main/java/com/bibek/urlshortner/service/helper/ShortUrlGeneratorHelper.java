package com.bibek.urlshortner.service.helper;

import com.bibek.urlshortner.utils.StringEncryptorUtil;

public class ShortUrlGeneratorHelper {

    protected String generateShortCode(Long id) {
        return StringEncryptorUtil.encrypt(String.valueOf(id));
    }

    protected Long decryptShortCode(String shortCode) {
        return Long.valueOf(StringEncryptorUtil.decrypt(shortCode));
    }
}
