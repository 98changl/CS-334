import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class A4_Q1_1 implements PlugInFilter {
	public int setup(String arg, ImagePlus im) 
	{
		return DOES_RGB;
	}
	
	public void run(ImageProcessor ip)
	{
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		int[] RGB = new int[3];
		
		// iterate
		for (int u = 0; u < w; u++)
		{
			for (int v = 0; v < h; v++)
			{
				ip.getPixel(u, v, RGB);
			}
		}
	}
}
