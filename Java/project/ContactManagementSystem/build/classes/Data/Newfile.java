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
        File file = new File(System.getProperty("user.dir") + "\\文件");
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("//不存在");
            file.mkdir();

        }

    }

    public static void settxt() {
        File file1 = new File(System.getProperty("user.dir") + "\\文件\\" + "users.txt");
        if (!file1.exists()) {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                System.out.println("保存用户登陆数据失败！"+e.toString());
            }
        }
    }
}
