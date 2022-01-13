import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args){
        try {
            String userName,password;
            Scanner input = new Scanner(System.in);
            Socket socket = new Socket("localhost",12345);
            DataOutputStream outStrm = new DataOutputStream(socket.getOutputStream());
            DataInputStream inStrm = new DataInputStream(socket.getInputStream());
            boolean notAuth = true;

            while(notAuth)  {
                System.out.print("Username: ");
                userName = input.nextLine();
                System.out.print("Password: ");
                password = input.nextLine();

                outStrm.writeUTF(userName);
                outStrm.writeUTF(password);
                outStrm.flush();

                notAuth = inStrm.readBoolean();
                if(notAuth){
                    System.out.println("Auntenticação falhou!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
