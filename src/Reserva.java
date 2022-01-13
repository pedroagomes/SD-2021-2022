import java.time.*;
import java.util.List;

public class Reserva {

    private List<String> aeroportos;
    private LocalDate dia;

    public Reserva(List<String> aeroportos, LocalDate dia){
        this.aeroportos = aeroportos;
        this.dia = dia;
    }
}