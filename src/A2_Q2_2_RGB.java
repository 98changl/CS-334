import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import imagingbook.lib.ij.IjUtils;

public class A2_Q2_2_RGB implements PlugIn {

	public void run(String args)
	{
		ImagePlus[] openImages = IjUtils.getOpenImages(true);
		ImageProcessor ip1 = openImages[0].getProcessor();
		ImageProcessor ip2;
		
		int w = ip1.getWidth();
		int h = ip1.getHeight();
		int[] RGB1 = new int[3];
		int[] RGB2 = new int[3];
		
		// absolute difference image
		ImagePlus diff = IJ.createImage("d", "8-bit", w, h, 0);
		ImageProcessor dp = diff.getProcessor();
		
		// iterate through each image
		for (int i = 1; i < openImages.length; i++)
		{
			ip1 = openImages[i - 1].getProcessor();
			ip2 = openImages[i].getProcessor();
			System.out.println("Image i: " + openImages[i].getTitle());
			
			// point operation for absolute difference
			for (int u = 0; u < w; u++)
			{
				for (int v = 0; v < h; v++)
				{
					ip1.getPixel(u, v, RGB1);
					ip2.getPixel(u, v, RGB2);
					
					
					
					int d = (int) (Math.abs(RGB1[0] - RGB2[0]) + Math.abs(RGB1[1] - RGB2[1]) 
							+ Math.abs(RGB1[2] - RGB2[2])) / 3;
					
					if (i > 1)
					{
						d = d + dp.getPixel(u, v);
					}
					
					dp.putPixel(u, v, d);
				}
			}
		}
		
		// draw the absolute difference image
		diff.draw();
		diff.show();
		
		// plot the histogram for the absolute difference
		IJ.run(diff, "Histogram", "");
		
		// visualize the absolute difference
		int[] H = dp.getHistogram();
		ImagePlus stretch = IJ.createImage("d-stretch", "8-bit", w, h, 0);
		ImageProcessor dsp = stretch.getProcessor();
		int hi = getMax(H);
		int lo = getMin(H);
		
		// point operation for range stretching
		for (int u = 0; u < w; u++)
		{
			for (int v = 0; v < h; v++)
			{
				int a = dp.getPixel(u, v);
				int d = (int) Math.round((a - lo) * (255.0 / (hi - lo)));
				
				if (d < 0)
					d = 0;
				
				if (d > 255)
					d = 255;
				
				dsp.putPixel(u, v, d);
			}
		}
		
		// draw the stretched visualization
		stretch.draw();
		stretch.show();
	}
	
	// get the minimum pixel value in the array
	int getMin(int[] H)
	{
		int minPixel = 0;
		int min = 0;
		
		for (int i = 0; i < H.length; i++)
		{
			if (H[i] > minPixel)
			{
				minPixel = H[i];
				min = i;
			}
		}
		return min;
	}
	
	// get the minimum pixel value in the array
	int getMax(int[] H)
	{
		int maxPixel = 1000;
		int max = 256;
		
		for (int i = 0; i < H.length; i++)
		{
			if (H[i] < maxPixel)
			{
				maxPixel = H[i];
				max = i;
			}
		}
		return max;
	}
}
