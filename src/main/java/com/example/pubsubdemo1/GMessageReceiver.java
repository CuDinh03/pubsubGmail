package com.example.pubsubdemo1;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class GMessageReceiver implements MessageReceiver {
    private Log log = LogFactory.getLog(GMessageReceiver.class);
    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
        log.info("Message Details...");
        log.info("ID: " + pubsubMessage.getMessageId());
        log. info ("Data: " + pubsubMessage.getData().toStringUtf8()) ;
        //handle the message - persisting the message into db
        ackReplyConsumer.ack();
    }
}
