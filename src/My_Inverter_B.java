import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

public class My_Inverter_B implements PlugIn {

	public void run(String args)
	{
		ImagePlus im = IJ.getImage();
		
		if (im.getType() != ImagePlus.GRAY8)
		{
			IJ.error("8-bit grayscale image required!");
		}
		
		ImageProcessor ip = im.getProcessor();
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		// iterate
		for (int u = 0; u < w; u++)
		{
			for (int v = 0; v < h; v++)
			{
				int p = ip.get(u, v);
				ip.set(u, v, 255-p);
			}
		}
		
		im.updateAndDraw();	// redraw the modified image
	}
}
