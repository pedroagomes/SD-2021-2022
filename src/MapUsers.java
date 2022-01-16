import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class MapUsers {
    private HashMap<String,String> users;

    public MapUsers(String filename){
        this.users = new HashMap<>();

        try {
            File usrPwd = new File(filename);
            Scanner scanner = new Scanner(usrPwd);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] keyValue = data.split("-");
                users.put(keyValue[0],keyValue[1]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String get(String key){
        return users.get(key);
    }
}
