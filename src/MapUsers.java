import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class MapUsers {
    private HashMap<String,String> users;
    private ReentrantLock lock;

    public MapUsers(String filename){
        this.lock = new ReentrantLock();
        this.users = new HashMap<>();

        try {
            lock.lock();

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
        } finally {
            lock.unlock();
        }

    }

    public String get(String key){
        try{
            lock.lock();
            return users.get(key);
        } finally {
            lock.unlock();
        }

    }

}
