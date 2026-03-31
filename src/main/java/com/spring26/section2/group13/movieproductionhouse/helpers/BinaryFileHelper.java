package com.spring26.section2.group13.movieproductionhouse.helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileHelper {

    public static void saveObject(File file, Object obj) {
        try {
            boolean fileExists = file.exists();
            FileOutputStream fos = new FileOutputStream(file, true);

            ObjectOutputStream oos = fileExists && file.length() > 0
                    ? new AppendableObjectOutputStream(fos)
                    : new ObjectOutputStream(fos);

            oos.writeObject(obj);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> ArrayList<T> readAllObjects(File file) {
        ArrayList<T> objects = new ArrayList<>();

        if (!file.exists()) return objects;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    @SuppressWarnings("unchecked")
                    T obj = (T) ois.readObject();
                    objects.add(obj);
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objects;
    }

    public static <T> void writeAllObjects(File file, List<T> objects) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (T obj : objects) {
                oos.writeObject(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
        }
    }
}