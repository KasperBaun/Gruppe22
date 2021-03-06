package gruppe22.cdio.dal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DummyData {
    private List<User> users = new ArrayList<>();


    public DummyData() throws IOException, ClassNotFoundException {
        if (!(new File("Data.txt").exists())) {
            for (int i = 0; i < 10; i++) {
                List<String> tomListe = new ArrayList<String>();
                tomListe.add("Pharmacist");
                users.add(new User(i, "StandardUserName_" + i, "TEMP", 0000000000, "3t4nd4rd", tomListe));
            }
            List<String> adminListe = new ArrayList<String>();
            adminListe.add("Admin");
            users.add(new User(10, "Admin", "ADM", 0101010101, "hest", adminListe));
        }
        else {
            readFromDisk("Data.txt");
        }
    }
    public User getUser(int id){
        return users.get(id);
    }

    public void createUser(UserDTO user){
        while(users.size() > user.getUserId()){
            user.setUserId(user.getUserId() + 1);
        }
        users.add(new User(user.getUserId(), user.getUserName(), user.getIni(), user.getCpr(),
                user.getPassword(), user.getRoles()));
    }
    public void updateUser(UserDTO updatedUser){
        int id = updatedUser.getUserId();
        users.get(id).setIni(updatedUser.getIni());
        users.get(id).setUserName(updatedUser.getUserName());
        users.get(id).setCpr(updatedUser.getCpr());
        users.get(id).setPassword(updatedUser.getPassword());
        users.get(id).setRole(updatedUser.getRoles());
    }
    public void deleteUser(UserDTO user){
        int id = user.getUserId();
        users.remove(id);
    }

    public int getUserListSize() {
        return users.size();
    }

    public void saveToDisk(String fileName) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(this.users);
        out.flush();
        out.close();
    }

    public void readFromDisk(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        this.users = (List<User>)in.readObject();
    }
}
