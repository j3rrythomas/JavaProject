import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Arrays;
import java.io.IOException;
import java.io.File;

import java.net.URI;
import java.net.URISyntaxException;

public class LecturesWindow
{
    JFrame jf;
    String frameName;

    JPanel lecturesPanel;
    //for setting the layout of lecturesPanel
    GridBagConstraints gpc = new GridBagConstraints();

    //for the dropdown for selecting dates
    JComboBox<String> dateChooser;
    String[] date;
    ArrayList<OnlineClassFile> lectures;


    public LecturesWindow(String folderName)
    {
        frameName = folderName;
        jf = new JFrame(frameName);
        jf.setLayout(new FlowLayout(FlowLayout.CENTER,100,50));
        jf.setSize(1000, 1000);
        jf.setLocation(450, 500);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //get the date
        date = getDate();

        //for setting the layout of the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20,20,5,5);

        //the panels
        JPanel mainPanel = new JPanel(new GridBagLayout());
        jf.add(mainPanel);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,50,10));
        searchPanel.setBackground(Color.LIGHT_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(searchPanel,gbc);

        lecturesPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(lecturesPanel,gbc);

        //the searchPanel
        JLabel dateLabel = new JLabel("Choose the date");
        dateLabel.setFont(new Font("Raleway",Font.BOLD,14));
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        searchPanel.add(dateLabel);

        //Drop down menu to choose the date
        dateChooser = new JComboBox<String>(date);
        dateChooser.setPreferredSize(new Dimension(130,30));
        searchPanel.add(dateChooser);

        //Handling selections
        dateChooser.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String s = (String) dateChooser.getSelectedItem();
                System.out.println(s);
                showLectures(s);
            }
        });

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100,30));
        searchButton.setFont(new Font("Raleway",Font.BOLD,15));
        //Action Lister for the search button
        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                //String str =
                //showLectures(str);
            }
            });
        searchPanel.add(searchButton);

        //Lectures panel components
        gbc.insets = new Insets(10,10,20,20);
        JLabel DATE = new JLabel("DATE",JLabel.CENTER);
        DATE.setFont(new Font("Raleway",Font.ITALIC,15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lecturesPanel.add(DATE,gbc);

        JLabel WEBLINK = new JLabel("WEBLINK",JLabel.CENTER);
        WEBLINK.setFont(new Font("Raleway",Font.ITALIC,15));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lecturesPanel.add(WEBLINK,gbc);

    }
    public void showLectures(String data)
    {
        lecturesPanel.removeAll();

        JLabel DATE = new JLabel("DATE",JLabel.CENTER);
        DATE.setFont(new Font("Raleway",Font.ITALIC,15));
        gpc.gridx = 0;
        gpc.gridy = 0;
        gpc.gridwidth = 1;
        gpc.fill = GridBagConstraints.HORIZONTAL;
        lecturesPanel.add(DATE,gpc);

        JLabel WEBLINK = new JLabel("WEBLINK",JLabel.CENTER);
        WEBLINK.setFont(new Font("Raleway",Font.ITALIC,15));
        gpc.gridx = 1;
        gpc.gridy = 0;
        gpc.gridwidth = 1;
        gpc.fill = GridBagConstraints.HORIZONTAL;
        lecturesPanel.add(WEBLINK,gpc);

        gpc.insets = new Insets(10,10,10,10);
        int LABEL_POS=0, LINK_POS=2;
        JLabel date,link;
        int i=2,j=0;
        /*while(i<=10)
        {
            j=0;
            date = new JLabel(data[j][i], JLabel.CENTER);
            date.setFont(new Font("Raleway",Font.PLAIN,14));
            gpc.gridx = j;
            gpc.gridy = i;
            gpc.gridwidth = 1;
            gpc.fill = GridBagConstraints.HORIZONTAL;
            lecturesPanel.add(date,gpc);j++;

            link = new JLabel(data[j][i], JLabel.CENTER);
            link.setFont(new Font("Raleway",Font.PLAIN,14));
            gpc.gridx = j;
            gpc.gridy = i;
            gpc.gridwidth = 1;
            gpc.fill = GridBagConstraints.HORIZONTAL;
            lecturesPanel.add(link,gpc);i++;
        }*/
        for(OnlineClassFile file: lectures){
            GregorianCalendar cal = file.getDateCreated();
            String dateStr = cal.get(5)+"-"+(int)(cal.get(2)+1)+"-"+cal.get(1);
            //ArrayList<JLabel> fileNames = new ArrayList<JLabel>();
            //ArrayList<JButton> linkButtons = new ArrayList<JButton>();
            if(dateStr.equals(data)){

                JLabel fileName = new JLabel(file.getName());
                fileName.setFont(new Font("Raleway",Font.PLAIN,14));
                gpc.gridx = LABEL_POS;
                gpc.gridy = i;
                gpc.gridwidth = 1;
                //gpc.fill = GridBagConstraints.HORIZONTAL;
                lecturesPanel.add(fileName,gpc);
                //fileNames.add(fileName);

                //Add the function to open the browser on clicking the button
                //TODO: Adjust the position of buttons
                JButton linkButton = new JButton("Click To open file");
                linkButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        try{
                            Desktop dsk = Desktop.isDesktopSupported() ?  Desktop.getDesktop() : null;

                        //    if(dsk != null&& dsk.isSupported(Desktop.Action.BROWSE)){ //this if statement checks for exceptions
                                dsk.browse(new URI(file.getWebViewLink()));
                        //    }
                        //    else{
                        //        throw new NullPointerException();
                        //    }
                        }
                        catch (UnsupportedOperationException e){
                            try{
                                //The fall back process for linux systems with xdg
                                ProcessBuilder pb = new ProcessBuilder("xdg-open", file.getWebViewLink());
                                //pb.directory(new File("/usr/bin/"));
                                Process p = pb.start();

                            }
                            catch(IOException e1){
                                OpenWindow.alertWindow("An IOException has occurred "+ "\n"+ e1.getMessage());
                            }
                        }
                        catch (NullPointerException e){
                            OpenWindow.alertWindow("ALERT "+e.getMessage());
                        }
                        catch(IOException e){
                            OpenWindow.alertWindow("ALERT "+e.getMessage());
                        }
                        catch (URISyntaxException e){
                            OpenWindow.alertWindow("ALERT "+e.getMessage());
                        }

                    }
                });

                gpc.gridx = LINK_POS;
                gpc.gridy=i;
                gpc.gridwidth =1;
                gpc.fill = GridBagConstraints.HORIZONTAL;
                lecturesPanel.add(linkButton,gpc);
                //linkButtons.add(linkButton);
                i++;
            }
        }
        lecturesPanel.setVisible(false);
        lecturesPanel.setVisible(true);
    }

    private void getFiles(){
        lectures = new ArrayList<OnlineClassFile>();
        for (OnlineClassFile f : OpenWindow.driveFiles.files){
            if(f.getParentName().equals(frameName)){
                lectures.add(f);
            }
        }
    }

    private String[] getDate(){
        getFiles();
        ArrayList<String> dates = new ArrayList<String>();
        for(OnlineClassFile file: lectures){
            GregorianCalendar cal=file.getDateCreated();
            String dateStr = cal.get(5)+"-"+(int)(cal.get(2)+1)+"-"+cal.get(1);
            //dates.add(cal.get(cal.DAY_OF_MONTH)+"-"+(int)(cal.get(cal.MONTH)+1)+"-"+cal.get(cal.YEAR));
            if(!dates.contains(dateStr))
                dates.add(dateStr);
        }
        Object temp[] = dates.toArray();
        String[] str = Arrays.copyOf(temp,temp.length,String[].class);
        return str;
    }
}
