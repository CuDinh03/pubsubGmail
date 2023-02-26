package com.example.pubsubdemo1;

import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Pubsubdemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Pubsubdemo1Application.class, args);
    }
    public CommandLineRunner getCommandLineRunner (){
        return (args ) -> {
            String PROJECT_ID = ServiceOptions.getDefaultProjectId();
            String SUBSCRIPTION_ID = "sendmail";
            ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(PROJECT_ID, SUBSCRIPTION_ID);
            Log log = LogFactory.getLog(Pubsubdemo1Application.class);
            log.info(String.format("Project: %s", PROJECT_ID));


            Subscriber subscriber = null;

            try{
                subscriber = Subscriber.newBuilder(subscriptionName, new GMessageReceiver()).build();
                subscriber.startAsync().awaitRunning();
                subscriber.awaitTerminated();

            }catch (IllegalStateException illegal){
                illegal.printStackTrace();
            }
        };
    }
}
