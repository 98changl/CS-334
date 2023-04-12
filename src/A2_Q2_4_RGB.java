import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class A2_Q2_4_RGB implements PlugInFilter {
	public int setup(String arg, ImagePlus im) 
	{
		return DOES_RGB;
	}
	
	public void run(ImageProcessor ip)
	{
		int M = ip.getWidth();
		int N = ip.getHeight();
		int[] RGB = new int[3];
		
		// horizontal blur
		int[][] H = {
			{0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0},
			{1, 1, 1, 1, 1, 1, 1},
			{0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0}};

		double s = 1.0 / 9;	// sum of filter coefficients
		
		int K = H[0].length / 2;
		int L = H.length / 2;
		
		ImageProcessor copy = ip.duplicate();
		
		for (int u = K; u <= M - K - 1; u++) {
			for (int v = L; v <= N - L - 1; v++) {
				// compute filter result for position (u,v):
				int sumR = 0;
				int sumG = 0;
				int sumB = 0;
				for (int i = -K; i <= K; i++) {
					for (int j = -L; j <= L; j++) {
						copy.getPixel(u + i, v + j, RGB);
						// get the corresponding filter coefficient:
						int c = H[j + L][i + K];
						sumR = sumR + c * RGB[0];
						sumG = sumG + c * RGB[1];
						sumB = sumB + c * RGB[2];
					}
				}
				
				RGB[0] = getPixelValue(s, sumR);
				RGB[1] = getPixelValue(s, sumG);
				RGB[2] = getPixelValue(s, sumB);
				ip.putPixel(u, v, RGB);
			}
		}
	}
	
	int getPixelValue(double s, int sum)
	{
		int q = (int) Math.round(s * sum);
		
		// clamp result
		if (q < 0)		q = 0;
		if (q > 255)	q = 255;
		return q;
	}
}
