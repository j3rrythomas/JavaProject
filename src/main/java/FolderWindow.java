import java.awt.*;

import javax.swing.*;


public class FolderWindow {

    public FolderWindow()
    {
       
        JFrame jf = new JFrame("The lectures");
        jf.setLayout(new FlowLayout(FlowLayout.CENTER,100,100));
        jf.setSize(1000, 1000);
        jf.setLocation(450, 500);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //for setting the layout of the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,20,20);
        //the main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        jf.add(mainPanel);
        
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

        //ROW 1
        
         ImageIcon dataIcon = new ImageIcon("src/main/resources/Images/data icon.jpg");
         JButton dataButton = new JButton(dataIcon);
         dataButton.setBackground(Color.BLACK);
         gbc.gridx = 0;
         gbc.gridy = 1;
         gbc.gridwidth = 1;
         gbc.fill = GridBagConstraints.HORIZONTAL;
         mainPanel.add(dataButton,gbc);

         ImageIcon javaIcon = new ImageIcon("src/main/resources/Images/java icon 1.jpg");
         JButton javaButton = new JButton(javaIcon);
         javaButton.setBackground(Color.BLACK);
         gbc.gridx = 1;
         gbc.gridy = 1;
         gbc.gridwidth = 1;
         gbc.fill = GridBagConstraints.HORIZONTAL;
         mainPanel.add(javaButton,gbc);

         ImageIcon lsdIcon = new ImageIcon("src/main/resources/Images/lsd icon 2.jpg");
         JButton lsdButton = new JButton(lsdIcon);
         lsdButton.setBackground(Color.BLACK);
         gbc.gridx = 2;
         gbc.gridy = 1;
         gbc.gridwidth = 1;
         gbc.fill = GridBagConstraints.HORIZONTAL;
         mainPanel.add(lsdButton,gbc);

         //ROW 2
         JLabel dataLabel = new JLabel("Data Structure");
         dataLabel.setFont(new Font("Raleway",Font.ITALIC,16));
         dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
         gbc.gridx = 0;
         gbc.gridy = 2;
         gbc.gridwidth = 1;
         gbc.fill = GridBagConstraints.HORIZONTAL;
         mainPanel.add(dataLabel,gbc);

         JLabel javaLabel = new JLabel("Object Oriented Programming");
         javaLabel.setFont(new Font("Raleway",Font.ITALIC,16));
         javaLabel.setHorizontalAlignment(SwingConstants.CENTER);
         gbc.gridx = 1;
         gbc.gridy = 2;
         gbc.gridwidth = 1;
         gbc.fill = GridBagConstraints.HORIZONTAL;
         mainPanel.add(javaLabel,gbc);

         JLabel lsdLabel = new JLabel("Logic System Design");
         lsdLabel.setFont(new Font("Raleway",Font.ITALIC,16));
         lsdLabel.setHorizontalAlignment(SwingConstants.CENTER);
         gbc.gridx = 2;
         gbc.gridy = 2;
         gbc.gridwidth = 1;
         gbc.fill = GridBagConstraints.HORIZONTAL;
         mainPanel.add(lsdLabel,gbc);




        

        
       
    }
}
