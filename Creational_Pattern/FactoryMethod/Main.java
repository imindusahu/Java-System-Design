interface Logistics{
    void send();
}

class Road implements Logistics{
    @Override
    public void send() {
        System.out.println("Sending by road logic");
    }
}

class Air implements Logistics{
    @Override
    public void send(){
        System.out.println("Sending by air logic");
    }
}

// one more mode of transportation is added, we need to add a new class for it, but we don't need to change the code of LogisticsService class
class Train implements Logistics{
    @Override
    public void send(){
        System.out.println("Sending by train logic");
    }
}

class LogisticsFactory{
    public static Logistics getLogistics(String mode){
        if(mode.equalsIgnoreCase("Air")){
            return new Air();
        }else if(mode.equalsIgnoreCase("Road")){
            return new Road();
        }else if(mode.equalsIgnoreCase("Train")){
            return new Train();
        }
        throw new IllegalArgumentException("Unknown logistics mode: " + mode);
    }
}

// no matter how many new modes of transportation are added, we don't need to change the code of LogisticsService class, which follows open-closed principle
class LogisticsService{
    public void send(String mode){
        Logistics logistics = LogisticsFactory.getLogistics(mode);
        logistics.send();
    }
}

public class Main{
    public static void main(String[] args){
        LogisticsService service = new LogisticsService();
        service.send("Air");
        service.send("Road");
        service.send("Train");
    }
}