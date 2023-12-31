package lift;

public class Lift extends Thread{
    private int currentFloor, nextFloor, fromFloor;
    private LiftMonitor monitor;
    private LiftView liftView;
    public Lift(LiftView view, LiftMonitor monitor){
        this.liftView = view;
        this.monitor = monitor;
    }
    public void run(){
        while(true){
                currentFloor = monitor.getCurrentFloor();
                nextFloor = monitor.getNextFloor();
                monitor.waitForPax();
                monitor.allowPassengersInOut();
                liftView.moveLift(currentFloor, nextFloor);
                monitor.updateCurrentFloor(nextFloor);
        }
    }

}
