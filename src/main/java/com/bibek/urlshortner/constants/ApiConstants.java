package com.bibek.urlshortner.constants;

public class ApiConstants {
    public static final String API_VERSION_V1 = "/api/v1";

    public static class Url {

        public static final String BASE_URL = API_VERSION_V1 + "/url";
        public static final String SHORTEN = "/shorten";
        public static final String GET = "/{short_code}";
        public static final String ANALYTICS = "/analytics/{short_code}";
        public static final String GET_PAGINATED = "/get-Paginated";
        public static final String ALL = "/all";
    }

}
