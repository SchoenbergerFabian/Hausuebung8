import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dagger {

    private boolean grabbed;

    public Dagger() {
        grabbed = false;
    }

    public boolean grab(){
        if (grabbed) {
            return false;
        } else {
            grabbed = true;
            return true;
        }
    }

    public void release(){
        grabbed = false;
    }
}
