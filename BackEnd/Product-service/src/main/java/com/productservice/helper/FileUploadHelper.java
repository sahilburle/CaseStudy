package com.productservice.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    //public final String UPLOAD_DIR="C:\\Users\\saanilra\\Desktop\\Project\\Shopping Project\\Product-service\\src\\main\\resources\\static\\image";
    public final String UPLOAD_DIR=new ClassPathResource("static/image").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException
    {

    }

    public boolean uploadFile(MultipartFile multipartFile)
    {
        boolean f=false;

        try{
//            InputStream is = multipartFile.getInputStream();
//            byte data[]=new byte[is.available()];
//
//            is.read(data);
//
//            FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+ File.separator +multipartFile.getOriginalFilename());
            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            f=true;

        }catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }
}