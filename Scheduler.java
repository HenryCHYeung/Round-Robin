import java.util.*;

public class Scheduler {
    private Process[] inputList;
    private ArrayList<Process> readyQueue;
    private int timeQuantum;

    public Scheduler(Process[] input, int t) {
        this.inputList = new Process[input.length];
        for (int i = 0; i < input.length; i++) {
            this.inputList[i] = new Process(input[i]);
        }
        this.readyQueue = new ArrayList<Process>();
        this.timeQuantum = t;
    }

    public ArrayList<Process> getReadyQueue() {
        ArrayList<Process> temp = new ArrayList<Process>();
        for (int i = 0; i < this.readyQueue.size(); i++) {
            temp.add(new Process(this.readyQueue.get(i)));
        }
        return temp;
    }

    public void printReadyQueue() {
        String result = "Current Ready Queue: [";
        if (this.readyQueue.size() > 0) {
            result += this.readyQueue.get(0).getPid();
        }
        if (this.readyQueue.size() > 1) {
            for (int i = 1; i < this.readyQueue.size(); i++) {
                result += ", " + this.readyQueue.get(i).getPid();
            }
        }
        result += "]";
        System.out.println(result);
    }

    public int currentSumOfBurst() {
        int currentSum = 0;
        for (int i = 0; i < this.inputList.length; i++) {
            currentSum += this.inputList[i].getBurst();
        }
        return currentSum;
    }

    public double avgTurnTime() {
        double currentSum = 0;
        for (int i = 0; i < this.inputList.length; i++) {
            currentSum += this.inputList[i].getTurnTime();
        }
        return currentSum / this.inputList.length;
    }

    public double avgWaitTime() {
        double currentSum = 0;
        for (int i = 0; i < this.inputList.length; i++) {
            currentSum += this.inputList[i].getWaitTime();
        }
        return currentSum / this.inputList.length;
    }

    public double avgResTime() {
        double currentSum = 0;
        for (int i = 0; i < this.inputList.length; i++) {
            currentSum += this.inputList[i].getResTime();
        }
        return currentSum / this.inputList.length;
    }

    public void checkProcessArrival(int time) {
        for (int i = 0; i < this.inputList.length; i++) {
            if (this.inputList[i].getArrive() == time) {
                this.readyQueue.add(this.inputList[i]);
                System.out.println("Process " + this.inputList[i].getPid() + " has arrived in ready queue.");
            }
        }
        this.printReadyQueue();
    }

    public void printInfo() {
        System.out.println("Current Running Process: " + this.readyQueue.get(0).getPid());
        System.out.println("Current Process Initial Burst Time: " + this.readyQueue.get(0).getInitialBurst());
        System.out.println("Current Process Burst Time: " + this.readyQueue.get(0).getBurst());
        System.out.println("Total Burst Time: " + this.currentSumOfBurst());
    }
    
    public void run() {
        int currentTimeQuantum = 0;
        int numSwitch = 0;
        Clock clock = new Clock();
        CPU cpu = new CPU(this.currentSumOfBurst(), 1);
        System.out.println("\nCurrent Time: " + clock.getCurrentTime());
        this.checkProcessArrival(clock.getCurrentTime());
        this.printInfo();
        while (this.currentSumOfBurst() > 0) {
            clock.incrementClock();
            currentTimeQuantum++;
            cpu.executeProcessBy1(this.readyQueue.get(0));
            System.out.println("\nCurrent Time: " + clock.getCurrentTime());
            this.checkProcessArrival(clock.getCurrentTime());
            this.printInfo();
            if (!this.readyQueue.get(0).getHasResponse()) {
                this.readyQueue.get(0).setHasResponse(true);
                this.readyQueue.get(0).calcResTime(clock.getCurrentTime());
            }
            if (this.readyQueue.get(0).getBurst() == 0) {
                this.readyQueue.get(0).setIsFinished(true);
                this.readyQueue.get(0).setCompleteTime(clock.getCurrentTime());
                this.readyQueue.get(0).calcTurnTime();
                this.readyQueue.get(0).calcWaitTime();
                this.readyQueue.remove(0);
                if (this.readyQueue.size() > 0) {
                    numSwitch++;
                    System.out.println("Process completed: a context switch has occurred.");
                    System.out.println("\nCurrent Time: " + clock.getCurrentTime());
                    this.printReadyQueue();
                    this.printInfo();
                } else {
                    System.out.println("\nAll processes have finished running.");
                    System.out.println("Number of context switches: " + numSwitch);
                    System.out.println("CPU Utilization: " + cpu.calcUtilization(numSwitch));
                    System.out.println("CPU Throughput: " + cpu.calcThroughput(this.inputList.length) + " per time unit");
                    System.out.println("Average Waiting Time: " + this.avgWaitTime());
                    System.out.println("Average Turnaround Time: " + this.avgTurnTime());
                    System.out.println("Average Response Time: " + this.avgResTime());
                }
                currentTimeQuantum = 0;
            }
            if (currentTimeQuantum == this.timeQuantum) {
                this.readyQueue.add(this.readyQueue.remove(0));
                numSwitch++;
                System.out.println("Time quantum ran out: a context switch has occurred.");
                System.out.println("\nCurrent Time: " + clock.getCurrentTime());
                this.printReadyQueue();
                this.printInfo();
                currentTimeQuantum = 0;
            }
        } 
    }
}
