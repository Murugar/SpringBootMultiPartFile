package com.iqmsoft.multipart.boot;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


@RestController
@RequestMapping(value = "/files", produces = MediaType.APPLICATION_JSON_VALUE)
public class UploadingController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestPart(value = "name", required = false) String name,
                                         @RequestPart(value = "file") MultipartFile file) {

        if (name == null) {
            name = file.getOriginalFilename();
        }

        if (!file.isEmpty()) {
            try {

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(Application.ROOT + "/" + name)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();

            } catch (Exception e) {
                String message = "You failed to upload " + name + " => " + e.getMessage();
                return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            String message = "You failed to upload " + name + " because the file is empty.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("File uploaded.", HttpStatus.CREATED);
    }

}
