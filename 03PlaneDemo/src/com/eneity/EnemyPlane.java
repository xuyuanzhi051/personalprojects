package com.eneity;

import com.utils.P;
import com.utils.PUtils;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 敌机类
 */
public class EnemyPlane extends GameObject {
     public int flat=0;//检测子弹是否与敌机相撞
    public static Image dj =PUtils.getImage("images/wugui.png");//飞机图像资源
   /* public static Image zd=PUtils.getImage("images/zd.png");//子弹图片资源*/
    public EnemyPlane(){
        x=300;
        y=300;
        width=120;
        height=120;
       // new CreateBullet().start();//创建画出子弹的线程
    }
    @Override
    public void drawMe(Graphics g) {

       g.drawImage(dj,x,y,width,height,null);
       y+=3;//每次画完更新y坐标
        if(y> P.HEIGHT){//节约资源重复利用每架飞机
            y=-150;
        }

      /* //画出子弹
       for(int i=0;i<arrayList.size();i++){
           arrayList.get(i).drawMe(g);
       }*/
    }

    @Override
    public Rectangle getRect() {

        return new Rectangle(x,y,width,height);
    }

   /* //创建子弹内部类
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
            return null;
        }
    }
    *//**
     * 创建一个不断画出子弹的线程
     *//*
    class CreateBullet extends Thread{
        @Override
        public void run() {
            while(true){
                try {
                    Bullet bullet=new Bullet();
                    bullet.x=x+32;
                    bullet.y=y+20;
                    arrayList.add(bullet);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
