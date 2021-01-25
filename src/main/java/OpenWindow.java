import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;


public class OpenWindow
{
    JFrame mainWindow;
    JFrame folderWindow;
    ArrayList<JButton> folderButtons;
    Main driveFiles;

    public OpenWindow(Main files)
    {
        driveFiles = files;
        createMainWindow();

    }

    public void createMainWindow(){
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
        JLabel captionLabel = new JLabel("A place where you can find the your lectures!",JLabel.CENTER);
        captionLabel.setFont(new Font("Raleway",Font.ITALIC,18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(captionLabel,gbc);

        JButton addFolders = new JButton("Add Folders to track");
        addFolders.setPreferredSize(new Dimension(40, 40));
        addFolders.setBackground(Color.GREEN);
        addFolders.setForeground(Color.DARK_GRAY);
        addFolders.setFont(new Font("Raleway",Font.BOLD,18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(addFolders,gbc);

        //the update button
        JButton updateButton = new JButton("Start Search");
        updateButton.setPreferredSize(new Dimension(40, 40));
        updateButton.setBackground(Color.GREEN);
        updateButton.setForeground(Color.DARK_GRAY);
        updateButton.setFont(new Font("Raleway",Font.BOLD,18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(updateButton,gbc);

        updateButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    mainWindow.setVisible(false);
                    FolderWindow();
            };
        });
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

        //components in the panel



        //ROW 0
        JLabel lectures = new JLabel("Here are the lectures !!!");
        lectures.setFont(new Font("Raleway",Font.PLAIN,30));
        //lectures.setBackground(Color.CYAN);
        //lectures.setOpaque(true);
        lectures.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(lectures,gbc);

        if(folderButtons == null){
            folderButtons =  new ArrayList<JButton>();
            ArrayList<String> folderNames = new ArrayList<String>();

            for(String str : driveFiles.getFolderList().keySet())
            {
                //if(driveFiles.getFolderList().get(str).equals())
                folderNames.add(driveFiles.getFolderList().get(str));
                System.out.println(driveFiles.getFolderList().get(str));
            }


            ImageIcon dataIcon = new ImageIcon("src/main/resources/Images/data icon.jpg");
            int k = 0; // To keep track of the folders that are displayed

            gbc.gridwidth=1;
            for(int i = 1; i<=folderNames.size()/3+1;i+=2) //y loop +=2 because we are printing folder icon and the label
                            //+1 in the condition is the offset for y axis
            {
                for(int j = 0;j<3;j++)// x loop
                {
                    if(k<folderNames.size()){ //if k becomes greater than or equal to the folderNames then all the folders have been shown
                        gbc.gridx = j;
                        gbc.gridy = i;
                        folderButtons.add(new JButton(dataIcon));
                        mainPanel.add(folderButtons.get(folderButtons.size()-1),gbc);

                        gbc.gridy = i+1;
                        mainPanel.add(new JLabel(folderNames.get(k)),gbc);
                        k++;
                    }
                }
            } //y loop ended

        }

    }
}
