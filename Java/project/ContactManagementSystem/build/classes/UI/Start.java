/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Data.LoginForm;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Lau Dukang(laudukang@gmail.com)
 */
public class Start extends MainFrame implements Runnable {

    public Start() {
        try {
            //MainFrame frame = new MainFrame();
            new Thread(this).start();
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //  UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //  JFrame.setDefaultLookAndFeelDecorated(true);
            SwingUtilities.updateComponentTreeUI(this);

            this.setTitle("��ӭ   " + LoginForm.getuser() + "   ʹ��ͨѶ¼����ϵͳ v3.0");

            this.addWindowListener(new WindowAdapter() {//��ȷ��Ҫ�˳���
                @Override
                public void windowClosing(WindowEvent e) {
                    int option = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�˳���",
                            "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (option == JOptionPane.YES_OPTION) {
                        System.exit(0);
                        // }
                    }
                }
            });

            //com.sun.awt.AWTUtilities.setWindowOpacity(frame, 0.3f);
            this.setUndecorated(false);//���û����ô˴����װ��
            this.setSize(895, 680);
            this.setResizable(false);//���ô�������
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        System.out.println("start thread running");
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
