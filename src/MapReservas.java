import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class MapReservas {
    private HashMap<Integer,Reserva> reservas;
    private MapCapacidades capacidades;
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

    public String diaActual2String(){
        try{
            lock.lock();
            return diaActual.toString();
        } finally {
            lock.unlock();
        }
    }

    public void encerrarDia(){
        try{
            lock.lock();
            diaActual = diaActual.plusDays(1);
        } finally {
            lock.unlock();
        }
    }

    public int fazerReserva(ArrayList<String> escalas, LocalDate diaDe, LocalDate diaAte){
        //retorna 0, se falhar
        return 0;
    }

    public boolean cancelarReserva(int codigo, String user){
        try{
            lock.lock();
            Reserva reserva = reservas.get(codigo);
            if(reserva != null)
                if(reserva.getUser().equals(user))
                    if(diaActual.compareTo(reserva.getDia()) <= 0){
                        ArrayList<String> aeroportos = reserva.getAeroportos();
                        for(int i = 0; i < aeroportos.size()-1; i++){
                            String oriDest = aeroportos.get(i)+aeroportos.get(i+1);
                            capacidades.get(oriDest).get(reserva.getDia()).decrementa();
                        }
                    }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
