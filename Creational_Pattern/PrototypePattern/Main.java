import java.util.*;

interface EmailTemplate extends Cloneable{
    EmailTemplate clone(); // Prototype method to create a copy of the template
    void setContent(String content);
    void send(String to);
    String getContent();
}

class WelcomeEmail implements EmailTemplate{
    private String subject;
    private String content;

    public WelcomeEmail(){
        this.subject = "Welcome to TUF+!";
        this.content = "Hi there! Thanks for joining us.";
    }

    @Override
    public WelcomeEmail clone(){
        try{
            return (WelcomeEmail) super.clone();
        } catch (CloneNotSupportedException e){
            throw new RuntimeException("Clone failed", e);
        }
    }

    @Override
    public String getContent(){
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void send(String to) {
        // Implementation for sending email
        System.out.println("Sending to " + to + ": [" + subject + "] " + content);
    }
}

class EmailTemplateRegistry{
    private static final Map<String, EmailTemplate> templates = new HashMap<>();
    static{
        templates.put("welcome", new WelcomeEmail());
        //Add more templates like "discount", "feature-update" etc.
    }
    public static EmailTemplate getTemplate(String type){
        return templates.get(type).clone(); // clone avoids modifying original template
    }
}

public class Main{
    public static void main(String[] args){
         // WelcomeEmail welcomeEmail = new WelcomeEmail();
        // welcomeEmail.setContent("Hello! We're excited to TUF+.");
        // WelcomeEmail welcomeEmailTufPlus = new WelcomeEmail();
        // welcomeEmailTufPlus.setContent("Welcome to TUF+!");

        EmailTemplate welcomeEmail = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail.setContent("Hello! We're excited to TUF+.");

        System.out.println(welcomeEmail.getContent());

        EmailTemplate welcomeEmail2 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail2.setContent("Welcome to TUF+!");

        System.out.println(welcomeEmail.getContent());
        System.out.println(welcomeEmail2.getContent());
    }
}