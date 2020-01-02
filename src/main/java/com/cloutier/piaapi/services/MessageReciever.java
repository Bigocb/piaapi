package com.cloutier.piaapi.services;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.beans.ExceptionListener;

public class MessageReciever {
    public static abstract class HelloWorldConsumer implements Runnable, ExceptionListener {
        public void run() {
            try {


                // Create a ConnectionFactory
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://ps603068.dreamhostps.com:61616?useInactivityMonitor=false");

                // Create a Connection
                Connection connection = connectionFactory.createConnection();
                connection.start();


                // Create a Session
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                // Create the destination (Topic or Queue)
                Destination destination = session.createTopic("topic/sentiment-p");

                // Create a MessageConsumer from the Session to the Topic or Queue
                MessageConsumer consumer = session.createConsumer(destination);

                // Wait for a message
                Message message = consumer.receive(1000);

                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    System.out.println("Received: " + text);
                } else {
                    System.out.println("Received: " + message);
                }

                consumer.close();
                session.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Caught: " + e);
                e.printStackTrace();
            }
        }
    }
    }
