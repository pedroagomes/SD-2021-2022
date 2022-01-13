import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Servidor {
    private static HashMap<String,String> usrListFromFile(String filename){
        HashMap<String,String> usrMap = new HashMap<>();

        try {
            File usrPwd = new File(filename);
            Scanner scanner = new Scanner(usrPwd);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] keyValue = data.split("-");
                usrMap.put(keyValue[0],keyValue[1]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return usrMap;
    }

    public static void main(String[] args){
        HashMap<String,String> userMap = usrListFromFile("TextFiles\\usrList.txt");

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
