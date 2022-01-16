import java.time.LocalDate;
import java.util.ArrayList;
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

    public void add(String oriDest, int maxCapacidade){
        try {
            lock.lock();
            capacidades.put(oriDest, new ListaCapacidades(maxCapacidade));
        } finally {
            lock.unlock();
        }
    }

    public boolean verificaCapacidades(ArrayList<String> escalas, LocalDate dia){
        try {
            lock.lock();
            for(int i = 0; i < escalas.size() - 1; i++){
                String oriDest = escalas.get(i) + '-' + escalas.get(i + 1);
                if(this.get(oriDest).get(dia).isFull())
                    return false;
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    public String toString(){
        try {
            lock.lock();
            StringBuilder strBldr = new StringBuilder();
            strBldr.append("{\n");
            for(String oriDest : capacidades.keySet()){
                strBldr.append(oriDest+":\n");
                strBldr.append(capacidades.get(oriDest).toString()+"\n");
            }
            strBldr.append("\n}");

            return strBldr.toString();
        } finally {
            lock.unlock();
        }
    }

    public boolean contains(String oriDest) {
        try {
            lock.lock();
            return this.capacidades.containsKey(oriDest);
        } finally {
            lock.unlock();
        }
    }
}