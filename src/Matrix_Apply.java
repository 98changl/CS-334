import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class Matrix_Apply implements PlugInFilter {
	public int setup(String arg, ImagePlus im) 
	{
		return DOES_8G;	// accepts 8-bits gray scale
	}
	
	public void run(ImageProcessor ip)
	{
		/*
		// Gaussian blur
		double[][] H = {
			{0.00000067, 0.00002292, 0.00019117, 0.00038771, 0.00019117, 0.00002292, 0.00000067},
			{0.00002292, 0.00078633, 0.00655965, 0.01330373, 0.00655965, 0.00078633, 0.00002292},
			{0.00019117, 0.00655965, 0.05472157, 0.11098164, 0.05472157, 0.00655965, 0.00019117},
			{0.00038771, 0.01330373, 0.11098164, 0.22508352, 0.11098164, 0.01330373, 0.00038771},
			{0.00019117, 0.00655965, 0.05472157, 0.11098164, 0.05472157, 0.00655965, 0.00019117},
			{0.00002292, 0.00078633, 0.00655965, 0.01330373, 0.00655965, 0.00078633, 0.00002292},
			{0.00000067, 0.00002292, 0.00019117, 0.00038771, 0.00019117, 0.00002292, 0.00000067}};
		*/
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		// creates test image
		ImagePlus test = IJ.createImage("Test", "8-bit", w, h, 0);
		ImageProcessor testIp = test.getProcessor();
		
		// 3x3 filter matrix
		double[][] H = {
			{0, 0, 0},
			{0, 0, 1},
			{0, 0, 0}};
	
		ImageProcessor copy = ip.duplicate();
		
		for (int u = 1; u <= w - 2; u++) {
			for (int v = 1; v <= h - 2; v++) {
				// compute filter result for position (u,v):
				double sum = 0;
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						int p = copy.getPixel(u + i, v + j);
						// get the corresponding filter coefficient:
						double c = H[j + 1][i + 1];
						sum = sum + c * p;
					}
				}
				int q = (int) Math.round(sum);
				
				if (q > 255)
					q = 255;
				System.out.println(q);
				testIp.putPixel(u, v, q);
				
			}
		}
		
		// display test image
		test.show();
		test.draw();
	}
}
