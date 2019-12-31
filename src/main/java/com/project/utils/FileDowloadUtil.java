package com.project.utils;

import com.project.constants.CommonConstants;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;

public class FileDowloadUtil {
    public  static ResponseEntity<InputStreamResource> download( String path){
        HttpHeaders responseHeader = new HttpHeaders();
        try {

            String filePath = Paths.get(CommonConstants.RESOURCE_SERVER, path).toString();;
            File file = new File(filePath);
            byte[] data = FileUtils.readFileToByteArray(file);
            // Set mimeType return
            responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // setting info return
            responseHeader.set("Content-disposition", "attachment; filename=" + file.getName());
            responseHeader.setContentLength(data.length);
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            return new ResponseEntity<InputStreamResource>(inputStreamResource, responseHeader, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<InputStreamResource>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

