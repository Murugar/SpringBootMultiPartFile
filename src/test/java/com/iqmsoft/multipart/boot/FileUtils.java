package com.iqmsoft.multipart.boot;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;


public class FileUtils {

    public static File createTemporaryFile(String body) throws IOException {
        File file = File.createTempFile("upload_", ".tmp");
        Files.write(body, file, Charset.defaultCharset());
        return file;
    }
}
