package com.modmountain.play.aws.ses;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import play.Configuration;
import play.libs.F;

import javax.inject.Inject;

public class DefaultSESClient implements SESClient {

    private final AmazonSimpleEmailServiceClient client;

    @Inject
    public DefaultSESClient(Configuration configuration) {
        this.client = new AmazonSimpleEmailServiceClient();
        Region region = Region.getRegion(Regions.fromName(configuration.getString("aws.ses.region")));
        this.client.setRegion(region);
    }

    @Override
    public AmazonSimpleEmailServiceClient getClient() {
        return client;
    }

    @Override
    public F.Promise<SendEmailResult> sendEmail(String from, String[] to, String subject, String body) {
        return F.Promise.promise(() -> {
            // Construct an object to contain the recipient address.
            Destination destination = new Destination().withToAddresses(to);

            // Create the subject and body of the message.
            Content contentSubject = new Content().withData(subject);
            Content contentBody = new Content().withData(body);
            Body emailBody = new Body().withText(contentBody);

            // Create a message with the specified subject and body.
            Message message = new Message().withSubject(contentSubject).withBody(emailBody);

            // Assemble the email.
            SendEmailRequest request = new SendEmailRequest().withSource(from).withDestination(destination).withMessage(message);
            return client.sendEmail(request);
        });
    }
}
