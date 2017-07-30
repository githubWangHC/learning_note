/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import static UI.Search.jtfMessage;
import UI.Start;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class LoginForm extends JFrame implements ActionListener {

    public static String username;
    User users;

    public JTextField inputusername = new JTextField(13);

    public JPasswordField inputuserpassword = new JPasswordField(13);
    public JTextField inputcode = new JTextField(6);

    public JTextField userNameJTextField = new JTextField(13);
    public JPasswordField passwordJPasswordField = new JPasswordField(13);
    public JButton[] loadjbutton = {new JButton("登  录"), new JButton("取 消")};
    public JButton[] registerjbutton = {new JButton("注  册"), new JButton("取 消")};

    public JTextField registercode = new JTextField(6);
    CheckCode checkcode = new CheckCode();

    JTabbedPane jta = new JTabbedPane(JTabbedPane.LEFT);
    JPanel load = new JPanel(new GridLayout(6, 1, 10, 10));
    public int keycount = 0;
    public String newstr = "";
    public String oldstr = "";

    public LoginForm() {

        this.setTitle("欢迎使用通讯录管理系统 v3.0");
        this.setResizable(false); // 设置窗口不可拖动大小
        this.setSize(470, 330);

        this.setUndecorated(false);//禁用或启用此窗体的装饰

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel t1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JPanel t2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JPanel t3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JPanel t4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        t1.add(new JLabel("用户名："));
        t1.add(inputusername);
        t2.add(new JLabel("密    码："));
        t2.add(inputuserpassword);

        inputusername.setFont(new Font("宋体", Font.BOLD, 16));
        inputuserpassword.setFont(new Font("宋体", Font.BOLD, 16));
        inputuserpassword.setEchoChar('*');
        t3.add(new JLabel("                                    验证码："));

        t3.add(inputcode);
        inputcode.setFont(new Font("宋体", Font.BOLD, 16));
        inputcode.addKeyListener(new InPutCodeListener());

        t4.add(loadjbutton[0]);
        t4.add(loadjbutton[1]);
        loadjbutton[0].addActionListener(this);
        loadjbutton[1].addActionListener(this);
        loadjbutton[0].addKeyListener(new MyKeyListener());
        load.add(new JLabel(""));
        load.add(t1);
        load.add(t2);
        load.add(t3);
        load.add(t4);
        load.add(new JLabel(""));

        JPanel register = new JPanel(new GridLayout(6, 1, 10, 10));

        JPanel rt1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JPanel rt2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JPanel rt3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JPanel rt4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        rt1.add(new JLabel("用户名："));
        rt1.add(userNameJTextField);
        rt2.add(new JLabel("密    码："));
        rt2.add(passwordJPasswordField);

        userNameJTextField.setFont(new Font("宋体", Font.BOLD, 16));
        passwordJPasswordField.setFont(new Font("宋体", Font.BOLD, 16));
        passwordJPasswordField.setEchoChar('*');
        rt3.add(new JLabel("                                    验证码："));
        rt3.add(registercode);
        registercode.setFont(new Font("宋体", Font.BOLD, 16));

        rt4.add(registerjbutton[0]);
        rt4.add(registerjbutton[1]);
        registerjbutton[0].addActionListener(this);
        registerjbutton[1].addActionListener(this);
        register.add(new JLabel(""));
        register.add(rt1);
        register.add(rt2);
        register.add(rt3);
        register.add(rt4);
        register.add(new JLabel(""));

        jta.add("<html><br>用<br>户<br>登<br>陆<br> <br> <br></html>", load);
        jta.add("<html><br>用<br>户<br>注<br>册<br> <br> <br></html>", register);

        this.add(checkcode);

        checkcode.setBounds(288, 162, 55, 26);
        this.add(jta);
        inputusername.requestFocusInWindow();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public class MyKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keycode = e.getKeyCode();
            if (keycode == KeyEvent.VK_ENTER) {
                System.out.println("press enter");
                try {
                    users = new User();
                } catch (IOException ex) {
                    Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                ifload();
            }
        }

    };

    public class InPutCodeListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

            //System.out.println("AAAAAAAAAAA=" + inputcode.getText());
            //   System.out.println(inputcode.getText().toString());
        }

        @Override
        public void keyPressed(KeyEvent e) {
            oldstr = inputcode.getText();
            // System.out.println("BBBBBBBBBBBBB=" + inputcode.getText());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println("CCCCCCCCCCCC=" + inputcode.getText());
            newstr = inputcode.getText();
            if (inputcode.getText().equals("") || newstr.length() == 0) {
                keycount = 0;
                return;
                // System.out.println("000000000");
            }
            if (!newstr.equals("") && newstr.length() < oldstr.length() && keycount != 0) {
                keycount--;
                //System.out.println("------" + keycount);
            } else if (newstr.length() > oldstr.length()) {
                keycount++;
                // System.out.println("++++++++++++" + keycount);
            }
            if (keycount == 4) {
                loadjbutton[0].requestFocus();
            }
        }
    }

    public void allinputclear() {
        inputusername.setText("");
        inputusername.requestFocus();
        inputuserpassword.setText("");
        inputcode.setText("");
        registercode.setText("");
        userNameJTextField.setText("");
        passwordJPasswordField.setText("");
        keycount = 0;
        oldstr = "";
        newstr = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.users = new User();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (jta.getSelectedIndex() == 0 && e.getSource() == loadjbutton[0]) {
            ifload();
        } else if (jta.getSelectedIndex() == 1 && e.getSource() == registerjbutton[0]) {
            String theCode1 = checkcode.getCode();
            String myCode1 = registercode.getText();

            username = userNameJTextField.getText().trim();
            String pwd1 = String.valueOf(passwordJPasswordField.getPassword());
            if (username.equals("") || pwd1.equals("")) {
                JOptionPane.showMessageDialog(this, "请输入用户名或密码！", "系统提示",
                        JOptionPane.INFORMATION_MESSAGE);
                allinputclear();
                checkcode.repaintPanel();

                return;
            }

            if (!myCode1.equals(theCode1)) {
                JOptionPane.showMessageDialog(this, "您输入的验证码不正确！", "系统提示",
                        JOptionPane.INFORMATION_MESSAGE);
                allinputclear();
                checkcode.repaintPanel();

                return;
            }

            if (users.searchusers(username)) { // 在已有用户中找到
                JOptionPane.showMessageDialog(this, "注册失败！\n该用户已经存在！",
                        "系统提示", JOptionPane.INFORMATION_MESSAGE);
                this.allinputclear();
                checkcode.repaintPanel();

                return;
            } else // 用户不存在,可以注册
            {
                users.registerUser(username, pwd1);
                JOptionPane.showMessageDialog(this, "注册成功！\n请使用您的账户登陆！",
                        "系统提示", JOptionPane.INFORMATION_MESSAGE);
                this.allinputclear();

                return;
            }

        }
        if (e.getSource() == loadjbutton[1] || e.getSource() == registerjbutton[1]) {
            System.out.println("取消登陆！");
            System.exit(0);
        }

    }

    public void ifload() {
        String theCode = checkcode.getCode();
        String myCode = inputcode.getText();

        //     username = "admin";
        //     String pwd = "2";
        username = inputusername.getText().trim();
        String pwd = String.valueOf(inputuserpassword.getPassword());
        if (username.equals("") || pwd.equals("")) {
            JOptionPane.showMessageDialog(this, "请输入用户名或密码！", "系统提示",
                    JOptionPane.INFORMATION_MESSAGE);
            allinputclear();
            checkcode.repaintPanel();

            return;
        }

        if (!myCode.equals(theCode)) {
            JOptionPane.showMessageDialog(this, "您输入的验证码不正确！", "系统提示",
                    JOptionPane.INFORMATION_MESSAGE);
            allinputclear();
            checkcode.repaintPanel();
            return;
        }
        if (users.searchpassword(username, pwd)) {
            try {
                // 登录成功，显示主窗体,并传递登录‘用户名’作为实参
                Data.file.Loadgroup();

                Data.file.searchgroup(Data.file.getgrouplist());
                Data.file.ReadInformation();

                Start start = new Start();
            } catch (IOException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "登录成功！",
                    "系统提示", JOptionPane.INFORMATION_MESSAGE);

            this.setVisible(false);// 释放登录窗口

        } else {// 登录失败，提示用户名或密码出错
            JOptionPane.showMessageDialog(null, "用户不存在或密码错误！");
            this.allinputclear();
            checkcode.repaintPanel();
        }

    }

    public static String getuser() {
        return username;
    }
}
