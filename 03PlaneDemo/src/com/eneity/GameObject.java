package com.eneity;

import java.awt.*;

/**
 * 定义游戏中所有对象的父类
 */
public abstract class GameObject {
    public int x,y,width,height;//定义飞机的坐标，长，宽
    public abstract void drawMe(Graphics g);//飞机画自己的方法
    public abstract Rectangle getRect();//得到一个矩形用来检测有没有和飞机相撞
}
