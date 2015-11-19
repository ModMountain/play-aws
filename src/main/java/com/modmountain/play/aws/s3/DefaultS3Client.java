package com.modmountain.play.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.Configuration;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DefaultS3Client implements S3Client {
    private final Logger LOGGER = LoggerFactory.getLogger(S3Client.class);

    public static final String AWS_S3_BUCKET = "aws.s3.bucket";

    private final AmazonS3 amazonS3;
    private final String bucket;

    @Inject
    public DefaultS3Client(final Configuration configuration) {
        bucket = configuration.getString(AWS_S3_BUCKET);

        amazonS3 = new AmazonS3Client();
        if (amazonS3.doesBucketExist(bucket)) {
            LOGGER.info("Using pre-existing S3 Bucket: " + bucket);
        } else {
            amazonS3.createBucket(bucket);
            LOGGER.warn("Creating new S3 Bucket: " + bucket);
        }
    }

    @Override
    public AmazonS3 getAmazonS3() {
        return amazonS3;
    }

    @Override
    public String getBucket() {
        return bucket;
    }

}
