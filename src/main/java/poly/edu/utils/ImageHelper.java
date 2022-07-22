/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.edu.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author leminhthanh
 */
public class ImageHelper {
    public static Image resize(Image originalImage, int targetWidth, int targetHight){
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHight, Image.SCALE_SMOOTH);
        return resultingImage;
    }
    public static byte[] toByArray(Image img, String type) throws Exception{
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(img, 0, 0, null);
        graphics2D.dispose();
        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, type, byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        
        return imageInByte;
    }
    public static Image createImageFrombyArray(byte[] data, String type) throws IOException{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
        
        Image img = bufferedImage.getScaledInstance(bufferedImage.getHeight(), bufferedImage.getWidth(), Image.SCALE_SMOOTH);
        return img;
    }
}
