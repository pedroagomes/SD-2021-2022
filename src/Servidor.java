import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static ListaVoos listaVoos = new ListaVoos();
    private static MapReservas reservaMap = new MapReservas();
    private static MapUsers userMap = new MapUsers("TextFiles\\usrList.txt");

    public static void main(String[] args){

        inicialiar();

        try {
            ServerSocket ssocket = new ServerSocket(12345);

            while(true){
                Socket socket = ssocket.accept();

                Thread thread = new Thread(new ServerSocketHandler(socket, reservaMap, userMap, listaVoos));
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void inicialiar() {
        listaVoos.add(new Voo("Lisboa","Porto",15));
        reservaMap.novoVoo("Lisboa-Porto",15);
        listaVoos.add(new Voo("Porto","Lisboa",1));
        reservaMap.novoVoo("Porto-Lisboa",1);
        listaVoos.add(new Voo("Lisboa","Paris",15));
        reservaMap.novoVoo("Lisboa-Paris",15);

    }


}
