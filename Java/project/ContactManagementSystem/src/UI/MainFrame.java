package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Lau Dukang(laudukang@gmail.com)
 */
public class MainFrame extends JFrame {
 
   public static Show startshow = new Show();
    public static Show mainshow = new Show("ok");
    public static MyMenu mymenu = new MyMenu();
    public static Tools tool = new Tools();
    public static Search sec = new Search();
    public static Tree jtz = new Tree();
    public static Status sta = new Status();
   public static  JPanel p1 = new JPanel(new BorderLayout());

    public MainFrame() {
        //  UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        //  JFrame.setDefaultLookAndFeelDecorated(true);
Data.SetterGetter.setboolean(false);
        try {
            setJMenuBar(mymenu);// 菜单Bar放到JFrame上
        } catch (Exception e) {
            e.printStackTrace();
        }
     
        tool.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        tool.setFloatable(false);

     
        JPanel p2 = new JPanel(new BorderLayout(10, 10));
        JPanel p3 = new JPanel(new BorderLayout());
        JPanel p4 = new JPanel(new GridLayout(1, 1, 10, 10));

        p1.add(sec, BorderLayout.NORTH);
        
       // p1.add(startshow, BorderLayout.CENTER);
         p1.add(startshow, BorderLayout.CENTER);

        JScrollPane jspz = new JScrollPane(jtz);

        Dimension dim = new Dimension(230, 200);
        TitledBorder title1 = new TitledBorder("");

        p4.setBorder(title1);
        p4.add(jspz);
        p4.setPreferredSize(dim);

        p2.add(p4, BorderLayout.WEST);
        p2.add(p1, BorderLayout.CENTER);

        //  p2.setLayout(box2);
        p3.add(tool, BorderLayout.NORTH);
        p3.add(p2, BorderLayout.CENTER);
        p3.add(sta, BorderLayout.SOUTH);

        this.add(p3);

    }

}
