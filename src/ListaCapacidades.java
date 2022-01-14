import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ListaCapacidades {
    private ArrayList<Capacidade> listaCapacidades;
    private ReentrantLock lock;

    public ListaCapacidades(){
        this.listaCapacidades = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public Capacidade get(LocalDate dia){
        try {
            lock.lock();
            int size = listaCapacidades.size();
            for(int i = 0; i < size; i++)
                if(listaCapacidades.get(i).getDia().equals(dia))
                    return listaCapacidades.get(i);
            return null;
        } finally {
            lock.unlock();
        }

    }
}
