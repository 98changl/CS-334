import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class A2_Q2_4 implements PlugInFilter {
	public int setup(String arg, ImagePlus im) 
	{
		return DOES_8G;
	}
	
	public void run(ImageProcessor ip)
	{
		int M = ip.getWidth();
		int N = ip.getHeight();
		
		// horizontal blur
		int[][] H = {
			{1, 1, 1, 1, 1, 1, 1}};

		double s = 1.0 / 9;	// sum of filter coefficients
		
		int K = H[0].length / 2;
		int L = H.length / 2;
		
		ImageProcessor copy = ip.duplicate();
		
		for (int u = K; u <= M - K - 1; u++) {
			for (int v = L; v <= N - L - 1; v++) {
				// compute filter result for position (u,v):
				int sum = 0;
				for (int i = -K; i <= K; i++) {
					for (int j = -L; j <= L; j++) {
						int p = copy.getPixel(u + i, v + j);
						// get the corresponding filter coefficient:
						int c = H[j + L][i + K];
						sum = sum + c * p;
					}
				}
				int q = (int) Math.round(s * sum);
				
				// clamp result
				if (q < 0)		q = 0;
				if (q > 255)	q = 255;
				ip.putPixel(u, v, q);
			}
		}
	}
}
