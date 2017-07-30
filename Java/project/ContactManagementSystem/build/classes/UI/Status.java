/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Lau Dukang(laudukang@gmail.com)
 */
public class Status extends JPanel {
public JLabel jlb = new JLabel("广告位招租中···");
    public Status()  {
        
        // JTextArea sta = new JTextArea("Status");
        TitledBorder title = new TitledBorder("");
        this.setBorder(title);
        
        this.add(jlb);
    }
}
