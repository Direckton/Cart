package Model;


import java.io.File;
import java.io.IOException;

public class Database {

    public Database(){}

    public File createFile(String path) {
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("Created new file " + file.getName());
            } else {
                System.out.println("File " + file.getName() + " already exists");
            }
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public File openFile(String path)
    {

        File file = new File(path);
        if(file.isFile())
        {
            return file;
        }
        else{
            return null;
        }

    }



    public void readFile(File file)
    {

    }
}
