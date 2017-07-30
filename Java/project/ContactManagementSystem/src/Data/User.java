/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class User {

    public static String[] name = new String[100];
    public static String[] password = new String[100];
    public static int count;

    public User() throws FileNotFoundException, IOException {
        new Newfile();
        password();

    }

    private void password() {
        File file = new File(System.getProperty("user.dir") + "\\文件" + "\\users.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader bfr = new BufferedReader(fr);
        count = 0;
        String users;
        try {
            while ((users = bfr.readLine()) != null) {
                Scanner input = new Scanner(users);
                name[count] = input.next();
                password[count] = input.next();

                count++;
            }
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            bfr.close();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean searchpassword(String code, String password1) {
        for (int i = 0; i < count; i++) {
            if ((name[i].equals(code)) && password[i].equals(password1)) {
                return true;
            }
        }
        return false;
    }

    public boolean searchusers(String code) {
        for (int i = 0; i < count; i++) {
            if (name[i].equals(code)) {
                return true;
            }
        }
        return false;
    }

    public void registerUser(String name, String password) {

        try {
            File file = new File(System.getProperty("user.dir") + "\\文件"
                    + "\\users.txt");
            FileWriter fw = new FileWriter(file, true);// 添加
            BufferedWriter bfw = new BufferedWriter(fw);
            bfw.write(name);
            bfw.write(" ");
            bfw.write(password);
            bfw.write("\r\n");
            bfw.flush();
            fw.close();

            //为用户创建一个用户文件,用于存入相关信息(分组,联系人等)
            File file1 = new File(System.getProperty("user.dir") + "\\文件"
                    + "\\" + name);
            file1.mkdir();//创建文件夹
            File file2 = new File(System.getProperty("user.dir") + "\\文件"
                    + "\\" + name + "\\默认分组");
            file2.mkdir();//创建文本

        } catch (Exception e) {
            System.out.println("在文件中添加用户时出现错误");
        }

    }

    public static boolean identifypassword(String pass1, String pass2, String pass3) {
        for (int i = 0; i < count; i++) {
            if (name[i].equals(LoginForm.getuser()) && password[i].equals(pass1) && pass2.equals(pass3)) {

                return true;

            }
        }
        return false;
    }

    public static void changepassword(String pass) {
        for (int i = 0; i < count; i++) {
            if (name[i].equals(LoginForm.getuser())) {
                password[i] = pass;
            System.out.println("hehe");
            }
            File file1 = new File(System.getProperty("user.dir") + "\\文件" + "\\users.txt");
            PrintWriter output;
            try {
                output = new PrintWriter(file1);
                for (int j = 0; j < count; j++) {
                    output.print(name[j]);
                    output.print(" ");
                    output.println(password[j]);
                  
                }
                  output.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
