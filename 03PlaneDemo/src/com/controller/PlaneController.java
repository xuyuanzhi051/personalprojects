package com.controller;

import com.eneity.FightPlane;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 控制飞机运动
 */
public class PlaneController extends KeyAdapter {
    FightPlane fightPlane;
    public PlaneController(FightPlane fightPlane){
        this.fightPlane=fightPlane;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        diretion(e.getKeyCode());
    }
    //控制飞机移动
    public void diretion(int code){
        switch(code){
            case 37://对应键盘左键
                fightPlane.x-=15;
                break;
            case 38://对应键盘上键
                fightPlane.y-=15;
                break;
            case 39://对应键盘右键
                fightPlane.x+=15;
                break;
            case 40://对应键盘下键
                fightPlane.y+=5;
                break;
        }
    }
}
