import java.awt.*;
import java.awt.event.*;

import javax.management.monitor.StringMonitor;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.io.*;

public class ChooseFolder
{
    //int count=0;
    private JFrame selectFolderWindow;
    private JPanel mainPanel;
    private JPanel submitPanel;
    private ArrayList<JLabel> labels;
    private ArrayList<JTextField> textBoxes;
    private ArrayList<JCheckBox> checkBoxes;
    //Main driveFiles;


    ChooseFolder()
    {
        labels = new ArrayList<JLabel>();
        textBoxes = new ArrayList<JTextField>();
        checkBoxes = new ArrayList<JCheckBox>();
        selectFolder();

    }

    public void selectFolder()
    {
            int LABEL_POS = 0; // The x position of the field where the folder label goes
            int FIELD_POS = 1; //The x postion of the field where the folder name is to be entered
            selectFolderWindow = new JFrame();
            selectFolderWindow.setLayout(new BorderLayout());
            selectFolderWindow.setSize(400,400);
            selectFolderWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            mainPanel  = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10,10,20,20);
            gbc.gridx=0; gbc.gridy=-1;
            gbc.weightx=1;gbc.weighty=1;
            gbc.fill = GridBagConstraints.HORIZONTAL;


            selectFolderWindow.add(mainPanel,BorderLayout.CENTER);
            selectFolderWindow.add(new JLabel("Enter the folders you want to add"), BorderLayout.NORTH);

            submitPanel = new JPanel(new FlowLayout());
            selectFolderWindow.add(submitPanel,BorderLayout.SOUTH);

            ArrayList<String> driveFolders = new ArrayList<String>();
            //try{driveFiles = new Main();}catch(Exception e){}
            for(String str : OpenWindow.driveFiles.getFolderList().keySet())
            {
                driveFolders.add(OpenWindow.driveFiles.getFolderList().get(str));
            }
            
            ArrayList<String> prevFolders=new ArrayList<String>();
            File f=new File("build/resources/main/folderList.txt");
            if(f.exists()){
                try{
                    prevFolders=FolderList.loadFolderList();
                }catch(IOException e){}
            }
            for(String s: driveFolders)
            {
                JCheckBox trackFolderBox = new JCheckBox(s);
                try{
                    if(prevFolders.contains(s)){
                        trackFolderBox.setSelected(true);
                    }
                }
                finally{
                trackFolderBox.setFont(new Font("Raleway",Font.PLAIN,14));
                checkBoxes.add(trackFolderBox);
                gbc.gridy = gbc.gridy+1;
                gbc.gridx= FIELD_POS;
                gbc.weightx=2;
                mainPanel.add(checkBoxes.get(checkBoxes.size()-1),gbc);}
            }
            JScrollPane pane = new JScrollPane(mainPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            selectFolderWindow.add(pane,BorderLayout.CENTER);          
            JButton submit = new JButton("Done");
            submit.setFont(new Font("Raleway",Font.BOLD,14));
            submit.addActionListener(new ActionListener(){
                ArrayList<String> folderList = new ArrayList<String>();         
                public void actionPerformed(ActionEvent e){

                    try{
                        for(JCheckBox x : checkBoxes){
                            try{
                                if(x.isSelected()){
                                    folderList.add(x.getText());
                                }
                            }
                            catch (NullPointerException err){
                                continue;
                            }
                        }
                        FolderList fl = new FolderList(folderList);
                        fl.saveFolderList();
                        selectFolderWindow.dispose();
                        }

                    catch(IOException err){
                        OpenWindow.alertWindow(err.getMessage());
                    }
                }
            });
            submitPanel.add(submit);

            mainPanel.setVisible(true);
            submitPanel.setVisible(true);
            selectFolderWindow.setVisible(true);
    }
}
