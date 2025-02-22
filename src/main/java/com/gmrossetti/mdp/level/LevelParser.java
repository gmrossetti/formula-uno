package com.gmrossetti.mdp.level;

import com.gmrossetti.mdp.entity.cartesian.CircuitGridPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LevelParser {
    final static int SUPPORTED_IMG_WIDTH = 60;
    final static int SUPPORTED_IMG_HEIGHT = 45;
    public static CircuitGridPoint[][] parseImageToGrid(String circuitName) throws IOException {
        final String basePath = "/com/gmrossetti/mdp/circuits/";
        final String imgFileExtension = ".gif";

        InputStream inputStream1 = LevelParser.class.getResourceAsStream(basePath + circuitName + imgFileExtension);

        BufferedImage imgBase = ImageIO.read(inputStream1);

        if(!validateImgs(imgBase))
            throw new RuntimeException("Circuit file format not valid!");

        CircuitGridPoint[][] grid = new CircuitGridPoint[SUPPORTED_IMG_HEIGHT][SUPPORTED_IMG_WIDTH];

        for (int y = 0; y < SUPPORTED_IMG_HEIGHT; y++) {
            for (int x = 0; x < SUPPORTED_IMG_WIDTH; x++) {
                // Ottieni il colore del pixel (RGB)
                Color colorBase = new Color(imgBase.getRGB(x, y));

                CircuitGridPoint.GridPointType type = CircuitGridPoint.GridPointType.OUTSIDE;

                if (colorBase.equals(Color.WHITE)) {
                    type = CircuitGridPoint.GridPointType.INSIDE;
                }

                // TODO: remove isNarrow isCurving
                grid[y][x] = new CircuitGridPoint(x,y,type,false,false);
            }
        }

        return grid;
    }

    public static boolean validateImgs(BufferedImage imgBase){
        final int imgBaseWidth = imgBase.getWidth();
        final int imgBaseHeight = imgBase.getHeight();

        return imgBaseWidth == SUPPORTED_IMG_WIDTH && imgBaseHeight == SUPPORTED_IMG_HEIGHT;
    }
}
