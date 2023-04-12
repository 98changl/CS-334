import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class A2_Q2_1_color implements PlugInFilter {
	static double alpha = 1.75;
	
	public int setup(String arg, ImagePlus im) 
	{
		return DOES_RGB;
	}
	
	public void run(ImageProcessor ip)
	{
		//ImageProcessor copy = ip.duplicate();
		
		int w = ip.getWidth();
		int h = ip.getHeight();
		int[] RGB = new int[3];
		int[][] grey = new int[w][h];
		
		// converts image from RGB to grey-scale
		for (int u = 0; u < w; u++)
		{
			for (int v = 0; v < h; v++)
			{
				ip.getPixel(u, v, RGB);
				int g = (RGB[0] + RGB[1] + RGB[2]) / 3;
				grey[u][v] = g;
			}
		}
		
		// 3x3 filter matrix
		double[][] H = {
				{0, -1, 0},
				{-1, 4, -1},
				{0, -1, 0}};
		
		// iterate
		for (int u = 1; u <= w - 2; u++) {
			for (int v = 1; v <= h - 2; v++) {
				int p = 0;
				
				// compute filter result for position (u,v):
				double sum = 0;
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						p = grey[u + i][v + j];
						// get the corresponding filter coefficient:
						double c = H[j + 1][i + 1];
						sum = sum + c * p;
					}
				}
				
				// edge highlight
				int q = (int) Math.round(sum);
				if (q < -15) { // determines if pixel is an edge
					ip.getPixel(u, v, RGB);
					
					RGB[0] = (int) Math.round(RGB[0] * alpha);
					if (RGB[0] > 255)
						RGB[0] = 255;
					
					RGB[1] = (int) Math.round(RGB[1] * alpha);
					if (RGB[1] > 255)
						RGB[1] = 255;
					
					RGB[2] = (int) Math.round(RGB[2] * alpha);
					if (RGB[2] > 255)
						RGB[2] = 255;
					
					ip.putPixel(u, v, RGB);
				}
			}
		}					
	}
}
