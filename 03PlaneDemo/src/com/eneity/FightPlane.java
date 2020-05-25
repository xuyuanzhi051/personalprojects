package com.eneity;

import com.utils.P;
import com.utils.PUtils;
import com.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 战斗机
 */
public class FightPlane extends GameObject {
    public List<Bullet> arrayList =new LinkedList<Bullet>();
    public static Image image =PUtils.getImage("images/zdj.png");//飞机图像资源
    public static Image zd=PUtils.getImage("images/zd.png");//子弹图片资源
    public ImageIcon bz2=new
            ImageIcon(PUtils.getImage("images/dd2.png"));
    public FightPlane(){
        x=300;
        y=300;
        width=120;
        height=120;
        new CreateBullet().start();//创建画出子弹的线程
    }
    @Override
    public void drawMe(Graphics g) {

       g.drawImage(image,x,y,width,height,null);


       //画出子弹;
       for(int i=0;i<arrayList.size();i++){
           //检测子弹是否与敌机相撞
           for(int j=0;j<GamePanel.EnemyPlaneArrayList.size();j++){
               if(GamePanel.EnemyPlaneArrayList.get(j).getRect().intersects(arrayList.get(i).getRect())){
                  /* int x=GamePanel.EnemyPlaneArrayList.get(j).x;
                   int y=GamePanel.EnemyPlaneArrayList.get(j).y;
                   GamePanel.EnemyPlaneArrayList.remove(j);

                   g.drawImage(bz2.getImage(),500,500,150,150,null);*/

                   GamePanel.EnemyPlaneArrayList.get(j).flat=1;


               }
           }
           if(arrayList.get(i).y<20){
               arrayList.remove(i);//优化子弹类，当子弹达到一定位置时让其消失
           }

           arrayList.get(i).drawMe(g);
       }
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(x,y,width,height);
    }

    //创建子弹内部类
    class Bullet extends GameObject{
        public Bullet(){
            width=60;
            height=120;
        }
        @Override
        public void drawMe(Graphics g) {//在面板上画出子弹
             g.drawImage(zd,x,y,width,height,null);
             y-=3;
        }

        @Override
        public Rectangle getRect() {

            return new Rectangle(x,y,width,height);
        }
    }
    /**
     * 创建一个不断画出子弹的线程
     */
    class CreateBullet extends Thread{
        @Override
        public void run() {
            while(true){
                try {
                    Bullet bullet=new Bullet();
                    bullet.x=x+32;
                    bullet.y=y+20;
                    arrayList.add(bullet);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
