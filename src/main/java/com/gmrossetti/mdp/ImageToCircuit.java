package com.gmrossetti.mdp;

import com.gmrossetti.mdp.model.GridPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToCircuit {
    final static int supportedImgWidth = 60;
    final static int supportedImgHeight = 45;
    public static GridPoint[][] parseImageToGrid(String circuitName) throws IOException {
        BufferedImage imgBase = ImageIO.read(new File(circuitName + "-base.gif"));
        BufferedImage imgData = ImageIO.read(new File(circuitName + "-data.gif"));

        if(!validateImgs(imgBase, imgData))
            throw new RuntimeException("Circuit file format not valid!");

        GridPoint[][] grid = new GridPoint[supportedImgHeight][supportedImgWidth];

        for (int y = 0; y < supportedImgHeight; y++) {
            for (int x = 0; x < supportedImgWidth; x++) {
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

        return imgBaseWidth == supportedImgWidth && imgBaseHeight == supportedImgHeight &&
                imgDataWidth == supportedImgWidth && imgDataHeight == supportedImgHeight;
    }
}
