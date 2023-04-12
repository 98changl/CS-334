import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import imagingbook.lib.ij.IjUtils;

public class A2_Q2_2_Visualize implements PlugIn {
	
	public void run(String args)
	{
		ImagePlus[] openImages = IjUtils.getOpenImages(true);
		ImageProcessor mask = openImages[0].getProcessor();
		ImageProcessor ip = openImages[1].getProcessor();
		
		System.out.println("Image mask: " + openImages[0].getTitle());
		System.out.println("Image ip: " + openImages[1].getTitle());
		
		int w = ip.getWidth();
		int h = ip.getHeight();

		// visualization image
		ImagePlus im = IJ.createImage("m", "8-bit", w, h, 0);
		ImageProcessor result = im.getProcessor();
		
		// iterate
		for (int u = 0; u < w; u++)
		{
			for (int v = 0; v < h; v++)
			{
				int m = mask.getPixel(u, v);
				int d = 0;
				if (m != 0)
				{
					d = ip.getPixel(u, v);
				}
				
				result.putPixel(u, v, d);
			}
		}
		
		im.draw();
		im.show();
	}
	
}
