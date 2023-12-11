# amqpbug

## Issue

This issue is about **consuming** messages from an AMQP queue. When there are **over 1000 messages** waiting to be consumed from an AMQ queue, quarkus is only getting the first 1000 messages and stopping there. In order to reproduce this, it is important to pre-load your AMQP queue with more than 1000 messages. 

When executing the same functionality in a .Net application, we are seeing the same issue. However, in .Net we are able to set the **Distrubition mode** attribute to **'copy'** when configuring the source AMQP address which then retrieves all deals from the queue, not just the first 1000.

Here is the AMQP source configuration code from our .Net application with the 'Distribution mode' attribute:
![image](https://github.com/ArvinUbhi/amqpbug/assets/117295982/bc67bae3-3814-4466-b599-d9be9fea5420)


## Reproducing the bug

1. Create an AMQP broker and set up a durable queue. Load this queue with over **1k** messages.
2. In the **AmqpClientConfig** file - change the host name, port, username and password values to those of your broker.
3. In **application properties** - change the targeted incoming address and queue to your new queue.
5. Run the project in dev mode!
6. Your application should begin to consume messages from this queue and print the number of messages recieved in the console. This number should stop at 1000.


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/amqpbug-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- Quarkus CXF ([guide](https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/dev/reference/extensions/quarkus-cxf.html)): Core capabilities for implementing SOAP clients and JAX-WS services
- SmallRye Reactive Messaging - AMQP Connector ([guide](https://quarkus.io/guides/amqp)): Connect to AMQP with Reactive Messaging

## Provided Code

### Reactive Messaging codestart

Use SmallRye Reactive Messaging

[Related Apache AMQP 1.0 guide section...](https://quarkus.io/guides/amqp)

