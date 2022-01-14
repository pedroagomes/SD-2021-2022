import java.time.LocalDate;
import java.util.concurrent.locks.ReentrantLock;

public class Capacidade {

    private LocalDate dia;
    private int capacidade;
    private ReentrantLock lock;

    public Capacidade(LocalDate dia, int capacidade, ReentrantLock lock) {
        this.dia = dia;
        this.capacidade = capacidade;
        this.lock = lock;
    }

    public LocalDate getDia(){
        try {
            lock.lock();
            return this.dia;
        } finally {
            lock.unlock();
        }
    }

    public void decrementa(){
        this.capacidade--;
    }
}
