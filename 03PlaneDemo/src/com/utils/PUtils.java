package com.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * 加载图片工具类
 */
public class PUtils {
    public static Image getImage(String url){
        ImageIcon icon=new ImageIcon(PUtils.class.getClassLoader().getResource(url));
        return icon.getImage();
    }




}
