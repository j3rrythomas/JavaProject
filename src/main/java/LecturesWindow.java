import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LecturesWindow
{
    JFrame jf;
    String frameName = "DS lectures";

    JPanel lecturesPanel;
    //for setting the layout of lecturesPanel
    GridBagConstraints gpc = new GridBagConstraints();

    //for the dropdown for selecting dates
    JComboBox<String> dateChooser;
    String date[] = {"22-08-2000","23-00-2010","01-05-2016","22-09-2019"};
   

    public LecturesWindow()
    {
        jf = new JFrame(frameName);
        jf.setLayout(new FlowLayout(FlowLayout.CENTER,100,50));
        jf.setSize(1000, 1000);
        jf.setLocation(450, 500);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
            }
        });

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100,30));
        searchButton.setFont(new Font("Raleway",Font.BOLD,15));
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
    public void showLectures(String data[][])
    {
        gpc.insets = new Insets(10,10,10,10);
        JLabel date,link;
        int i=1,j=0;
        while(i<=10)
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
        }
    }
}
