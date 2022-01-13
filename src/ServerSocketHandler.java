import java.io.*;
import java.net.Socket;

public class ServerSocketHandler implements Runnable {
    private Socket socket;
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
