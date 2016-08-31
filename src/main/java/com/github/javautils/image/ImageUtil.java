package com.github.javautils.image;

import com.github.javautils.encrypt.BASE64Decoder;
import com.github.javautils.encrypt.BASE64Encoder;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 图片处理工具类
 */
public class ImageUtil {

    /**
     * 给图片添加水印、可设置水印图片旋转角度
     *
     * @param iconPath   水印图片路径
     * @param srcImgFile 源图片路径
     * @param degree     水印图片旋转角度
     */
    public static byte[] markImageByIcon(String iconPath, File srcImgFile, Integer degree) {
        OutputStream os = null;
        try {
            Image srcImg = ImageIO.read(srcImgFile);

            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
                    BufferedImage.TYPE_INT_RGB);

            // 得到画笔对象
            Graphics2D g = buffImg.createGraphics();

            // 设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
                    0, null);

            if (null == degree) {
                // 设置水印旋转
                // g.rotate(Math.toRadians(degree), (double) buffImg.getWidth()
                // / 2, (double) buffImg.getHeight() / 2);
                degree = 0;
            }

            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(iconPath);

            // 得到Image对象。
            Image img = imgIcon.getImage();

            float alpha = 0.6f; // 透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

            // 表示水印图片的位置
            if (srcImg.getWidth(null) > 200 && srcImg.getHeight(null) > 50) {
                int x = 1;
                int y = 1;
                for (int i = 0; i < 1000; i++) {
                    // 设置水印旋转
                    g.rotate(Math.toRadians(degree), (double) x, (double) y);
                    g.drawImage(img, x, y, null);
                    g = buffImg.createGraphics();
                    x += 300;
                    if (x >= srcImg.getWidth(null)) {
                        x = 0;
                        y += 300;
                    }
                    if (x >= (srcImg.getWidth(null) + 200) && (y >= srcImg.getHeight(null) + 100)) {
                        break;
                    }
                }
            } else {
                g.drawImage(img, srcImg.getWidth(null) / 2, srcImg.getHeight(null) / 2, null);
            }
            /*
             * g.drawImage(img, 0, 0, null); g.drawImage(img, 30, 30, null);
			 * g.drawImage(img, 60, 60, null); g.drawImage(img, 90, 90, null);
			 * g.drawImage(img, 120, 120, null); g.drawImage(img, 150, 300,
			 * null);
			 */

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

            g.dispose();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(buffImg, "jpg", bos);
            byte[] data = bos.toByteArray();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 图片转化成base64字符串
    public static String GetImageStr(String url) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = url;// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    // base64字符串转化成图片
    public static boolean GenerateImage(String imgStr, String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
//            String imgFilePath = "C:/Users/Star/Desktop/test22.png";// 新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
