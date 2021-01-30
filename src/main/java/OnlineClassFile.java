import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.io.Serializable;


public class OnlineClassFile implements Serializable{
    private String fileName;
    private String parentName;
    private String parentId;
    private GregorianCalendar dateCreated;
    private String webLink;
    private String type;
    private String fileId;

    OnlineClassFile(String fileName, String fileId,
                    String parentName,String parentId,
                    String dateCreated,String type,
                    String weblink){

            this.fileName = fileName;
            this.parentName = parentName;
            this.dateCreated = this.getDate(dateCreated);
            this.fileId = fileId;
            this.webLink = weblink;
            this.type = type;  // TODO: make a funtion for determing the type of the file
            this.parentId = parentId;
    }

    private GregorianCalendar getDate(String dateCreated){
        //TODO: add regex for checking the format of dateCreated

        GregorianCalendar tempDate = new GregorianCalendar();
        //Assuming the dateCreated is in RFC3339 format
        // yyyy-mm-ddTHH:MM.milliseconds(3 decimal places)[Time zone]
        int year = Integer.parseInt(dateCreated.substring(0,4));
        int month = Integer.parseInt(dateCreated.substring(5,7)); //Since months are numbered from 0 to 11
        int day = Integer.parseInt(dateCreated.substring(8,10));

        int hour = Integer.parseInt(dateCreated.substring(11,13));
        int minute = Integer.parseInt(dateCreated.substring(14,16));
        int second = Integer.parseInt(dateCreated.substring(17,18));

        tempDate.set(year,month-1,day,hour,minute,second);

        if(dateCreated.substring(23,24).equals("Z")){
            //Then the time is in UTC no converstion needed
            tempDate.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        }
        else {
            tempDate.setTimeZone(TimeZone.getTimeZone("GMT"+dateCreated.substring(23,29)));
        }

        return tempDate;
    }

    public OnlineClassFile setName(String x){fileName= x; return this;}
    public OnlineClassFile setParentName(String x){parentName = x; return this;}
    public OnlineClassFile setParentId(String x){parentId= x; return this;}
    public OnlineClassFile setWebViewLink(String x){webLink = x; return this;}
    public OnlineClassFile setType(String x){type= x; return this;}
    public OnlineClassFile setId(String x){fileId = x;return this;}


    public String getName(){return fileName;}
    public String getParentName(){return parentName;}
    public String getParentId(){return parentId;}
    public String getWebViewLink(){return webLink;}
    public String getType(){return type;}
    public String getId(){return fileId;}
    public GregorianCalendar getDateCreated(){return dateCreated;}

}
