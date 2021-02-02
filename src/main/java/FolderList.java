import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.io.*;


public class FolderList implements java.io.Serializable
{
    private ArrayList<String> folderNameList;

    FolderList(ArrayList<String> folderNameList)
    {
        this.folderNameList = folderNameList;
    }

    public void saveFolderList() throws IOException
    {
        try
        {
          FileOutputStream fos=new FileOutputStream("build/resources/main/folderList.txt");
          ObjectOutputStream oos=new ObjectOutputStream(fos);
          oos.writeObject(this);
          for(String x : folderNameList){
              System.out.println("Added Folder "+x);
          }
          oos.close();
          fos.close();

        }
        catch(FileNotFoundException e)
        {
          File f1=new File("build/resources/main/folderList.txt");
          f1.createNewFile();
          System.out.println("File Created");
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
        OpenWindow.alertWindow("Folders Saved, you can close this window");
    }

    public static ArrayList<String> loadFolderList() throws IOException
    {
        FolderList object;
        try
        {
          FileInputStream fis=new FileInputStream("build/main/resources/folderList.txt");
          ObjectInputStream ois=new ObjectInputStream(fis);
          object=(FolderList)ois.readObject();
          System.out.println("Deserialization Successful");
          return object.folderNameList;

        }
        catch(FileNotFoundException e)
        {
            OpenWindow.alertWindow("No Existing class files found please update the database");
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
        return null;
    }
}
