import java.util.HashMap;
import java.util.concurrent.locks.*;

public class MapVoos {

    private ReentrantLock lock;
    private HashMap<String,Voo> voos;

    public MapVoos(){
        this.lock = new ReentrantLock();
        this.voos = new HashMap<>();
    }
}