package de.javaGeneral.SPI;

public class PushNotificationServiceProviders implements MessageServiceProvider{
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending Push Notification with Message = " + message);
    }
}
