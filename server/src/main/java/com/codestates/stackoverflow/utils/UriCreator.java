package com.codestates.stackoverflow.utils;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class UriCreator {
    public static URI createUri(String defaultUrl, long resourceId) {
        return UriComponentsBuilder
                .newInstance()
                .path(defaultUrl + "/{resourceId}")
                .buildAndExpand(resourceId)
                .toUri();
    }
}
