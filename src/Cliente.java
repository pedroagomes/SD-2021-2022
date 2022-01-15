import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args){
        try {
            Socket socket = new Socket("localhost",12345);
            DataOutputStream outStrm = new DataOutputStream(socket.getOutputStream());
            DataInputStream inStrm = new DataInputStream(socket.getInputStream());

            communicating(outStrm, inStrm);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void communicating(DataOutputStream outStrm, DataInputStream inStrm) throws IOException {
        Scanner input = new Scanner(System.in);
        String info;
        String[] received;
        Boolean communication = true;

        while(communication){
            received = inStrm.readUTF().split(";");

            if (!received[0].equals("0")) {
                if (!received[0].equals("LogOff")) {
                    System.out.println(received[0]);

                    info = input.nextLine();

                    outStrm.writeUTF(info);
                }
                else {
                    communication = false;
                }
            }
            else {
                System.out.println(received[1]);
            }
        }
    }
}
