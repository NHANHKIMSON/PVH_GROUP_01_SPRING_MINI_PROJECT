package org.example.pvh_group_01_spring_mini_project.service.impl;

import io.minio.*;
import lombok.SneakyThrows;
import org.example.pvh_group_01_spring_mini_project.exception.NotAllowedFile;
import org.example.pvh_group_01_spring_mini_project.models.entity.FileMetaData;
import org.example.pvh_group_01_spring_mini_project.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final MinioClient minioClient;

    public FileServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Value("${minio.bucket-name}")
    private String bucketName;

    private boolean isAllowedExtension(String filename) {
        return  filename.endsWith(".png")||
                filename.endsWith(".svg") ||
                filename.endsWith(".jpg") ||
                filename.endsWith(".jpeg")||
                filename.endsWith(".gif");
    }

    @SneakyThrows
    @Override
    public FileMetaData uploadFile(MultipartFile multipartFile) {

        //validate file extension
        String fileExtension = multipartFile.getOriginalFilename();
        if(fileExtension == null|| !isAllowedExtension(fileExtension)){
            throw new NotAllowedFile("Profile image must be a valid image URL ending with .png, .svg, .jpg, .jpeg, or .gif");
        }

        //  if there are no bucket then creak bucket
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        //make a file name
        String filename = multipartFile.getOriginalFilename() + UUID.randomUUID();

        //put object into bucket
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(filename)
                .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                .contentType(multipartFile.getContentType())
                .build()
        );

        // make image url
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/files/preview-file/"+filename).toUriString();

        //return File into
        return FileMetaData.builder()
                .fileName(filename)
                .fileSize(multipartFile.getSize())
                .fileType(multipartFile.getContentType())
                .fileUrl(fileUrl)
                .build();
    }

    @SneakyThrows
    @Override
    public InputStream getFileByName(String fileName) {
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build()) ;
    }
}
