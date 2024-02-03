public class CPU {
    private int totalExecTime;
    private int switchTime;

    public CPU(int t, int s) {
        this.totalExecTime = t;
        this.switchTime = s;
    }

    public int getTotalExecTime() {
        return this.totalExecTime;
    }

    public double calcUtilization(int numSwitch) {
        double utilize = 1 - ((this.switchTime * numSwitch) / (double) this.totalExecTime);
        return utilize;
    }

    public double calcThroughput(int numProcess) {
        double throughput = this.totalExecTime / (double) numProcess;
        return throughput;
    }
    
    public void executeProcessBy1(Process p) {
        int currentBurst = p.getBurst();
        p.setBurst(currentBurst - 1);
    }
}
