package com.iqmsoft.multipart.boot;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


public class MultipartFormDataBuilder {

    public static HttpEntity<MultiValueMap<String, Object>> buildMultipartRequestEntity(Pair<Resource, MediaType> resourceContentTypePair) {

        Resource resource = resourceContentTypePair.getLeft();
        MediaType contentType = resourceContentTypePair.getRight();

        HttpHeaders multipartHeaders = new HttpHeaders();
        multipartHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        LinkedMultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("file", buildFilePart(resource, contentType));

        if (resource.getFilename() != null) {
            parts.add("name", resource.getFilename());
        }

        return new HttpEntity<>(parts, multipartHeaders);

    }


    public static HttpEntity<Resource> buildFilePart(Resource resource, MediaType mediaType) {

        HttpHeaders headers = null;

        if (mediaType != null) {
            headers = new HttpHeaders();
            headers.setContentType(mediaType);
        }

        return new HttpEntity<>(resource, headers);
    }
}
