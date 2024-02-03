public class Process {
    private int pid, arrive, burst, initialBurst, completeTime, turnTime, waitTime, resTime;
    private boolean hasResponse, isFinished;

    public Process(String processInfo) {
        String[] info = processInfo.split(",");
        this.pid = Integer.parseInt(info[0]);
        this.arrive = Integer.parseInt(info[1]);
        this.burst = Integer.parseInt(info[2]);
        this.initialBurst = this.burst;
        this.completeTime = 0;
        this.turnTime = 0;
        this.waitTime = 0;
        this.resTime = 0;
        this.hasResponse = false;
        this.isFinished = false;
    }

    public Process(Process other) {
        this.pid = other.pid;
        this.arrive = other.arrive;
        this.burst = other.burst;
        this.initialBurst = other.initialBurst;
        this.completeTime = other.completeTime;
        this.turnTime = other.turnTime;
        this.waitTime = other.waitTime;
        this.resTime = other.resTime;
        this.hasResponse = other.hasResponse;
        this.isFinished = other.isFinished;
    }

    public int getPid() {
        return this.pid;
    }

    public void setPid(int p) {
        this.pid = p;
    }

    public int getArrive() {
        return this.arrive;
    }

    public void setArrive(int a) {
        this.arrive = a;
    }

    public int getBurst() {
        return this.burst;
    }

    public void setBurst(int b) {
        this.burst = b;
    }

    public int getInitialBurst() {
        return this.initialBurst;
    }

    public int getCompleteTime() {
        return this.completeTime;
    }

    public void setCompleteTime(int c) {
        this.completeTime = c;
    }

    public int getTurnTime() {
        return this.turnTime;
    }

    public void calcTurnTime() {
        this.turnTime = this.completeTime - this.arrive;
    }

    public int getWaitTime() {
        return this.waitTime;
    }

    public void calcWaitTime() {
        this.waitTime = this.turnTime - this.initialBurst;
    }
    
    public int getResTime() {
        return this.resTime;
    }

    public void calcResTime(int first) {
        this.resTime = first - this.arrive;
    }

    public boolean getHasResponse() {
        return this.hasResponse;
    }

    public void setHasResponse(boolean r) {
        this.hasResponse = r;
    }

    public boolean getIsFinished() {
        return this.isFinished;
    }

    public void setIsFinished(boolean i) {
        this.isFinished = i;
    }

    public String toString() {
        return "Process Info: \nProcess ID: "  + this.pid + "\nArrival Time: " + this.arrive + "\nBurst Time: " + this.burst
        + "\nCompletion Time: " + this.completeTime + "\nTurnaround Time: " + this.turnTime + "\nWaiting Time: " + this.waitTime
        + "\nResponse Time: " + this.resTime;
    }
}
