package familytree.storage;

import java.io.*;

public class SerializationStorage implements Writable {

    @Override
    public boolean save(Serializable serializable, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(serializable);
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении данных: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Object read(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при чтении данных: " + e.getMessage());
            return null;
        }
    }
}
