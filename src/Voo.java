import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Voo {

    private String origem;
    private String destino;
    private int capacidade_Maxima;

    public Voo(String origem, String destino, int capMax){
        this.origem = origem;
        this.destino = destino;
        this.capacidade_Maxima = capMax;
    }

    public void serialize(DataOutputStream out) throws IOException {
        out.writeUTF(this.origem);
        out.writeUTF(this.destino);
        out.writeInt(this.capacidade_Maxima);
    }
    public static Voo deserialize(DataInputStream in) throws IOException {
        String origem = in.readUTF();
        String destino = in.readUTF();
        int capacidade = in.readInt();

        Voo voo = new Voo(origem,destino,capacidade);
        return voo;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.origem+" -> "+this.destino+" - "+this.capacidade_Maxima);

        return str.toString();
    }

    public String getOrigem() {
        return this.origem;
    }

    public String getDestino() {
        return this.destino;
    }
}