package com.modmountain.play.aws;

import com.modmountain.play.aws.s3.S3Client;
import com.modmountain.play.aws.s3.DefaultS3Client;
import play.api.Configuration;
import play.api.Environment;
import play.api.inject.Binding;
import play.api.inject.Module;
import scala.collection.Seq;

public class PlayAWSModule extends Module {
    @Override
    public Seq<Binding<?>> bindings(Environment environment, Configuration configuration) {
        return seq(bind(S3Client.class).to(DefaultS3Client.class).eagerly());
    }
}
