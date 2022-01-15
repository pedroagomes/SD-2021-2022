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
        this.capacidades = new MapCapacidades();
        this.contador = 0;
        this.lock = new ReentrantLock();
        this.diaActual = LocalDate.now();
    }

    public MapReservas(LocalDate diaInicial){
        this.reservas = new HashMap<>();
        this.capacidades = new MapCapacidades();
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

    public void novoVoo(String oriDest, int maxCapacidade){
        try{
            lock.lock();
            capacidades.add(oriDest, maxCapacidade);
        } finally {
            lock.unlock();
        }
    }

    //o ArrayList escalas tem de ser verificado antes de ser passado a este metodo, para ver se existem os voos
    public int fazerReserva(String user, ArrayList<String> escalas, LocalDate diaDe, LocalDate diaAte){
        try{
            lock.lock();
            if(diaDe.compareTo(diaAte) <= 0)
                if(diaAte.compareTo(this.diaActual) >= 0) {
                    if(diaDe.compareTo(this.diaActual) < 0)
                        diaDe = diaActual.plusDays(0);
                    while(diaDe.compareTo(diaAte) <= 0){
                        if(capacidades.verificaCapacidades(escalas, diaDe))
                            break;
                        diaDe = diaDe.plusDays(1);
                    }
                    if(diaDe.compareTo(diaAte) > 0) {
                        for (int i = 0; i < escalas.size() - 1; i++) {
                            String oriDest = escalas.get(i) + '-' + escalas.get(i + 1);
                            capacidades.get(oriDest).get(diaDe).incrementa();
                        }
                        reservas.put(contador, new Reserva(escalas, user, diaDe));
                        return contador++;
                    }
                }
            return -1;
        } finally {
            lock.unlock();
        }
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
                            String oriDest = aeroportos.get(i)+'-'+aeroportos.get(i+1);
                            capacidades.get(oriDest).get(reserva.getDia()).decrementa();
                        }
                    }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
