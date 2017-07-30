/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Data.LoginForm;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Administrator
 */
public class Main {

    public static void main(String[] args) throws Exception {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        JFrame.setDefaultLookAndFeelDecorated(true);
        new LoginForm();
       //new Newfile();
        // UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        // SwingUtilities.updateComponentTreeUI(user);

        // Data.file.Loadgroup();
//Data.file.searchgroup(Data.file.getgrouplist());
//Data.file.ReadInformation();
    }
}
