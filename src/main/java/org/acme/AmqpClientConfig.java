
package org.acme;

import io.vertx.amqp.AmqpClientOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;


@ApplicationScoped
public class AmqpClientConfig {
    @Produces
    @Named("named-options")
    public AmqpClientOptions getNamedOptions()
    {
        return new AmqpClientOptions()
                .setHost("HOST-ADDRESS")
                .setPort(443)
                .setSsl(true)
                .setUsername("USERNAME")
                .setPassword("PASSWORD")
                .setTrustAll(true)
                .setHostnameVerificationAlgorithm("")
                .setConnectTimeout(30000)
                .setReconnectInterval(1000)
                .setReconnectAttempts(100);
    }
}
