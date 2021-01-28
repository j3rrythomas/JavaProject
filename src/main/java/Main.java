/*************************************************************
This is the main class file The program excecution begins here
**************************************************************/
import java.security.GeneralSecurityException;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.HashMap;

class Main implements java.io.Serializable{
    List<OnlineClassFile> files;
    HashMap <String, String> folders;


    Main() throws IOException, GeneralSecurityException
    {
        files = StartDrive.getFiles();
        folders = new HashMap<String,String>();

        for (OnlineClassFile file : files)
        {
            if(!folders.containsKey(file.getParentId())){
                folders.put(file.getParentId(),file.getParentName());
            }
        }
    }

    public List<OnlineClassFile> getFileList(){ return files; }
    public HashMap <String, String> getFolderList(){return folders;}

    public static void main(String[] args) throws IOException,GeneralSecurityException{
        //Files contains all the files which are placed in folders in the Google Drive

        //Function which upates the Database
        //JDBC.updateDatabase();

        //Function which shows the showDatabase
        //JDBC.showDatabase();

        /*for(OnlineClassFile file:files){
                 System.out.printf("%s (%s) Time : %s Type: %s  \nClick here To View File: %s \nBelongs To parent: %s\n\n",
                     file.getName(),
                     file.getId(),
                     file.getDateCreated().toString(),
                     file.getType(),
                     file.getWebViewLink(),
                     file.getParentName() );
                 }*/

        new OpenWindow();

        }

      static void serializeData(Main object)throws IOException,GeneralSecurityException
      {
        try
        {
          FileOutputStream fos=new FileOutputStream("src/main/resources/data.txt");
          ObjectOutputStream oos=new ObjectOutputStream(fos);
          oos.writeObject(object);
          oos.close();
          fos.close();
         OpenWindow.alertWindow("Serialization Successful");
        }
        catch(FileNotFoundException e)
        {
          File f1=new File("data.txt");
          f1.createNewFile();
          System.out.println("File Created");
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
      }

      static Main deserializeData()throws IOException,GeneralSecurityException
      {
        Main object=null;
        try
        {
          FileInputStream fis=new FileInputStream("src/main/resources/data.txt");
          ObjectInputStream ois=new ObjectInputStream(fis);
          object=(Main)ois.readObject();
          // System.out.println(object.files);
          System.out.println("Deserialization Successful");

        }
        catch(FileNotFoundException e)
        {
            OpenWindow.alertWindow("No Existing class files found please update the database");
          //System.out.println("Invalid file name");
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
        return object;
      }
}
