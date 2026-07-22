import java.util.*;

class PaymentService{
    public void makePayment(String accountId, double amount){
        System.out.println("Payment of Rs. " + amount + " successful for account " + accountId);
    }
}

class SeatReservationService{
    public void reserveSeat(String movieId, String seatNumber){
        System.out.println("Seat " + seatNumber + " reserved for movie " + movieId);
    }
}

class NotificationService{
    public void sendBookingConfirmation(String userEmail){
        System.out.println("Booking confirmation sent to " + userEmail);
    }
}

class LoyaltyPointsService{
    public void addPoints(String accountId, int points){
        System.out.println(points + " loyalty points added to account " + accountId);
    }
}

class TicketService{
    public void generateTicket(String movieId, String seatNumber){
        System.out.println("Ticket generated for movie " + movieId + ", Seat: " + seatNumber);
    }
}

class MovieBookingFacade{
    private PaymentService paymentService;
    private SeatReservationService seatReservationService;
    private NotificationService notificationService;
    private LoyaltyPointsService loyaltyPointsService;
    private TicketService ticketService;


    public MovieBookingFacade() {
        this.paymentService = new PaymentService();
        this.seatReservationService = new SeatReservationService();
        this.notificationService = new NotificationService();
        this.loyaltyPointsService = new LoyaltyPointsService();
        this.ticketService = new TicketService();
    }

    public void bookMovieTicket(String accountId, String movieId, String seatNumber, String userEmail, double amount){
        paymentService.makePayment(accountId, amount);
        seatReservationService.reserveSeat(movieId, seatNumber);
        ticketService.generateTicket(movieId, seatNumber);
        loyaltyPointsService.addPoints(accountId, 50);
        notificationService.sendBookingConfirmation(userEmail);

        System.out.println("Movie ticket booking completed successfully!");
    }
}

public class Main{
    public static void main(String[] args){

        // Booking a movie ticket manually
        PaymentService paymentService = new PaymentService();
        paymentService.makePayment("user123", 500);

        SeatReservationService seatReservationService = new SeatReservationService();
        seatReservationService.reserveSeat("movie456", "A10");

        NotificationService notificationService = new NotificationService();
        notificationService.sendBookingConfirmation("user@example.com");

        LoyaltyPointsService loyaltyPointsService = new LoyaltyPointsService();
        loyaltyPointsService.addPoints("user123", 50);

        TicketService ticketService = new TicketService();
        ticketService.generateTicket("movie456", "A10");
    }
}