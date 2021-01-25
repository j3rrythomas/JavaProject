/*************************************************************
This is the main class file The program excecution begins here
**************************************************************/
import java.security.GeneralSecurityException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.HashMap;

class Main{
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
        Main driveFiles = new Main();

        /*for(OnlineClassFile file:files){
                 System.out.printf("%s (%s) Time : %s Type: %s  \nClick here To View File: %s \nBelongs To parent: %s\n\n",
                     file.getName(),
                     file.getId(),
                     file.getDateCreated().toString(),
                     file.getType(),
                     file.getWebViewLink(),
                     file.getParentName() );
                 }*/

        new OpenWindow(driveFiles);

        }
}
