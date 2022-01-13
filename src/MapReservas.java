import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class MapReservas {
    private HashMap<Integer,Reserva> reservas;
    private int contador;
    private ReentrantLock lock;
}
