import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static MapUsers userMap = new MapUsers("TextFiles\\usrList.txt");

    public static void main(String[] args){

        try {
            ServerSocket ssocket = new ServerSocket(12345);

            while(true){
                Socket socket = ssocket.accept();

                Thread thread = new Thread(new ServerSocketHandler(socket,userMap));
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
