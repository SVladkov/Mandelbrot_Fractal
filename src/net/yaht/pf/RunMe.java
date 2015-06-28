package net.yaht.pf;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import org.apache.commons.math3.complex.Complex;

public class RunMe {

	
	
	public static void main(String[] args) {

		int width = 1041;
		int height = 1081;
		double precision = 1.8;
		double range = 2;
		
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2d = bi.createGraphics();
		g2d.setColor(Color.RED);
		g2d.fillRect(0, 0, width, height);

		PrintWriter out = new PrintWriter(System.out);
		
		Color[][] imageAsColorArray = new Color[width][height];
		
		MThread test = new MThread(imageAsColorArray, 0, width*height, width, height, precision, range);
		test.run();
		
		for (int row=0; row<height; row++) {
			for (int col=0; col<width; col++) {
				int color;
				if (imageAsColorArray[col][row] == Color.RED) {
					color = 0xff0000;
				} else {
					color = 0xffffff;
				}
				
				bi.setRGB(col, row, color);
			}
		}
		
		/*double ty = 1.0/(height * precision);

		
		for (int i = 0; i < (int) (height * precision); i++) {
			
			double py = range - 4.0 * ty;
			int py_scr = (int) (Math.abs((py - range)) * ((height - 1) / (2 * range)));
			double tx = 1.0/(width * precision);
			
			for (int j = 0; j < (int) (width * precision); j++) {
				
				double px = -range + 4.0 * tx;
				int px_scr = (int) ((px + range) * (width / 4.0));
				int r = z_check(new Complex(px, py));
				
				//System.out.println(r);

				if (r == 0) { // inside ...
					bi.setRGB(px_scr, py_scr, 0x6600aa);
				} else {
					bi.setRGB(px_scr, py_scr, 0xffffff);
				}
				
				// color
/*				if (r == 0) { // inside ...
					bi.setRGB(px_scr, py_scr, 0x00ff00);
				} else if (r <= 10) { // outside ... (rapid move)
					bi.setRGB(px_scr, py_scr, 0xFFFFFF);
					
				// close to inside cases ...
				// } else if (10 < r && r <= 50) {
				// bi.setRGB(px_scr, py_scr, 0x0033EE);
				} else if (r == 11) {
					bi.setRGB(px_scr, py_scr, 0x0000ff);
				} else if (r == 12) {
					bi.setRGB(px_scr, py_scr, 0x0000ee);
				} else if (r == 13) {
					bi.setRGB(px_scr, py_scr, 0x0000dd);
				} else if (r == 14) {
					bi.setRGB(px_scr, py_scr, 0x0000cc);
				} else if (r == 15) {
					bi.setRGB(px_scr, py_scr, 0x0000bb);
				} else if (r == 16) {
					bi.setRGB(px_scr, py_scr, 0x0000aa);
				} else if (r == 17) {
					bi.setRGB(px_scr, py_scr, 0x000099);
				} else if (r == 18) {
					bi.setRGB(px_scr, py_scr, 0x000088);
				} else if (r == 19) {
					bi.setRGB(px_scr, py_scr, 0x000077);
				} else if (r == 20) {
					bi.setRGB(px_scr, py_scr, 0x000066);
				} else if (20 < r && r <= 30) {
					bi.setRGB(px_scr, py_scr, 0x666600);
				} else if (30 < r && r <= 40) {
					bi.setRGB(px_scr, py_scr, 0x777700);
				} else if (40 < r && r <= 50) {
					bi.setRGB(px_scr, py_scr, 0x888800);
				} else if (50 < r && r <= 100) {
					bi.setRGB(px_scr, py_scr, 0x999900);
				} else if (100 < r && r <= 150) {
					bi.setRGB(px_scr, py_scr, 0xaaaa00);
				} else if (150 < r && r <= 150) {
					bi.setRGB(px_scr, py_scr, 0xbbbb00);
				} else if (200 < r && r <= 150) {
					bi.setRGB(px_scr, py_scr, 0xcccc00);
				} else if (350 < r && r <= 300) {
					bi.setRGB(px_scr, py_scr, 0xdddd00);
				} else {
					bi.setRGB(px_scr, py_scr, 0xeeee00);
				}

				tx += 1.0/(width * precision);
				
			}
			
			ty += 1.0/(height * precision);
			
		}*/

		bi.setRGB(width/2, height/2, 0xffffff);
		
		g2d.setColor(Color.YELLOW);
		g2d.drawRect(0, 0, width - 2, height - 2);
		
		try {
			ImageIO.write(bi, "PNG", new File("SeeMe-2x2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.println("done.\n");
		
		out.flush();
		out.close();

	}

}
