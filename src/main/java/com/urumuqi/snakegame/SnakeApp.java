package com.urumuqi.snakegame;

import com.urumuqi.snakegame.actions.SnakeWindowOption;

import javax.swing.*;
import java.awt.*;

/**
 * Class: 贪吃蛇 主程序启动器.
 *
 * @author wuqi <wuqi226@gmail.com>
 * @version 2022/04/30 : V0.0.1
 * @name Snake
 **/
public class SnakeApp extends JFrame {

    /**
     * 窗口配置
     * 宽度、高度
     * 窗口起始坐标
     */
    private static final int WIDTH = 800, HEIGHT = 600, LX = 200, LY = 80;

    private SnakeWindowOption snakeWindowOption;

    public SnakeApp() throws HeadlessException {
        super("Eat, Eat, Eat, Snake");
        snakeWindowOption = new SnakeWindowOption();
        add(snakeWindowOption);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLocation(LX, LY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public static void main(String[] args) {
        new SnakeApp();
    }
}
