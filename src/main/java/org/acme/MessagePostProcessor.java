package org.acme;

import jakarta.inject.Singleton;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Singleton
public class MessagePostProcessor {
    private int count = 0;
    @Incoming("post-processor")
    public void sinkB(String response) {
        count++;
        System.out.println(count);
    }
}
