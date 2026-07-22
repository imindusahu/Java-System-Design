import java.util.*;

interface MatchingStrategy {
    void match(String riderLocation);
}

class NearestDriverStrategy implements MatchingStrategy {
    @Override 
    public void match(String riderLocation){
        System.out.println("Matching with the nearest available driver to " + riderLocation);
    }
}

class AirportQueueStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation){
        System.out.println("Matching using FIFO airport queue for " + riderLocation);
    }
}

class SurgePriorityStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching rider using surge pricing priority near " + riderLocation);
    }
}

class RideMatchingService {
    private MatchingStrategy strategy;

    public RideMatchingService(MatchingStrategy strategy){
        this.strategy = strategy;
    }

    public void setStrategy(MatchingStrategy strategy){
        this.strategy = strategy;
    }

    public void matchRider(String location) {
        strategy.match(location);
    }
}

public class Main {
    public static void main(String[] args){
        RideMatchingService rideMatchingService = new RideMatchingService(new AirportQueueStrategy());
        rideMatchingService.matchRider("hjrfhjd");

        RideMatchingService rideMatchingService2 = new RideMatchingService(new NearestDriverStrategy());
        rideMatchingService2.matchRider("hjrfhjd");
    }
}