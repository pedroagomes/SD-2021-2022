import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ListaCapacidades {
    private ArrayList<Capacidade> listaCapacidades;
    private final int maxCapacidade;
    private ReentrantLock lock;

    public ListaCapacidades(int maxCapacidade){
        this.maxCapacidade = maxCapacidade;
        this.listaCapacidades = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public Capacidade get(LocalDate dia){
        try {
            lock.lock();
            int i;
            int size = listaCapacidades.size();
            for(i = 0; i < size; i++)
                if(listaCapacidades.get(i).getDia().equals(dia))
                    return listaCapacidades.get(i);
            add(dia);
            // verificar se quando não existe registo para o dia, está a criar e a devolver o correcto
            return listaCapacidades.get(i);
        } finally {
            lock.unlock();
        }
    }

    public void add(LocalDate dia){
        try {
            lock.lock();
            listaCapacidades.add(new Capacidade(dia,0, this.maxCapacidade));
        } finally {
            lock.unlock();
        }
    }

    public String toString(){
        try {
            lock.unlock();
            StringBuilder strBldr = new StringBuilder();
            strBldr.append("{"+this.maxCapacidade+";");
            for(Capacidade c : this.listaCapacidades)
                strBldr.append("\n\t"+c.toString());
            strBldr.append("\n}");

            return strBldr.toString();
        } finally {
            lock.unlock();
        }
    }
}
