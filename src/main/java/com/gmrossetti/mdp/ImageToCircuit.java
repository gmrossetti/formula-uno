package com.gmrossetti.mdp;

import com.gmrossetti.mdp.model.GridPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageToCircuit {
    final static int SUPPORTED_IMG_WIDTH = 60;
    final static int SUPPORTED_IMG_HEIGHT = 45;
    public static GridPoint[][] parseImageToGrid(String circuitName) throws IOException {
        final String basePath = "/com/gmrossetti/mdp/circuits/";

        final String imgBaseSuffix = "-base.gif";
        final String imgDataSuffix = "-data.gif";

        InputStream inputStream1 = ImageToCircuit.class.getResourceAsStream(basePath + circuitName + imgBaseSuffix);
        InputStream inputStream2 = ImageToCircuit.class.getResourceAsStream(basePath + circuitName + imgDataSuffix);

        BufferedImage imgBase = ImageIO.read(inputStream1);
        BufferedImage imgData = ImageIO.read(inputStream2);

        if(!validateImgs(imgBase, imgData))
            throw new RuntimeException("Circuit file format not valid!");

        GridPoint[][] grid = new GridPoint[SUPPORTED_IMG_HEIGHT][SUPPORTED_IMG_WIDTH];

        for (int y = 0; y < SUPPORTED_IMG_HEIGHT; y++) {
            for (int x = 0; x < SUPPORTED_IMG_WIDTH; x++) {
                // Ottieni il colore del pixel (RGB)
                Color colorBase = new Color(imgBase.getRGB(x, y));
                Color colorData = new Color(imgData.getRGB(x, y));

                GridPoint.GridPointType type = GridPoint.GridPointType.OUTSIDE;

//                final boolean isCurving = colorData.getGreen() == 0xff;
//                final boolean isNarrow = colorData.getBlue() == 0xff;

                if (colorBase.equals(Color.WHITE)) {
                    type = GridPoint.GridPointType.INSIDE;

                    if (colorData.getRed() == 0xff) {
                        type = GridPoint.GridPointType.START;
                    } else if (colorData.getRed() > 0) {
                        type = GridPoint.GridPointType.END;
                    }
                }

                //  GridPoint model = new GridPoint(x,y,type,isCurving,isNarrow);
                GridPoint model = new GridPoint(x,y,type);

                grid[y][x] = model;
            }
        }

        return grid;
    }

    public static boolean validateImgs(BufferedImage imgBase, BufferedImage imgData){
        final int imgBaseWidth = imgBase.getWidth();
        final int imgBaseHeight = imgBase.getHeight();

        final int imgDataWidth = imgData.getWidth();
        final int imgDataHeight = imgData.getHeight();

        return imgBaseWidth == SUPPORTED_IMG_WIDTH && imgBaseHeight == SUPPORTED_IMG_HEIGHT &&
                imgDataWidth == SUPPORTED_IMG_WIDTH && imgDataHeight == SUPPORTED_IMG_HEIGHT;
    }
}
