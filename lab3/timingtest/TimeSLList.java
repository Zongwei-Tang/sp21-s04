package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> time = new AList<>();
        AList<Integer> op = new AList<>();
        int N = 1000;
        while (N <= 128000){
            Ns.addLast(N);
            SLList<Integer> truelist = new SLList<>();
            for (int j = 0; j < N; j++){
                truelist.addLast(100);
            }
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < 10000; i++){
                truelist.getLast();
            }
            time.addLast(sw.elapsedTime());
            op.addLast(10000);
            N *= 2;
        }
        printTimingTable(Ns, time, op);
    }

}
