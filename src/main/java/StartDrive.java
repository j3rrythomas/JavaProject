import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.HashMap;

public class StartDrive
{
    private static final String APPLICATION_NAME = "Google Drive Example";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance(); //Returns a global Thread safe instace of JacksonFactory
    private static final String TOKENS_DIRECTORY_PATH = "tokens"; //We choose the path to store the data

    //Modifying these scopes requires to delete the token folder created
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_METADATA_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";


    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException
    {
        //Throws exception if /credentials.json is not found

        //This funtion returns an InputStream Object of a resource. null if it is not found
        InputStream in = StartDrive.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if(in == null)
        {
            throw new FileNotFoundException("Resource Not Found:" +CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,new InputStreamReader(in)); //JsonFactory and Reader Objects as parameters

        //Build flow and trigger user authoriztion request
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT,JSON_FACTORY,clientSecrets,SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow,receiver).authorize("user");
    }

    public static List<OnlineClassFile> getFiles() throws IOException, GeneralSecurityException
    {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        List<OnlineClassFile> completeFileList = new ArrayList<OnlineClassFile>();
        List<File> completeFolderList = new ArrayList<File>();
        HashMap <String,ArrayList<OnlineClassFile>> fileMap = new HashMap<String,ArrayList<OnlineClassFile>>();

        try{
            //System.out.println("Ahoy matey");
            Drive service = new Drive.Builder(HTTP_TRANSPORT,JSON_FACTORY,getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();


            //Get the list of files
            //Drive.Files.List
            String filePageToken = null;
            String folderPageToken = null;

            do{
                FileList fileList = service.files().list()
                .setQ("mimeType!='application/vnd.google-apps.folder'")
                .setPageSize(10)
                .setFields("nextPageToken,files(id,name,createdTime,mimeType,webViewLink,parents)")
                .setPageToken(filePageToken)
                .execute();


                  for (File file : fileList.getFiles()){

                          if(file.getParents()!=null){
                            completeFileList.add(new OnlineClassFile(
                            file.getName(),file.getId(),
                            "",file.getParents().get(0),
                            file.getCreatedTime().toString(),file.getMimeType(),
                            file.getWebViewLink()
                                ));
                            }
                        }

                filePageToken = fileList.getNextPageToken();

            }while(filePageToken!=null);

            do{
                FileList folderList = service.files().list().setPageSize(10)
                .setQ("mimeType='application/vnd.google-apps.folder'")
                .setFields("nextPageToken,files(id,name,createdTime,mimeType,webViewLink,parents)")
                .setPageToken(folderPageToken)
                .execute();

                for (File file: folderList.getFiles()){
                    for(int i = 0;i<completeFileList.size();i++){
                        if(completeFileList.get(i).getParentId().equals(file.getId())){
                            completeFileList.set(i,completeFileList.get(i).setParentName(file.getName()));
                        }
                    }
                }

                folderPageToken = folderList.getNextPageToken();

            }while(folderPageToken!=null);

        }


        catch (java.net.SocketTimeoutException e)
        {
            System.out.println("The Server is taking too long to respond");
        }
        catch (IOException e)
        {
            System.out.println("Runtime Exception Occured");
        }
        return completeFileList;
    }
}
