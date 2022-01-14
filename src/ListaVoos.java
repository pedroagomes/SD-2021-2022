import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class ListaVoos {

    private ReentrantReadWriteLock lock;
    private ArrayList<Voo> voos;

    public ListaVoos(){
        this.lock = new ReentrantReadWriteLock();
        this.voos = new ArrayList<>();
    }

    public boolean add(Voo voo){
        try{
            lock.writeLock().lock();
            return this.voos.add(voo);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void serialize(DataOutputStream out) throws IOException {
        try {
            lock.readLock().lock();
            int size = voos.size();

            out.writeInt(size);

            for(int i = 0; i < size; i++)
                voos.get(i).serialize(out);

        } finally {
            lock.readLock().unlock();
        }
    }

    public static ListaVoos deserialize(DataInputStream in) throws IOException {
        int size = in.readInt();
        ListaVoos lVoos = new ListaVoos();

        for(int i = 0; i < size; i++)
            lVoos.add(Voo.deserialize(in));

        return lVoos;
    }
}