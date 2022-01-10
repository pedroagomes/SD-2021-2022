import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ServerSocketHandler implements Runnable {
    private Socket socket;
    public HashMap<String,String> userMap;

    public ServerSocketHandler(Socket socket, HashMap<String,String> usrMap){
        this.socket = socket;
        this.userMap = usrMap;
    }

    @Override
    public void run(){
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            boolean notAuth = true;

            while(notAuth){
                System.out.println("Não Autenticado!");
                String[] input = new String[2];
                input[0] = in.readUTF();
                input[1] = in.readUTF();

                if(userMap.get(input[0]).equals(input[1])){
                    notAuth = false;
                }
            }
            System.out.println("Auntenticado!");
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }

}
