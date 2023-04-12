import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

public class A2_Q2_2_Close implements PlugIn {
	public void run(String args)
	{
		ImagePlus im = IJ.getImage();
		ImageProcessor ip = im.getProcessor();
		
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		// morphological close filter
		int[][] H = {
			{1, 1, 1},
			{1, 2, 1},
			{1, 1, 1}};
		
		ImageProcessor copy = ip.duplicate();
		
		for (int u = 1; u <= w - 2; u++) {
			for (int v = 1; v <= h - 2; v++) {
				// compute filter result for position (u,v):
				double min = 0;
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						int p = copy.getPixel(u + i, v + j);
						// get the corresponding filter coefficient:
						double c = H[j + 1][i + 1];
						
						if (c * p < min)
						{
							min = c * p;
						}
					}
				}
				int q = (int) Math.round(min);
				
				if (q > 255)
					q = 255;
			}
		}
	}
}
