import java.time.LocalDate;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class MapReservas {
    private HashMap<Integer,Reserva> reservas;
    private int contador;
    private ReentrantLock lock;
    private LocalDate diaActual;

    public MapReservas(){
        this.reservas = new HashMap<>();
        this.contador = 1;
        this.lock = new ReentrantLock();
        this.diaActual = LocalDate.now();
    }

    public MapReservas(LocalDate diaInicial){
        this.reservas = new HashMap<>();
        this.contador = 0;
        this.lock = new ReentrantLock();
        this.diaActual = diaInicial;
    }

    public LocalDate getDiaActual(){
        try{
            lock.lock();
            return diaActual;
        } finally {
            lock.unlock();
        }
    }

    public void encerrarDia(){

    }

    public int fazerReserva(){
        //retorna 0, se falhar
    }

    public boolean cancelarReserva(){

    }
}
