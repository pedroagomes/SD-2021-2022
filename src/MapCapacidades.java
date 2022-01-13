import java.util.HashMap;
import java.util.concurrent.locks.*;

public class MapCapacidades {

    private HashMap<String, ListaCapacidades> capacidades;
    private ReentrantLock lock;

    public MapCapacidades(){
        this.lock = new ReentrantLock();
    }
}