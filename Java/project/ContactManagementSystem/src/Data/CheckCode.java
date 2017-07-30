/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JPanel;

public class CheckCode extends JPanel {

    private static Random random = new Random();
    private int width = 53; // ��֤����
    private int height = 25; // ��֤��߶�
    private int font_size = 20; // ��֤����ɫ
    private int x = 0; // ��֤�����ڴ���X����
    private int y = 0; // ��֤�����ڴ���Y����
    private int jam = 4; // ����Ԫ�� ����ʹ�� 4~7 ֮�������
    private String code = ""; // ������֤��
    private String theCode = "";

    public CheckCode() {

        this.setVisible(true);
        this.setSize(60, 50);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                repaintPanel();
            }
        });
    }

    public void repaintPanel() {
        this.repaint();
    }

    public Color getRandomColor() {
        int R = random.nextInt(255), G = random.nextInt(255), B = random
                .nextInt(255);
        return new Color(R, G, B);
    }

    public String getRandomString() {
        int num = random.nextInt(9);
        code = num + "";
        return num + "";
    }

    public void checkCode(Graphics g) {
        this.drawBorder(g);
        this.drawCode(g);
        this.drawJam(g);
    }

    public void drawBorder(Graphics g) {
        Color gc = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.setColor(gc);
    }

    public void drawCode(Graphics g) {
        Color gc = g.getColor();
        theCode = "";
        for (int i = 0; i < 4; i++) {
            String string = getRandomString();
            theCode += string;
            g.setColor(getRandomColor());
            g.setFont(new Font("����", Font.BOLD, font_size));
            g.drawString(string, x + 5 + (i * 12), y + font_size);
        }
        g.setColor(gc);
         System.out.println("��ǰ��Ч��֤�룺"+theCode);
    }

    public void drawJam(Graphics g) {
        Color gc = g.getColor();
        for (int i = 0; i < jam; i++) {
            g.setColor(getRandomColor());
            g.drawLine(x + random.nextInt(width), y + random.nextInt(height), x
                    + random.nextInt(width), y + random.nextInt(height));
        }
        g.setColor(gc);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        checkCode(g);
        g.setColor(c);
    }

    public String getCode() {
        return theCode;
    }
}
