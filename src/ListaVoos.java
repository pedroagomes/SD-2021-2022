import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.*;

public class ListaVoos {

    private ReentrantLock lock;
    private List<Voo> voos;

    public ListaVoos(){
        this.lock = new ReentrantLock();
        this.voos = new ArrayList<>();
    }
}