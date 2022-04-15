package com.example.bitdefenderbackend.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public class Pagination {

    private static final String HEADER_X_TOTAL_COUNT = "X-Total-Count";
    private static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";

    private Pagination() {
    }

    public static <T> HttpHeaders generatePaginationHttpHeaders(Page<T> page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_X_TOTAL_COUNT, Long.toString(page.getTotalElements()));
        headers.add(ACCESS_CONTROL_EXPOSE_HEADERS, "*");
        return headers;
    }

}
