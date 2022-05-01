package com.urumuqi.snakegame.actions;

/**
 * Class: 蛇身体坐标点对象.
 *
 * @author wuqi <wuqi226@gmail.com>
 * @version 2022/04/30 : V0.0.1
 * @name SnakeNode
 **/
public class SnakeNode {
    /**
     * 蛇身点位坐标
     * x, y
     */
    private int x, y;

    public SnakeNode() {
    }

    public SnakeNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SnakeNode(SnakeNode node) {
        x = node.getX();
        y = node.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
