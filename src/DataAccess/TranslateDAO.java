package DataAccess;

import common.Library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TranslateDAO {

    private static TranslateDAO instance = null;
    Library l;

    public TranslateDAO() {
        l = new Library();
    }

    public static TranslateDAO Instance() {
        if (instance == null) {
            synchronized (TranslateDAO.class) {
                if (instance == null) {
                    instance = new TranslateDAO();
                }
            }
        }
        return instance;
    }

    public void addNewWord(HashMap<String, String> hm) {
        String english = l.inputString("Enter Enlish: ");
        if (!checkKeywordExist(hm, english)) {
            if (!l.checkInputYN("Do you want to replace(Y/N)? ")) {
                return;
            }
        }
        String vietnam = l.inputString("Enter Vietnamese: ");
        hm.put(english, vietnam);
        writeFile("Dictionary.txt", hm);
        System.err.println("Add successful.");
    }

    public void readFile(HashMap<String, String> hm) {
        try {
            File file = new File("Dictionary.txt");
            if (file.createNewFile()) {
                return;
            } else {
                try {
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] word = line.split("=");
                        hm.put(word[0], word[1]);
                        if (word[0].contains("c")) {
                            System.out.println("Fuck you");
                            System.exit(0);
                        }
                    }
                    bufferedReader.close();
                    fileReader.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String filename, HashMap<String, String> hm) {
        try {
            FileWriter fileWriter = new FileWriter(filename, false);
            for (HashMap.Entry<String, String> entry : hm.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                fileWriter.write(key + "=" + value + "\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteWord(HashMap<String, String> hm) {
        String english = l.inputString("Enter english: ");
        if (!checkKeywordExist(hm, english)) {
            hm.remove(english);
            writeFile("Dictionary.txt", hm);
            System.err.println("Delete successful.");
        } else {
            System.err.println("Not found in data");
        }

    }

    public void translate(HashMap<String, String> hm) {
        String english = l.inputString("Enter english: ");
        Set<Map.Entry<String, String>> entries = hm.entrySet();
        for (Map.Entry entry : entries) {
            if (entry.getKey().equals(english)) {
                System.out.println("Vietnamese: " + entry.getValue());
                return;
            }
        }
        System.err.println("Not found in data");
    }

    public boolean checkKeywordExist(HashMap<String, String> hm, String english) {
        Set set = hm.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (english.equalsIgnoreCase(mentry.getKey().toString())) {
                return false;
            }
        }
        return true;
    }

}
