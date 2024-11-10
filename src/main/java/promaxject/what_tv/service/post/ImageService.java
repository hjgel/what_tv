package promaxject.what_tv.service.post;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ImageService {

    public BufferedImage applyMosaic(BufferedImage image, int blockSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y += blockSize) {
            for (int x = 0; x < width; x += blockSize) {
                int pixelColor = image.getRGB(x, y);

                for (int dy = 0; dy < blockSize; dy++) {
                    for (int dx = 0; dx < blockSize; dx++) {
                        if (x + dx < width && y + dy < height) {
                            image.setRGB(x + dx, y + dy, pixelColor);
                        }
                    }
                }
            }
        }
        return image;
    }

    public File processAndSaveMosaicImage(File inputImageFile, int blockSize) throws IOException {
        BufferedImage image = ImageIO.read(inputImageFile);
        BufferedImage mosaicImage = applyMosaic(image, blockSize);

        File outputImageFile = new File("mosaic_" + inputImageFile.getName());
        ImageIO.write(mosaicImage, "jpg", outputImageFile);

        return outputImageFile;
    }
}