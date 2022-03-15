package de.javaGeneral.SPI;

public class EmailServiceProvider implements MessageServiceProvider{
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending Email with Message = " + message);
    }
}
