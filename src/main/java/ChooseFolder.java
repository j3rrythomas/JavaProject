import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.io.*;

public class ChooseFolder
{
    private JFrame selectFolderWindow;
    private JPanel mainPanel;
    private JPanel submitPanel;
    private ArrayList<JLabel> labels;
    private ArrayList<JTextField> textBoxes;


    ChooseFolder()
    {
        labels = new ArrayList<JLabel>();
        textBoxes = new ArrayList<JTextField>();
        selectFolder();
    }

    public void selectFolder()
    {
            int LABEL_POS = 0;
            int FIELD_POS = 1;
            selectFolderWindow = new JFrame();
            selectFolderWindow.setLayout(new BorderLayout());
            selectFolderWindow.setSize(400,400);
            selectFolderWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            mainPanel  = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx=0; gbc.gridy=-1;
            gbc.weightx=1;gbc.weighty=1;
            gbc.fill = GridBagConstraints.HORIZONTAL;


            selectFolderWindow.add(mainPanel,BorderLayout.CENTER);
            selectFolderWindow.add(new JLabel("Enter the folders you want to add"), BorderLayout.NORTH);

            submitPanel = new JPanel(new FlowLayout());
            selectFolderWindow.add(submitPanel,BorderLayout.SOUTH);

            JButton addNewField = new JButton("Add field");


            addNewField.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    gbc.gridy = gbc.gridy+1;
                    labels.add(new JLabel("Folder "+ String.valueOf(gbc.gridy+1)));
                    textBoxes.add(new JTextField());
                    gbc.gridx = LABEL_POS;
                    gbc.weightx=1;
                    mainPanel.add(labels.get(labels.size()-1),gbc);
                    gbc.gridx= FIELD_POS;
                    gbc.weightx=2;
                    mainPanel.add(textBoxes.get(textBoxes.size()-1),gbc);
                    mainPanel.setVisible(false);
                    mainPanel.setVisible(true);
                }
            });

            submitPanel.add(addNewField);

            JButton submit = new JButton("Done");
            submit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        ArrayList<String> folderList = new ArrayList<String>();
                        for(JTextField x : textBoxes){
                            try{
                                folderList.add(x.getText());
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
