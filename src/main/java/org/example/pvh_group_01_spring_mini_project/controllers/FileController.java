package org.example.pvh_group_01_spring_mini_project.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Builder;
import lombok.SneakyThrows;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.ApiRespones;
import org.example.pvh_group_01_spring_mini_project.models.entity.FileMetaData;
import org.example.pvh_group_01_spring_mini_project.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/files")
@SecurityRequirement(name = "bearerAuth")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @Operation(summary = "Upload a file")
    @PostMapping(value = "/upload-file",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiRespones<FileMetaData>> uploadFile(@RequestPart(value = "file", required = true) MultipartFile file){
        ApiRespones<FileMetaData> respones = ApiRespones.<FileMetaData>builder()
                .success(true)
                .message("File uploaded successfully! metadata of the file upload is return")
                .status(HttpStatus.CREATED)
                .payload(fileService.uploadFile(file))
                .timestamps(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(respones);
    }

    @Operation(summary = "Preview a file")
    @SneakyThrows
    @GetMapping("/preview-file/{file-name}")
    public ResponseEntity<?> getFileByName(@PathVariable("file-name") String fileName){
        InputStream resource = fileService.getFileByName(fileName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource.readAllBytes());
    }
}
