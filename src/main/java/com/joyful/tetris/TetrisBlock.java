package com.joyful.tetris;

import java.awt.Color;

public class TetrisBlock {
    private int[][] shape;
    private Color color;

    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }
    
    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {
        return shape[0].length;
    }
}
