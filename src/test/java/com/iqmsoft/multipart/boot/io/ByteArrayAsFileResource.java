package com.iqmsoft.multipart.boot.io;

import org.springframework.core.io.ByteArrayResource;


public class ByteArrayAsFileResource extends ByteArrayResource {

    public ByteArrayAsFileResource(byte[] byteArray, String description) {
        super(byteArray, description);
    }

    @Override
    public String getFilename() {
        return getDescription().replaceAll(".*\\[|\\].*", "");
    }
}
