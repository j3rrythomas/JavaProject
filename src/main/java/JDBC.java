import java.security.GeneralSecurityException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import java.sql.*;



class JDBC{

  private static String dburl="jdbc:mysql://freedb.tech:3306/freedbtech_DRIVE?autoReconnect=true&useSSL=false";
  private static String username="freedbtech_3696434";
  private static String password="5D4S2RZG3jMELFq";
  public static void update(ArrayList<String> details,Statement stmt)
  {
    try
    {

      String id=details.get(0);
      String subject=details.get(1);
      String date=details.get(2);
      String link=details.get(3);

      String sql="INSERT IGNORE INTO LINKS VALUES(\""+id+"\",\""+subject+"\",\""+date+"\",\""+link+"\")";
      stmt.executeUpdate(sql);
      // stmt.executeUpdate(sql);




    }
    catch(Exception e)
    {
      System.out.println(e);
      e.printStackTrace();
    }
  }

  public static void updateDatabase() throws IOException,GeneralSecurityException
  {
    try{

        // Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(dburl,username,password);
        Statement stmt=con.createStatement();
        List<OnlineClassFile> files = StartDrive.getFiles();
        ArrayList<String> details =new ArrayList<String>();


        for(OnlineClassFile file:files){
            // System.out.printf("%s (%s) Time : %s Type: %s  \nClick here To View File: %s \nBelongs To parent: %s\n\n",
            //     file.getName(),
            //     file.getId(),
            //     file.getDateCreated().toString(),
            //     file.getType(),
            //     file.getWebViewLink(),
            //     file.getParentName() );

            String parent=file.getParentName();
            GregorianCalendar cal=file.getDateCreated();
            String date=cal.get(GregorianCalendar.DATE)+"-"+cal.get(GregorianCalendar.MONTH)+"-"+cal.get(GregorianCalendar.YEAR);

            if(parent.equals("Ds lectures") || parent.equals("Java classes") ||parent.equals("lsd lectures"))
            {
              details.add(file.getId());
              details.add(parent.substring(0,parent.indexOf(' ')).toLowerCase());
              details.add(date);
              details.add(file.getWebViewLink());

            update(details,stmt);

            details.clear();
          }


        }
        con.close();
      }
      catch(IOException ex){
          System.out.println("Runtime Exception Occured");
        }
        catch(SQLException e){
          e.printStackTrace();
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
      }

      public static void showDatabase() throws IOException,GeneralSecurityException
      {
        try{

            // Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(dburl,username,password);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM LINKS");
            while(rs.next())
            {
              System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
            }
            con.close();
            }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(Exception e){
          e.printStackTrace();
        }

      }
}
