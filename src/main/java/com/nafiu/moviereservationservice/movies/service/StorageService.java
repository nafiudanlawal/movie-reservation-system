package com.nafiu.moviereservationservice.movies.service;

import org.apache.coyote.BadRequestException;
import org.apache.tika.Tika;
import org.apache.tika.mime.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;

@Service
public class StorageService {

    @Value("${aws.s3.region}")
    private String region = "us-east-1"; // use this as default

    @Value("${aws.s3.bucket:?missing aws.s3.bucket}")
    private String bucket;
    private final S3Client s3Client;

    public StorageService() {
        this.s3Client = S3Client.builder().region(Region.of(region)).build();
    }

    /**
     * @param multipartFile Multipart upload file
     * @return public access url for object
     * @throws java.io.IOException if a file is a wrong format
     */
    public String uploadMultipartFile(MultipartFile multipartFile) throws Exception {
        String key = this.generateMultipartFileRandomKey(multipartFile);
        PutObjectRequest request = PutObjectRequest.builder()
                .key(key)
                .bucket(this.bucket)
                .build();
        try{
            s3Client.putObject(request, RequestBody.fromInputStream(multipartFile.getInputStream(), multipartFile.getSize()));
            return this.getObjectPublicUrl(key);
        } catch (S3Exception exception){
            exception.printStackTrace();
            throw new BadRequestException("image upload failed");
        }
    }

    public String getObjectPublicUrl(String key) {
        return "https://%s.s3.%s.amazonaws.com/%s".formatted(this.bucket, this.region, key);
    }

    private String generateMultipartFileRandomKey(MultipartFile multipartFile) throws IOException {
        Tika tika = new Tika();
        MediaType mediaType = MediaType.parse(tika.detect(multipartFile.getInputStream()));
        return "%s_%s.%s".formatted(multipartFile.getName().split("\\W")[0], System.currentTimeMillis(), mediaType.getSubtype());
    }
}
