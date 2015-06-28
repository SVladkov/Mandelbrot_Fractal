package net.yaht.pf;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;

import org.apache.commons.math3.complex.Complex;

public class MThread implements Runnable{

	Color[][] imageAsColorArray;
	int startPosition;
	int length;
	static int widthOfImage;
	static int heightOfImage;
	static double precision;
	static double range;
	
	public MThread(Color[][] initialImageAsColorArray, int initialStartPosition, int initialLength,
			int initialWidthOfImage, int initialHeightOfImage, double initialPrecision, double initialRange) {
		this.imageAsColorArray = initialImageAsColorArray;
		this.startPosition = initialStartPosition;
		this.length = initialLength;
		this.widthOfImage = initialWidthOfImage;
		this.heightOfImage = initialHeightOfImage;
		this.precision = initialPrecision;
		this.range = initialRange;
	} 
	
	public static int z_check(Complex c) {
		
		Complex z0 = new Complex(0.0, 0.0);

		Complex z_prev = z0;
		Complex z_i = null;
		int steps = 0;
		Double d = null;
		
		for(int i = 0; i < 640; i++) {
			
			z_i = z_prev.multiply(z_prev).add(c);
			z_prev = z_i;
			
			d = new Double(z_prev.getReal());
			
			if (d.isInfinite() || d.isNaN()) {
				steps = i;
				break;
			}
		}

		return steps;
	}
	
	public void calculateColorOfPixels() {
		int height = heightOfImage;
		int width = widthOfImage;
		
		double ty = 1.0/(height * precision);

		PrintWriter out = new PrintWriter(System.out);
		
		for (int i = 0; i < (int) (height * precision); i++) {
			
			double py = range - 4.0 * ty;
			int py_scr = (int) (Math.abs((py - range)) * ((height - 1) / (2 * range)));
			double tx = 1.0/(width * precision);
			
			for (int j = 0; j < (int) (width * precision); j++) {
				
				double px = -range + 4.0 * tx;
				int px_scr = (int) ((px + range) * (width / 4.0));
				int r = z_check(new Complex(px, py));

				if (r == 0) {
					this.imageAsColorArray[px_scr][py_scr] = Color.RED;
					//bi.setRGB(px_scr, py_scr, 0x6600aa);
				} else {
					this.imageAsColorArray[px_scr][py_scr] = Color.WHITE;
					//bi.setRGB(px_scr, py_scr, 0xffffff);
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
*/
				tx += 1.0/(width * precision);
				
			}
			
			ty += 1.0/(height * precision);
			
		}
	}
	
	public void run() {
		this.calculateColorOfPixels();
	}

}
