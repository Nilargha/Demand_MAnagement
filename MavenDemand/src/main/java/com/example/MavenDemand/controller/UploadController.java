package com.example.MavenDemand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.MavenDemand.SimpleDateFormatter;
import com.example.MavenDemand.dao.LorealDaoImpl;
import com.example.MavenDemand.model.ResponseModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;


@RestController
public class UploadController {

	@Autowired 
	LorealDaoImpl daoimpl;
	
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://temp//";

    @GetMapping("/api/uploadfile")
    public String index() {
        return "/api/upload";
    }

    @PostMapping("/api/upload/{demid}/{name}/{newfile}") 
    public Object singleFileUpload(@RequestParam("file") MultipartFile file,
    		@PathVariable("demid") String requestId,@PathVariable("name") String creatorname,@PathVariable("newfile") String filedesc) {
    	
    	System.out.println(requestId+" "+creatorname+" "+ filedesc);

        if (file.isEmpty()) {
            
            return new ResponseEntity<>(new ResponseModel("Empty Content"),HttpStatus.BAD_REQUEST);
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            Date date=Date.valueOf(new SimpleDateFormatter().format());
            
            daoimpl.createDemandUploadDocument(requestId, UPLOADED_FOLDER + file.getOriginalFilename(),creatorname,filedesc,date);
            
            return new ResponseEntity<>(new ResponseModel("You successfully uploaded '" + file.getOriginalFilename() + "'"),HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ResponseModel("Your upload unsuccessful"),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/uploadStatus")
    public String uploadStatus() {
        return "/api/uploadStatus";
    }

}