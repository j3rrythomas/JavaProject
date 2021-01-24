import java.awt.*;
//import java.awt.event.*;

import javax.swing.*;


public class OpenWindow
{
    public OpenWindow()
    {
        JFrame jf = new JFrame("Welcome");
        jf.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
        jf.setSize(1000, 1000);
        jf.setLocation(450, 500);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //for setting the layout of the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,20,20);
        //creating the panel for displaying name and icon
        JPanel mainPanel = new JPanel(new GridBagLayout());
        jf.add(mainPanel);

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

        //the update button
        JButton updateButton = new JButton("Start Search");
        updateButton.setPreferredSize(new Dimension(40, 40));
        updateButton.setBackground(Color.GREEN);
        updateButton.setForeground(Color.DARK_GRAY);
        updateButton.setFont(new Font("Raleway",Font.BOLD,18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(updateButton,gbc);        

    }

    /*public static void main(String[] args) {
        //new OpenWindow();
        //new FolderWindow();
        //new LecturesWindow();
    }*/
}
