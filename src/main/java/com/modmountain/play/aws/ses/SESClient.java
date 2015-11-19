package com.modmountain.play.aws.ses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.google.inject.Inject;
import play.libs.F;

public interface SESClient {

    AmazonSimpleEmailServiceClient getClient();

    F.Promise<SendEmailResult> sendEmail(String from, String[] to, String subject, String body);
}
