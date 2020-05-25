package com.view;

import com.controller.PlaneController;
import com.eneity.EnemyPlane;
import com.eneity.FightPlane;
import com.utils.P;

import javax.swing.*;

/**
 * 窗口类
 */
public class FrameWindow {
    /**
     * 构造方法
     */
    public FrameWindow() {
        launch();
    }

    /**
     * 创建窗口界面
     */
    public void launch() {
        FightPlane fightPlane = new FightPlane();

        PlaneController controller = new PlaneController(fightPlane);//飞机控制类
        GamePanel gamePanel = new GamePanel(fightPlane);
        JFrame jFrame = new JFrame();
        jFrame.add(gamePanel);
        jFrame.setTitle("飞机大战");
        jFrame.addKeyListener(controller);//给窗口设置监听事件
        jFrame.setSize(P.WEIGHT, P.HEIGHT);//设置窗口大小
        jFrame.setVisible(true);//显示在窗口
        jFrame.setResizable(false);//使窗口大小固定
        jFrame.setLocationRelativeTo(null);//初始化的界面显示的位置,null为内容自适应
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击关闭之后后台也会关闭
    }
}
