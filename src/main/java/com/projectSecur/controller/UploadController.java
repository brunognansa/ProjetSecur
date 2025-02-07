package com.projectSecur.controller;

import java.nio.file.Files;
import java.nio.file.Path; // Utilisez cette importation
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus; // Importation de HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projectSecur.service.BaseServiceImpl;
import com.projectSecur.service.UserServiceImpl;

@RestController
@RequestMapping(value="service/upload")
@CrossOrigin(origins="*")
public class UploadController {

    @Autowired
    @Qualifier("BaseService")
    BaseServiceImpl baseService;

    @Autowired
    @Qualifier("UserService")
    UserServiceImpl userService;

    @PostMapping(value="/file")
    public ResponseEntity<String> uploadFiles(
        @RequestParam(value = "residence", required = false) MultipartFile residenceFile,
        @RequestParam(value = "profile", required = false) MultipartFile profileFile,
        @RequestParam(value = "nationalite", required = false) MultipartFile nationaliteFile,
        @RequestParam(value = "carte_identite", required = false) MultipartFile carte_identiteFile) {
        
        try {
            if (residenceFile != null) {
                String residenceFileName = residenceFile.getOriginalFilename();
                String residenceFileType = residenceFile.getContentType();
                System.out.println("Received file: " + residenceFileName + " of type: " + residenceFileType);

                String residenceUploadDir = "src/main/resources/static/user-information/user-certificat-residence";
                Path residenceUploadPath = Paths.get(residenceUploadDir);

                if (!Files.exists(residenceUploadPath)) {
                    Files.createDirectories(residenceUploadPath);
                }

                Path residenceFilePath = residenceUploadPath.resolve(residenceFile.getOriginalFilename());
                Files.copy(residenceFile.getInputStream(), residenceFilePath);
            } else {
                System.out.println("No residence file received");
            }
            
            if (nationaliteFile != null) {
                String nationaliteFileName = nationaliteFile.getOriginalFilename();
                String nationaliteFileType = nationaliteFile.getContentType();
                System.out.println("Received file: " + nationaliteFileName + " of type: " + nationaliteFileType);

                String nationaliteUploadDir = "src/main/resources/static/user-information/user-nationalite";
                Path nationaliteUploadPath = Paths.get(nationaliteUploadDir);

                if (!Files.exists(nationaliteUploadPath)) {
                    Files.createDirectories(nationaliteUploadPath);
                }

                Path nationaliteFilePath = nationaliteUploadPath.resolve(nationaliteFile.getOriginalFilename());
                Files.copy(nationaliteFile.getInputStream(), nationaliteFilePath);
            } else {
                System.out.println("No nationalite file received");
            }
            
            if (carte_identiteFile != null) {
                String carte_identiteFileName = carte_identiteFile.getOriginalFilename();
                String carte_identiteFileType = carte_identiteFile.getContentType();
                System.out.println("Received file: " + carte_identiteFileName + " of type: " + carte_identiteFileType);

                String carte_identiteUploadDir = "src/main/resources/static/user-information/user-carte_identite";
                Path carte_identiteUploadPath = Paths.get(carte_identiteUploadDir);

                if (!Files.exists(carte_identiteUploadPath)) {
                    Files.createDirectories(carte_identiteUploadPath);
                }

                Path carte_identiteFilePath = carte_identiteUploadPath.resolve(carte_identiteFile.getOriginalFilename());
                Files.copy(carte_identiteFile.getInputStream(), carte_identiteFilePath);
            } else {
                System.out.println("No carte d'identité file received");
            }

            if (profileFile != null) {
                String profileFileName = profileFile.getOriginalFilename();
                String profileFileType = profileFile.getContentType();
                System.out.println("Received file: " + profileFileName + " of type: " + profileFileType);

                String profileUploadDir = "src/main/resources/static/user-information/user-profile";
                Path profileUploadPath = Paths.get(profileUploadDir);

                if (!Files.exists(profileUploadPath)) {
                    Files.createDirectories(profileUploadPath);
                }

                Path profileFilePath = profileUploadPath.resolve(profileFile.getOriginalFilename());
                Files.copy(profileFile.getInputStream(), profileFilePath);
            } else {
                System.out.println("No profile file received");
            }

            return ResponseEntity.status(HttpStatus.OK).body("Files uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload files");
        }
    }

}
