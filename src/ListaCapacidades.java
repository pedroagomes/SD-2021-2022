import java.util.concurrent.locks.*;

public class ListaCapacidades {

    private ReentrantLock lock;
    private int[] capacidade;

    public ListaCapacidades(){
        this.lock = new ReentrantLock();
        this.capacidade = new int[30];
    }
}