package com.eneity;

import com.view.GamePanel;

import java.util.Random;

/**
 * 源源不断的创建敌机
 */
public class CreateEnemy extends Thread{
    Random random =new Random();//创建随机数保证每次出现的敌机x坐标随机出现
    @Override
    public void run() {
       while(true){
            try {
               EnemyPlane e= new EnemyPlane();
               e.x=random.nextInt(650)+50;
               e.y=-150;
                GamePanel.EnemyPlaneArrayList.add(e);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
