import java.util.concurrent.locks.Lock;

public class Ork implements Runnable {

    private final String name;
    private final long startTime;

    private final int seat;
    private final Table table;

    private final Lock lock;

    private boolean leftDaggerGrabbed;
    private boolean rightDaggerGrabbed;

    private boolean thirsty;

    public Ork(String name, long startTime, int seat, Table table, Lock lock) {
        this.name = name;
        this.startTime = startTime;
        this.seat = seat;
        this.table = table;
        this.lock = lock;
        leftDaggerGrabbed=false;
        rightDaggerGrabbed=false;
        thirsty=true;
    }

    @Override
    public void run() {
        while(true) {
            if(thirsty){
                try {
                    drinking();
                    thirsty=false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            lock.lock();
            try {
                grabLeftDagger();
                grabRightDagger();
            }finally {
                if(!leftDaggerGrabbed||!rightDaggerGrabbed){
                    if (leftDaggerGrabbed) {
                        releaseLeftDagger();
                    }else if (rightDaggerGrabbed) {
                        releaseRightDagger();
                    }
                }
                lock.unlock();
            }

            try {
                feasting();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void grabLeftDagger(){
        leftDaggerGrabbed = table.getLeftDagger(seat).grab();
    }

    private void grabRightDagger(){
        rightDaggerGrabbed = table.getRightDagger(seat).grab();
    }

    private void releaseLeftDagger(){
        leftDaggerGrabbed = false;
        table.getLeftDagger(seat).release();
    }

    private void releaseRightDagger(){
        rightDaggerGrabbed = false;
        table.getRightDagger(seat).release();
    }

    private void drinking() throws InterruptedException {
        System.out.println("("+name+") "+(System.currentTimeMillis()-startTime)+" ms: drinking");
        sleepRandomTime(2000,6000);
    }

    private void feasting() throws InterruptedException {
        if(leftDaggerGrabbed && rightDaggerGrabbed){
            System.out.println("("+name+") "+(System.currentTimeMillis()-startTime)+" ms: feasting");
            sleepRandomTime(8000,12000);
            thirsty=true;
            releaseLeftDagger();
            releaseRightDagger();
        }
    }

    private void sleepRandomTime(long min, long max) throws InterruptedException {
        Thread.sleep((long)(Math.random()*(max-min)+min));
    }
}
