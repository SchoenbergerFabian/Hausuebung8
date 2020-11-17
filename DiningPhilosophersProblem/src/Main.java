import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final int NUMBER_OF_ORKS = 5;

    public static void main(String[] args) {
        Table table = new Table(5);
        ExecutorService exec = Executors.newFixedThreadPool(5);

        long startTime = System.currentTimeMillis();

        Lock grabberLock = new ReentrantLock();

        for(int seat = 0; seat<5; seat++){
            exec.execute(new Ork("Ork"+seat,startTime,seat,table,grabberLock));
        }
    }
}
