import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args){
        String[] out = new String[2];
        out[0] = "joao";
        out[0] = "blah";

        Socket s = null;
        try {
            s = new Socket("localhost",12345);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            DataOutputStream outS = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
            outS.writeUTF(out[0]);
            outS.writeUTF(out[1]);
            outS.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
