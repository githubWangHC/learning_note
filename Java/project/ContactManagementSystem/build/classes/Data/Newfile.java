/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class Newfile {

    public Newfile() {
        setnewfile();
        settxt();
    }

    public static void setnewfile() {
        File file = new File(System.getProperty("user.dir") + "\\�ļ�");
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("//������");
            file.mkdir();

        }

    }

    public static void settxt() {
        File file1 = new File(System.getProperty("user.dir") + "\\�ļ�\\" + "users.txt");
        if (!file1.exists()) {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                System.out.println("�����û���½����ʧ�ܣ�"+e.toString());
            }
        }
    }
}
