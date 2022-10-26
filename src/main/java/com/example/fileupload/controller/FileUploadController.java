package com.example.fileupload.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "D:\\mine.001\\MAVEN-PROJECTS\\fileUpload\\filesuploaded\\";

    @GetMapping("/index")
    public String hello() {
        return "uploader";
    }

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/uploadd")
    public ResponseEntity<?> handleFileUpload(
            @RequestParam("file") MultipartFile file
    ) {

        String fileName = file.getOriginalFilename();

        try {
            file.transferTo(new File("D:\\mine.001\\MAVEN-PROJECTS\\fileUpload\\filesuploaded\\" + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok("File Uploaded successfully");
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            //Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path,bytes);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename());
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
