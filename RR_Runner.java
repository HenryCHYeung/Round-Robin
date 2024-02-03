import java.io.*;
import java.util.*;

class RR_Runner {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(new File(args[0]));
        String processInfo = "";
        while (sc.hasNextLine()) {
            processInfo += sc.nextLine() + "\n";
        }
        sc.close();
        Process_Creator creator = new Process_Creator(processInfo);
        Scheduler s1 = new Scheduler(creator.getListProcess(), Integer.parseInt(args[1]));
        s1.run();
    }
}