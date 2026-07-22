import java.util.*;

abstract class NotificationSender {
    // Final template method
    public final void send(String to, String rawMessage){
        rateLimitCheck(to);
        validateRecipient(to);
        String formatted = formatMessage(rawMessage);
        preSendAuditLog(to, formatted);

        //logic not common
        String composedMessage = composeMessage(formatted);
        sendMessage(to, composedMessage);

        //common
        postSendAnalytics(to);
    }

    //Common step 1
    private void rateLimitCheck(String to) {
        System.out.println("Checking rate limits for: " + to);
    }

    //Common step 2 
    private void validateRecipient(String to){
        System.out.println("Validating recipient: " + to);
    }

    //Common step 3
    private String formatMessage(String message){
        return message.trim();
    }

    //Common step 4
    private void preSendAuditLog(String to, String formatted) {
      System.out.println("Logging before send: " + formatted + " to " + to);  
    }

    //Hook for subclasses
    protected abstract String composeMessage(String formattedMessage);
    
    protected abstract void sendMessage(String to, String message);

    // Common step 5 (optional hook)
    protected void postSendAnalytics(String to) {
        System.out.println("Analytics updated for: " + to);
    }
}

class EmailNotification extends NotificationSender {
    @Override
    protected String composeMessage(String formattedMessage){
        return "<html><body><p>" + formattedMessage + "</p></body></html>";
    }

    @Override
    protected void sendMessage(String to, String message) {
        System.out.println("Sending Email to " + to + " with content:\n" + message);
    }
}

class SMSNotification extends NotificationSender {
    @Override
    protected String composeMessage(String formattedMessage) {
        return "[SMS] " + formattedMessage;
    }

    @Override
    protected void sendMessage(String to, String message){
        System.out.println("Sending SMS to " + to + " with message: " + message);
    }

    @Override
    protected void postSendAnalytics(String to) {
        System.out.println("Custom SMS analytics for: " + to);
    }
}


public class Main{
    public static void main(String[] args){
        NotificationSender emailSender = new EmailNotification();
        emailSender.send("john@example.com", "Welcome to TUF+! ");

        NotificationSender smsSender = new SMSNotification();
        smsSender.send("9876543210", "Your OTP is 4567.");
    }
}