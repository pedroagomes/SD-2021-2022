public class Voo {

    private String origem;
    private String destino;
    private int capacidade_Maxima;
    private ListaCapacidades capacidade;

    public Voo(String origem, String destino, int capMax){
        this.origem = origem;
        this.destino = destino;
        this.capacidade_Maxima = capMax;
        this.capacidade = new ListaCapacidades();
    }

}