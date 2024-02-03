public class Clock {
    private int currentTime;

    public Clock() {
        this.currentTime = 0;
    }

    public int getCurrentTime() {
        return this.currentTime;
    }

    public void incrementClock() {
        this.currentTime++;
    }

    public void incrementClockBy(int t) {
        this.currentTime += t;
    }
}
