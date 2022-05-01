package com.urumuqi.snakegame.actions;

import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class: 贪吃蛇-蛇.
 *
 * @author wuqi <wuqi226@gmail.com>
 * @version 2022/04/30 : V0.0.1
 * @name Snake
 **/
public class Snake {

    /**
     * 方向 上、下、左、右
     */
    static final int DIR[][] = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    /**
     * 蛇身体，数组
     */
    private List<SnakeNode> snakeNodes = new ArrayList<>();
    /**
     * 当前蛇移动方向
     */
    private int curDir;

    /**
     * create snake.
     */
    public Snake() {
        curDir = SnakeWindowOption.RIGHT;
        snakeNodes.add(new SnakeNode(370, 250));
        snakeNodes.add(new SnakeNode(360, 250));
        snakeNodes.add(new SnakeNode(350, 250));
        System.out.println("current snake : " + JSON.toJSONString(snakeNodes) + " , current direction : " + curDir + " \n");
    }

    /**
     * snake length.
     *
     * @return int
     */
    public int length() {
        return snakeNodes.size();
    }

    /**
     * 获取当前方向.
     *
     * @return current direction
     */
    public int getDir() {
        return curDir;
    }

    /**
     * 画蛇
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.black);
        for (SnakeNode snakeNode : snakeNodes) {
            g.fillRect(snakeNode.getX(), snakeNode.getY(), 10, 10);
        }
    }

    /**
     * 检查蛇是否挂掉.
     *
     * @return is dead
     */
    public boolean dead() {
        for (int i = 1; i < snakeNodes.size(); i ++) {
            if (snakeNodes.get(0).getX() == snakeNodes.get(i).getX()
                    && snakeNodes.get(0).getY() == snakeNodes.get(i).getY()) {
                System.out.println("Snake is dead because : " + JSON.toJSONString(snakeNodes));
                return true;
            }
        }
        return false;
    }

    /**
     * 转向.
     *
     * @return move head node
     */
    public SnakeNode headMove() {
        int toX = snakeNodes.get(0).getX() + SnakeWindowOption.SIZE * DIR[curDir][0];
        int toY = snakeNodes.get(0).getY() + SnakeWindowOption.SIZE * DIR[curDir][1];

        if (toX > SnakeWindowOption.GAME_LOC_X + SnakeWindowOption.GAME_WIDTH - SnakeWindowOption.SIZE) {
            toX = SnakeWindowOption.GAME_LOC_X;
        }
        if (toX < SnakeWindowOption.GAME_LOC_X) {
            toX = SnakeWindowOption.GAME_WIDTH + SnakeWindowOption.GAME_LOC_X - SnakeWindowOption.SIZE;
        }

        if (toY > SnakeWindowOption.GAME_LOC_Y + SnakeWindowOption.GAME_HEIGHT - SnakeWindowOption.SIZE) {
            toY = SnakeWindowOption.GAME_LOC_Y;
        }
        if (toY < SnakeWindowOption.GAME_LOC_Y) {
            toY = SnakeWindowOption.GAME_HEIGHT + SnakeWindowOption.GAME_LOC_Y - SnakeWindowOption.SIZE;
        }

        return new SnakeNode(toX, toY);
    }

    /**
     * 蛇移动.
     */
    public void move() {
        SnakeNode head = headMove();
        SnakeNode newNode = new SnakeNode();
        boolean eat = false;
        if (Math.abs(head.getX() - SnakeWindowOption.rx) < 10
                && Math.abs(head.getY() - SnakeWindowOption.ry) < 10) {
            eat = true;
            newNode = new SnakeNode(snakeNodes.get(snakeNodes.size() - 1));
            SnakeWindowOption.rx = (int) (Math.random() * (SnakeWindowOption.GAME_WIDTH - 10) + SnakeWindowOption.GAME_LOC_X);
            SnakeWindowOption.ry = (int) (Math.random() * (SnakeWindowOption.GAME_HEIGHT - 10) + SnakeWindowOption.GAME_LOC_Y);
        }

        for (int i = snakeNodes.size() - 1; i > 0; i --) {
            snakeNodes.set(i, snakeNodes.get(i - 1));
        }
        snakeNodes.set(0, head);

        if (dead()) {
            JOptionPane.showMessageDialog(null,
                    "The snake was so hungry that it ate itself",
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        if (eat) {
            snakeNodes.add(newNode);
            SnakeWindowOption.score ++;
            SnakeWindowOption.speed ++;
        }
    }

    /**
     * 改变方向.
     *
     * @param dir
     */
    public void changeDir(int dir) {
        curDir = dir;
    }
}
