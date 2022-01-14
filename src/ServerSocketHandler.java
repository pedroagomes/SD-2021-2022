import java.io.*;
import java.net.Socket;

public class ServerSocketHandler implements Runnable {
    private Socket socket;
    private String user;
    public MapUsers userMap;

    public ServerSocketHandler(Socket socket, MapUsers usrMap){
        this.socket = socket;
        this.userMap = usrMap;
    }

    @Override
    public void run(){
        try {
            DataInputStream inStrm = new DataInputStream(socket.getInputStream());
            DataOutputStream outStrm = new DataOutputStream(socket.getOutputStream());

            boolean notAuth = true;

            while(notAuth){
                String[] input = new String[2];
                input[0] = inStrm.readUTF();
                input[1] = inStrm.readUTF();

                if(userMap.get(input[0]) != null && userMap.get(input[0]).equals(input[1])){
                    notAuth = false;
                    user = input[0];
                }

                outStrm.writeBoolean(notAuth);
                outStrm.flush();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }

}
