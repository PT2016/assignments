package threadsAssign3;
import java.io.*;
import java.awt.Desktop;

public class EventsLog {
	private static final String FILE_PATH = "Log.txt";
    private static EventsLog instance;
    private static PrintWriter out;

    private EventsLog() {
        try {
            out = new PrintWriter(new FileWriter(FILE_PATH));
            out.println("Simulation started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initialize(){
        if (instance == null)
            instance = new EventsLog();
    }

    public static void log(String string){
        out.println(Simulator.getCurrentTime() + ": " + string);
    }

    public static void close(float averageWaitingTime, float serviceTime, int peakHour){
        out.println("Simulation ended");
        out.println(String.format("Average waiting time: %f; Service time: %f; Peak hour: %d",
                averageWaitingTime, serviceTime, peakHour));
        out.close();
        try {
            Desktop.getDesktop().open(new File(FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        instance = null;
    }

    public static void close(){
        out.close();
        instance = null;
    }
}
