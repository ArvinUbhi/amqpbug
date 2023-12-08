package org.acme;

import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class MessageProcessor {

    @Incoming("incoming-queue")
    @Outgoing("post-processor")
    @Broadcast
    public Uni<String> consume(Message<String> payload)
    {
        payload.ack();
        return Uni.createFrom().item("string");
    }
}