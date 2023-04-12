import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class A4_Q1_2 implements PlugInFilter {
	public int setup(String arg, ImagePlus im) 
	{
		return DOES_RGB;
	}
	
	public void run(ImageProcessor ip)
	{
		int w = ip.getWidth();
		int h = ip.getHeight();
		int size = w * h;
		
		int[] RGB = new int[3];
		double[] hue = new double[size];
		double[] saturation = new double[size];
		double[] value = new double[size];
		
		// iterate
		int index = 0;
		for (int u = 0; u < w; u++)
		{
			for (int v = 0; v < h; v++)
			{
				ip.getPixel(u, v, RGB);
				
				double red = RGB[0] / 255.0;
				double green = RGB[1] / 255.0;
				double blue = RGB[2] / 255.0;
				
				double max = Math.max(red, Math.max(green, blue));
				double min = Math.min(red, Math.min(green, blue));
				double diff = max - min;
				
				hue[index] = calcHue(max, min, diff, red, green, blue);
				saturation[index] = calcSaturation(max, diff);
				value[index] = max * 100;
				
				System.out.println("Hue:" + hue + " Saturation:" + saturation + " Value:" + value);
				index++;
			}
		}
	}
	
	private double calcHue(double max, double min, double diff, double red, double green, double blue) {
		double hue = -1;
		
		if (max == min) {
			hue = 0;
		} else if (max == red) {
			hue = (60 * ((green - blue) / diff) + 360) % 360;
		} else if (max == green) {
			hue = (60 * ((blue - red) / diff) + 360) % 360;
		} else if (max == blue) {
			hue = (60 * ((red - green) / diff) + 360) % 360;
		}
		return hue;
	}
	
	private double calcSaturation(double max, double diff) {
		double saturation = -1;
		if (max == 0) {
			saturation = 0;
		} else {
			saturation = (diff / max) * 100;
		}
		return saturation;
	}
	
	private double max(int[] RGB) {
		double max = (double) RGB[0];
		for (int i = 1; i < RGB.length; i++) {
			if (RGB[i] > (int) max) {
				max = (double) RGB[i];
			}
		}
		return max;
	}
}
