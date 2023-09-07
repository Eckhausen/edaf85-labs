import clock.AlarmClockEmulator;
import clock.io.*;
import clock.io.ClockInput.UserInput;

public class ClockMain {
    public static void main(String[] args) throws InterruptedException {
        AlarmClockEmulator emulator = new AlarmClockEmulator();

        ClockInput  in  = emulator.getInput();
        ClockOutput out = emulator.getOutput();
        Monitor monitor = new Monitor();
        ClockTime clock = new ClockTime(out, monitor);
        clock.start();

        //out.displayTime(15, 2, 37);   // arbitrary time: just an example

        while (true) {
            in.getSemaphore().acquire();
            UserInput userInput = in.getUserInput();
            Choice c = userInput.choice();
            int h = userInput.hours();
            int m = userInput.minutes();
            int s = userInput.seconds();
            if(c == Choice.SET_TIME){
                monitor.setTime(h,m,s);
            } else if (c == Choice.SET_ALARM) {
                monitor.setAlarmTime(h,m,s);
            } else if (c == Choice.TOGGLE_ALARM) {
                monitor.toggleAlarm();
                boolean isAlarmEnabled = monitor.isAlarmEnabled();
                out.setAlarmIndicator(isAlarmEnabled);
            }
            System.out.println("choice=" + c + " h=" + h + " m=" + m + " s=" + s);
        }
    }
}
