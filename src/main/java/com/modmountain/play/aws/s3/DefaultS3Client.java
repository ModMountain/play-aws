package com.modmountain.play.aws.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.Application;
import play.Play;

public class DefaultS3Client implements S3Client {
    private final Logger LOGGER = LoggerFactory.getLogger(S3Client.class);

    public static final String AWS_S3_BUCKET = "aws.s3.bucket";
    public static final String AWS_ACCESS_KEY = "aws.accessKey";
    public static final String AWS_SECRET_KEY = "aws.secretKey";

    private final AmazonS3 amazonS3;
    private final String bucket;

    public DefaultS3Client() {
        final Application application = Play.application();
        final String accessKey = application.configuration().getString(AWS_ACCESS_KEY);
        final String secretKey = application.configuration().getString(AWS_SECRET_KEY);
        bucket = application.configuration().getString(AWS_S3_BUCKET);

        final AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        amazonS3 = new AmazonS3Client(awsCredentials);
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
