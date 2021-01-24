import java.awt.*;
import javax.swing.*;

public class LecturesWindow 
{
    JFrame jf;
    String frameName = "DS lectures";
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
         gbc.insets = new Insets(5,5,5,5);

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

        JPanel lecturesPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(lecturesPanel,gbc);

        //the searchPanel
        JLabel dateLabel = new JLabel("dd-mm-yyyy");
        dateLabel.setFont(new Font("Raleway",Font.BOLD,14));
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        searchPanel.add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setPreferredSize(new Dimension(150,30));
        searchPanel.add(dateField);

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100,30));
        searchButton.setFont(new Font("Raleway",Font.BOLD,15));
        searchPanel.add(searchButton);

        //Lectures panel components
        gbc.insets = new Insets(10,10,20,20);
        JLabel DATE = new JLabel("DATE");
        DATE.setFont(new Font("Raleway",Font.ITALIC,15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lecturesPanel.add(DATE,gbc);

        JLabel WEBLINK = new JLabel("WEBLINK");
        WEBLINK.setFont(new Font("Raleway",Font.ITALIC,15));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lecturesPanel.add(WEBLINK,gbc);

    }
}
