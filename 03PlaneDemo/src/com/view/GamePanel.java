package com.view;

import com.eneity.CreateEnemy;
import com.eneity.EnemyPlane;
import com.eneity.FightPlane;
import com.utils.P;
import com.utils.PUtils;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 游戏面板
 */
public class GamePanel extends JPanel {
    public static long count = 0;//消灭敌机数量
    public static List<EnemyPlane> EnemyPlaneArrayList = new LinkedList<EnemyPlane>();
    private FightPlane fightPlane;//战斗机
    public ImageIcon bz2 = new
            ImageIcon(PUtils.getImage("images/dd2.png"));

    //构造方法,启动这个线程
    public GamePanel(FightPlane fightPlane) {
        this.fightPlane = fightPlane;

        new updateGame().start();//不断重画出画布
        new CreateEnemy().start();//画出10架敌机
    }

    boolean flage = true;

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, P.WEIGHT, P.HEIGHT);
        g.setColor(Color.red);
        g.drawString("杀龟数量:" + count, 650, 50);
        //画出战斗机
        if (fightPlane != null) {
            fightPlane.drawMe(g);
        }
        //画出敌机
        for (int i = 0; i < EnemyPlaneArrayList.size(); i++) {
            if (EnemyPlaneArrayList.get(i).flat == 1) {
                int x = EnemyPlaneArrayList.get(i).x;
                int y = EnemyPlaneArrayList.get(i).y;
                g.drawImage(bz2.getImage(), x, y, 150, 150, null);
                EnemyPlaneArrayList.get(i).flat--;
                GamePanel.count++;
                System.out.println(count);
                if (EnemyPlaneArrayList.get(i).flat == -3) {
                    int c = new Random().nextInt(650) + 50;
                    EnemyPlaneArrayList.get(i).x = c;
                    EnemyPlaneArrayList.get(i).y = -150;
                }
            } else {
                EnemyPlaneArrayList.get(i).drawMe(g);
            }

        }

    }

    //内部类，创建一个线程不停的刷新界面
    class updateGame extends Thread {
        @Override
        public void run() {
            while (flage) {
                repaint();
            }
        }
    }
}
