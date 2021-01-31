import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;


public class OpenWindow
{
    JFrame mainWindow;
    JFrame folderWindow;
    JFrame selectFolderWindow;
    ArrayList<JButton> folderButtons;

    static Main driveFiles;
    ArrayList<String> requiredFolders;
    private LecturesWindow lecturesWindow;

    public OpenWindow()
    {
        createMainWindow();
    }

    public void createMainWindow()
    {
        if(mainWindow == null){

            try{
                File f =  new File("build/main/resources/data.txt");
                if(f.exists())
                    driveFiles = Main.deserializeData();
            }
            catch(IOException err){
                    OpenWindow.alertWindow(err.getMessage());
            }
            catch (GeneralSecurityException err){
                    OpenWindow.alertWindow(err.getMessage());
            }

            mainWindow = new JFrame("Welcome");
            mainWindow.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
            mainWindow.setSize(1000, 1000);
            mainWindow.setLocation(450, 500);
            mainWindow.setVisible(true);
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //for setting the layout of the panel
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10,10,20,20);
            //creating the panel for displaying name and icon
            JPanel mainPanel = new JPanel(new GridBagLayout());
            mainWindow.add(mainPanel);
        {
            //Creating icon
            ImageIcon icon = new ImageIcon("src/main/resources/Images/classFind.jpg");
            //label for icon
            JLabel imgLabel = new JLabel(icon,JLabel.CENTER);
            imgLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(imgLabel,gbc);

            //label for "ClassFind"
            JLabel nameLabel = new JLabel("Welcome to ClassFind",JLabel.CENTER);
            nameLabel.setFont(new Font("Raleway",Font.BOLD,30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(nameLabel,gbc);

            //label for caption
            JLabel captionLabel = new JLabel("A place where you can find all the lectures!",JLabel.CENTER);
            captionLabel.setFont(new Font("Raleway",Font.ITALIC,18));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(captionLabel,gbc);
        }
            JButton addFolders = addMainWindowButton("Add Folders to track");
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(addFolders,gbc);
            addFolders.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    new ChooseFolder();
                }
            });


            JButton viewLectures = addMainWindowButton("ViewLectures");
            gbc.gridx = 0;
            gbc.gridy = 4;
            mainPanel.add(viewLectures,gbc);


            viewLectures.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                        if(driveFiles !=null){
                            mainWindow.setVisible(false);
                            //if(folderWindow == null)
                                FolderWindow();
                            //else
                                //folderWindow.setVisible(true);
                        }
                    };
                }
            );

            //the update button
            JButton updateDatabase = addMainWindowButton("Start Search");
            gbc.gridx = 0;
            gbc.gridy = 5;
            updateDatabase.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    try{
                        driveFiles = new Main();
                        Main.serializeData(driveFiles);
                    }
                    catch(IOException err)
                    {
                        alertWindow(err.getMessage());
                        err.printStackTrace();
                    }
                    catch(GeneralSecurityException err)
                    {
                        alertWindow("Security exception occured");
                    }
                }
            });
            mainPanel.add(updateDatabase, gbc);

        }
    }

    public void FolderWindow()
    {

        folderWindow = new JFrame("The lectures");
        folderWindow.setLayout(new FlowLayout(FlowLayout.CENTER,100,100));
        folderWindow.setSize(1000, 1000);
        folderWindow.setLocation(450, 500);
        folderWindow.setVisible(true);
        folderWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //for setting the layout of the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,20,20);
        //the main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        folderWindow.add(mainPanel);

        //the back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Raleway",Font.BOLD,14));
        backButton.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        mainPanel.add(backButton,gbc);
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                folderWindow.setVisible(false);
                mainWindow.setVisible(true);
                folderWindow.removeAll();
                folderButtons.clear();
                //folderWindow.dispatchEvent(new WindowEvent(folderWindow, WindowEvent.WINDOW_CLOSING));
                folderWindow.dispose();
            }
        });


        JLabel lectures = new JLabel("Here are the lectures !!!");
        lectures.setFont(new Font("Raleway",Font.PLAIN,25));
        lectures.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(lectures,gbc);
        try{
            folderButtons =  new ArrayList<JButton>();
            ArrayList<String> folderNames = new ArrayList<String>();
            requiredFolders = FolderList.loadFolderList();

            for(String str : driveFiles.getFolderList().keySet())
            {
                if(!driveFiles.getFolderList().get(str).equals("") && requiredFolders.contains(driveFiles.getFolderList().get(str))){
                //if()
                    folderNames.add(driveFiles.getFolderList().get(str));
                }
            }

            if(requiredFolders.size() > folderNames.size())
            {
                OpenWindow.alertWindow(
                "ALERT!"+"\n"+"Some of the Folders entered are not found in the\n Google Drive"+
                "The Folders enterd are "+requiredFolders.toString()+"\n"+
                "The Folders in the Google Drive are "+driveFiles.getFolderList().values().toString()
                );
            }

            ImageIcon dataIcon = new ImageIcon("src/main/resources/Images/file icon 3.jpg");
            int k = 0; // To keep track of the folders that are displayed

            gbc.gridwidth=1;
            for(int i = 1; i<=folderNames.size()/3+1;i+=2) //y loop +=2 because we are printing folder icon and the label
                            //+1 in the condition is the offset for y axis
            {
                for(int j = 0;j<3;j++)// x loop
                {
                    if(k<folderNames.size()){ //if k becomes greater than or equal to the folderNames then all the folders have been shown
                        gbc.gridx = j;
                        gbc.gridy = i+1;
                        JButton folderButton = new JButton(dataIcon);
                        folderButton.setBackground(Color.WHITE);
                        folderButton.setBorder(BorderFactory.createEtchedBorder(1));
                        folderButtons.add(folderButton);

                        folderButtons.get(folderButtons.size()-1).addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent evt){
                                for(JButton b : folderButtons){b.setBorder(BorderFactory.createEtchedBorder(1));}
                                ((JButton)evt.getSource()).setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
                                int index = folderButtons.indexOf((JButton)evt.getSource());
                                String lectureFolder =  folderNames.get(index);
                                System.out.println("Folder at "+index +" is "+ lectureFolder);
                                new LecturesWindow(lectureFolder);
                            }
                        });

                        mainPanel.add(folderButtons.get(folderButtons.size()-1),gbc);

                        gbc.gridy = i+2;

                        JLabel folderName = new JLabel(folderNames.get(k),JLabel.CENTER);
                        folderName.setFont(new Font("Raleway",Font.ITALIC,14));
                        mainPanel.add(folderName,gbc);

                        k++;
                    }//
                }
            } //y loop ended
        }
        catch(IOException e){
            OpenWindow.alertWindow(e.getMessage());
        }


        JScrollPane pane = new JScrollPane(mainPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        folderWindow.setContentPane(pane);
    }



    private JButton addMainWindowButton(String buttonLabel)
    {
        JButton button = new JButton(buttonLabel);
        button.setPreferredSize(new Dimension(40, 40));
        button.setBackground(Color.GREEN);
        button.setForeground(Color.DARK_GRAY);
        button.setFont(new Font("Raleway",Font.BOLD,18));

        return button;
    }

    //This is the go to alert window for showing the errors
    public static void alertWindow(String str)
    {
        JOptionPane.showMessageDialog(null,str);
    }

}
