/*************************************************************
This is the main class file The program excecution begins here
**************************************************************/
import java.security.GeneralSecurityException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

class Main{
    public static void main(String[] args) throws IOException,GeneralSecurityException{
        //Files contains all the files which are placed in folders in the Google Drive
        try{
            List<OnlineClassFile> files = StartDrive.getFiles();

            for(OnlineClassFile file:files){
                System.out.printf("%s (%s) Time : %s Type: %s  \nClick here To View File: %s \nBelongs To parent: %s\n\n",
                    file.getName(),
                    file.getId(),
                    file.getDateCreated().toString(),
                    file.getType(),
                    file.getWebViewLink(),
                    file.getParentName() );
            }
        }
        catch(IOException ex){
            System.out.println("Runtime Exception Occured");
        }
    }

}
