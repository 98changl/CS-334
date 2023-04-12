import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import imagingbook.lib.ij.IjUtils;

public class A2_Q2_2_Visualize_RGB implements PlugIn {
	
	public void run(String args)
	{
		ImagePlus[] openImages = IjUtils.getOpenImages(true);
		ImageProcessor mask = openImages[0].getProcessor();
		ImageProcessor ip = openImages[1].getProcessor();
		
		System.out.println("Image mask: " + openImages[0].getTitle());
		System.out.println("Image ip: " + openImages[1].getTitle());
		
		int w = ip.getWidth();
		int h = ip.getHeight();
		int[] RGB = new int[3];

		// visualization image
		ImagePlus im = IJ.createImage("m", "RGB", w, h, 0);
		ImageProcessor result = im.getProcessor();
		
		// iterate
		for (int u = 0; u < w; u++)
		{
			for (int v = 0; v < h; v++)
			{
				int m = mask.getPixel(u, v);
				if (m != 0)
				{
					ip.getPixel(u, v, RGB);
				}
				else
				{
					RGB[0] = 0;
					RGB[1] = 0;
					RGB[2] = 0;
				}
				
				result.putPixel(u, v, RGB);
			}
		}
		
		im.draw();
		im.show();
	}
	
}
