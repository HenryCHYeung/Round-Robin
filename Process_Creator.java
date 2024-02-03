public class Process_Creator {
    private Process[] listProcess;

    public Process_Creator(String pi) {
        String lines[] = pi.split("\\r?\\n");
        this.listProcess = new Process[lines.length];
        for (int i = 0; i < lines.length; i++) {
            listProcess[i] = new Process(lines[i]);
        }
    }

    public Process[] getListProcess() {
        Process[] temp = new Process[this.listProcess.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new Process(this.listProcess[i]);
        }
        return temp;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < this.listProcess.length; i++) {
            result += "Process " + (i + 1) + ":\n" + listProcess[i] + "\n";
        }
        return result;
    }
}
