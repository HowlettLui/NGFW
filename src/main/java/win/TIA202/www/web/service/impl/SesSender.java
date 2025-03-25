package win.TIA202.www.web.service.impl;

import org.springframework.stereotype.Service;

// import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
// import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
// import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;

@Service
public class SesSender {
    private static final Region REGION = Region.AP_NORTHEAST_1;
    // private static final String ACCESS = "";
    // private static final String SECRET = "";
    private final SesClient sesClient = SesClient.builder()
                                            .region(REGION)
                                            // .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(ACCESS, SECRET)))
                                            .build();
    private String Sender = "no-reply@tia202g1.win";

    public void send(String recipient, String subject, String bodyString) {
        Destination destination = Destination.builder().toAddresses(recipient).build();
        Content content = Content.builder().data(bodyString).build();
        Content subjectContent = Content.builder().data(subject).build();
        Body body = Body.builder().text(content).build();
        Message msg = Message.builder().subject(subjectContent).body(body).build();

        SendEmailRequest request = SendEmailRequest.builder()
                .source(Sender)
                .destination(destination)
                .message(msg)
                .build();
        
        try {
            sesClient.sendEmail(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}