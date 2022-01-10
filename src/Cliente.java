import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args){

        Socket s = null;
        try {
            s = new Socket("localhost",12345);

            DataOutputStream outS = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
            outS.writeUTF("joao");
            outS.writeUTF("blah");
            outS.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
