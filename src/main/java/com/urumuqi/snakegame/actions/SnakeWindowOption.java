package com.urumuqi.snakegame.actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class: 贪吃蛇-主控制界面.
 *
 * @author wuqi <wuqi226@gmail.com>
 * @version 2022/04/30 : V0.0.1
 * @name SnakeOptionWindow
 **/
public class SnakeWindowOption extends JPanel implements ActionListener, KeyListener {

    /**
     * 上、下、左、右
     */
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

    public static final int GAME_LOC_X = 50, GAME_LOC_Y = 50, GAME_WIDTH = 700, GAME_HEIGHT = 500, SIZE = 10;

    /**
     * rx 起点x坐标
     * ry 起点y坐标
     * score 得分
     * speed 移动速度
     */
    public static int rx, ry, score = 0, speed = 5;

    /**
     * 暂停标记
     */
    public boolean startFlag = false;

    /**
     * startBtn 开始
     * stopBtn 暂停
     * quitBtn 退出
     */
    JButton startBtn, stopBtn, quitBtn;

    Snake snake;

    public SnakeWindowOption() {
        snake = new Snake();
        rx = (int) (Math.random() * (GAME_WIDTH - 10) + GAME_LOC_X);
        ry = (int) (Math.random() * (GAME_HEIGHT - 10) + GAME_LOC_Y);

        startBtn = new JButton("开始");
        stopBtn = new JButton("暂停");
        quitBtn= new JButton("退出");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(startBtn);
        add(stopBtn);
        add(quitBtn);

        startBtn.addActionListener(this);
        stopBtn.addActionListener(this);
        quitBtn.addActionListener(this);
        addKeyListener(this);

        new Thread(new SnakeThread()).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 刷新界面
        g.setColor(Color.white);
        g.fillRect(GAME_LOC_X, GAME_LOC_Y, GAME_WIDTH, GAME_HEIGHT);

        // 绘制边界
        g.setColor(Color.black);
        g.drawRect(GAME_LOC_X, GAME_LOC_Y, GAME_WIDTH, GAME_HEIGHT);
        g.drawString("Score : " + score + "    Speed : "  + speed + " (MaxSpeed: 100)", 250, 25);

        // 生成食物
        g.setColor(Color.green);
        g.fillRect(rx, ry, 10, 10);
        snake.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            startFlag = true;
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
        }
        if (e.getSource() == stopBtn) {
            startFlag = false;
            startBtn.setEnabled(true);
            stopBtn.setEnabled(false);
        }
        if (e.getSource() == quitBtn) {
            System.exit(0);
        }
        this.requestFocus();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                startFlag = !startFlag;
                startBtn.setEnabled(startFlag);
                stopBtn.setEnabled(!startFlag);
                System.out.println("Game Stop : " + startFlag);
                break;
            default:
                System.out.println("Pressed : " + e.getKeyCode());
                break;
        }

        if (!startFlag) {
            return;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (snake.length() != 1 && snake.getDir() == DOWN) {
                    break;
                }
                snake.changeDir(UP);
                break;
            case KeyEvent.VK_DOWN:
                if (snake.length() != 1 && snake.getDir() == UP) {
                    break;
                }
                snake.changeDir(DOWN);
                break;
            case KeyEvent.VK_LEFT:
                if (snake.length() != 1 && snake.getDir() == RIGHT) {
                    break;
                }
                snake.changeDir(LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                if (snake.length() != 1 && snake.getDir() == LEFT) {
                    break;
                }
                snake.changeDir(RIGHT);
                break;
            default:
                System.out.println("Nothing @todo !!!");
                break;
        }
    }

    /**
     * Thread : 蛇移动线程
     */
    class SnakeThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(Math.max(100 - speed, 0));
                    // 刷新
                    repaint();
                    if (startFlag) {
                        snake.move();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
