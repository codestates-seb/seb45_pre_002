package com.codestates.stackoverflow.utils;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageInfo {

    private int pageNumber;

    private int pageSize;

    private long totalElements;

    private int totalPages;
}
