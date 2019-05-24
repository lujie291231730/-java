package com.seed.utils;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

public class ImageUtils {

	public static void CreatImageThumb(String imagefile, int thumbwidth, int thumbheight, int quality) {
		CreatImageThumb(imagefile, "s_" + imagefile, thumbwidth, thumbheight, quality);
	}
	
	public static void CreatImageThumb(String imagefile, String targetfile, int thumbwidth, int thumbheight, int quality) {
		try {
			Image image = Toolkit.getDefaultToolkit().getImage(imagefile);
			MediaTracker mt = new MediaTracker(new Container());
			mt.addImage(image, 0);
			mt.waitForID(0);
			
			// use this to test for errors at this point
			System.out.println(mt.isErrorAny());
			
			double thumbratio = (double)thumbwidth / (double)thumbheight;
			
			int imagewidth = image.getWidth(null);
			int imageheight = image.getHeight(null);
			
			double imageratio = (double)imagewidth / (double)imageheight;
			
			if (thumbratio < imageratio)
				thumbheight = (int)(thumbwidth / imageratio);
			else
				thumbwidth = (int)(thumbheight * imageratio);
			
			BufferedImage thumbimage = new BufferedImage(thumbwidth, thumbheight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = thumbimage.createGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(image, 0, 0, thumbwidth, thumbheight, null);
			
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(targetfile));
			
			ImageIO.write(thumbimage, targetfile.substring(targetfile.lastIndexOf(".") + 1), out);
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
