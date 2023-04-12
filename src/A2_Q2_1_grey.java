import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class A2_Q2_1_grey implements PlugInFilter {
	static double alpha = 1.75;
	
	public int setup(String arg, ImagePlus im) 
	{
		return DOES_8G;	// accepts 8-bits gray scale
	}
	
	public void run(ImageProcessor ip)
	{
		ImageProcessor copy = ip.duplicate();
		
		int w = ip.getWidth();
		int h = ip.getHeight();
		
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
						p = copy.getPixel(u + i, v + j);
						// get the corresponding filter coefficient:
						double c = H[j + 1][i + 1];
						sum = sum + c * p;
					}
				}
				
				// edge highlight
				int q = (int) Math.round(sum);
				if (q < -15) { // determines if pixel is an edge
					int newP = p;
					newP = (int) Math.round(p * alpha);
					
					if (newP > 255) {
						newP = 255;
					}
					ip.putPixel(u, v, newP);
				}
			}
		}
	}
}
