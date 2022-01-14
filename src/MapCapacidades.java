import java.util.HashMap;
import java.util.concurrent.locks.*;

public class MapCapacidades {

    private HashMap<String, ListaCapacidades> capacidades;
    private ReentrantLock lock;

    public MapCapacidades(){
        this.capacidades = new HashMap<>();
        this.lock = new ReentrantLock();
    }

    public ListaCapacidades get(String oriDest){
        try {
            lock.lock();
            return capacidades.get(oriDest);
        } finally {
            lock.unlock();
        }
    }
}