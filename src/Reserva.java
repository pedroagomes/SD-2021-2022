import java.time.*;
import java.util.ArrayList;

public class Reserva {

    private ArrayList<String> aeroportos;
    private String user;
    private LocalDate dia;

    public Reserva(ArrayList<String> aeroportos, String user,LocalDate dia){
        this.aeroportos = aeroportos;
        this.user = user;
        this.dia = dia;
    }

    public ArrayList<String > getAeroportos(){
        return  this.aeroportos;
    }

    public String getUser(){
        return this.user;
    }

    public LocalDate getDia(){
        return this.dia;
    }
}