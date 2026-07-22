import java.util.*;

//Receiver
class Light {
    public void on() {
        System.out.println("Light turned ON");
    }

    public void off() {
        System.out.println("Light turned OFF");
    }
}

class AC {
    public void on(){
        System.out.println("AC turned ON");
    }

    public void off(){
        System.out.println("AC turned OFF");
    }
}

// Commands
interface Command {
    void execute();
    void undo();
}

//Concrete commands
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo(){
        light.off();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light){
        this.light = light;
    }

    public void execute(){
        light.off();
    }

    public void undo(){
        light.on();
    }
}

class ACOnCommand implements Command {
    private AC ac;

    public ACOnCommand(AC ac) {
        this.ac = ac;
    }

    public void execute() {
        ac.on();
    }

    public void undo(){
        ac.off();
    }
}

class ACOffCommand implements Command {
    private AC ac;

    public ACOffCommand(AC ac) {
        this.ac = ac;
    }

    public void execute() {
        ac.off();
    }

    public void undo() {
        ac.on();
    }
 }

//Invoker
//4 Commands
class RemoteControl {
    private Command[] buttons = new Command[4];
    private Stack<Command> commandHistory = new Stack<>();
    
    public void setCommand(int slot, Command command) {
        buttons[slot] = command;
    }

    public void pressButton(int slot) {
        if(buttons[slot] != null) {
            buttons[slot].execute();
        }else {
            System.out.println("No command assigned to slot " + slot);
        }
    }

    public void pressUndo(){
        if(!commandHistory.isEmpty()){
            commandHistory.pop().undo();
        }else{
            System.out.println("No commands to undo.");
        }
    }
}

// class NaiveRemoteControl {
//     private Light light;
//     private AC ac;
//     private String lastAction = "";

//     public NaiveRemoteControl(Light light, AC ac){
//         this.light = light;
//         this.ac = ac;
//     }

//     public void pressLightOn() {
//         light.on();
//         lastAction = "LIGHT_ON";
//     }

//     public void pressLightOff() {
//         light.off();
//         lastAction = "LIGHT_OFF";
//     }

//     public void PressACOn(){
//         ac.on();
//         lastAction = "AC_ON";
//     }

//     public void pressACOff(){
//         ac.off();
//         lastAction = "AC_OFF";
//     }

//     public void pressUndo() {
//         switch(lastAction) {
//             case "LIGHT_ON": light.off(); lastAction = "LIGHT_OFF"; break;
//             case "LIGHT_OFF": light.on(); lastAction = "LIGHT_ON"; break;
//             case "AC_ON": ac.off(); lastAction = "AC_OFF"; break;
//             case "AC_OFF": ac.on(); lastAction = "AC_ON"; break;
//             default: System.out.println("No action to undo.");
//         }
//     }
// }

public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        AC ac = new AC();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command acOn = new ACOnCommand(ac);
        Command acOff = new ACOffCommand(ac);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(0, lightOn);
        remote.setCommand(1, lightOff);
        remote.setCommand(2, acOn);
        remote.setCommand(3, acOff);

        remote.pressButton(0);  // Light ON
        remote.pressButton(2);  // AC ON
        remote.pressButton(1); // Light OFF
        remote.pressUndo();   // Undo Light OFF => Light ON
        remote.pressUndo();   // Undo AC ON => AC OFF
    }
}