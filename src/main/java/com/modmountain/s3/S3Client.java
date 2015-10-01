package com.modmountain.s3;

import com.amazonaws.services.s3.AmazonS3;

public interface S3Client {
    AmazonS3 getAmazonS3();
    String getBucket();
}
