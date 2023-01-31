package com.joyful.tetris;

import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel { 
    private final int cellSize;
    private final int rowsNumber;
    private final int columnsNumber;
    private Color[][] background;
    
    private TetrisBlock block;
    
    public GameArea(JPanel placeholder) {
        placeholder.setVisible(false);
        setBounds(placeholder.getBounds());
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
        
        columnsNumber = 10;
        cellSize = getBounds().width / columnsNumber;
        rowsNumber = getBounds().height / cellSize;
        
        background = new Color[rowsNumber][columnsNumber];
        spawnBlock();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        for (int j = 0; j < rowsNumber; j++) {
//            for (int i = 0; i < columnsNumber; i++) {
//                g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);            
//            }
//        }

        drawBackgrouond(g);
        drawBlock(g);
    }
    
    private void moveBlockToBackground() {
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if (block.getShape()[i][j] == 1) {
                    background[i + block.getY()][j + block.getX()] = block.getColor();
                }
            }
        }
    }

    private void drawBlock(Graphics g) {
        int[][] shape = block.getShape();
        
        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = 0; col < block.getWidth(); col++) {
                if (shape[row][col] == 1) {
                    int x = (block.getX() + col) * cellSize;
                    int y = (block.getY() + row) * cellSize;
                    g.setColor(block.getColor());
                    drawGridSquare(g, block.getColor(), cellSize, cellSize);
                }
            }            
        }
    }

    private void spawnBlock() {
        block = new TetrisBlock(new int[][]{{1, 0}, {1, 0}, {1, 1}}, BLUE);
        block.spawn(columnsNumber);
    }
    
    public void moveBlockDown() {
        if (!isBottom()) {
            block.moveDown();
            repaint();
        }
    }

    private boolean isBottom() {
        return block.getBottomCoord() >= rowsNumber; 
    }

    private void drawBackgrouond(Graphics g) {
        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                Color color = background[i][j];
                if (color != null) {
                    drawGridSquare(g, color, i * cellSize, j * cellSize);
                }
            }
        }
    }

    private void drawGridSquare(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, cellSize, cellSize);
        g.setColor(BLACK);
        g.drawRect(x, y, cellSize, cellSize);
    }
}
