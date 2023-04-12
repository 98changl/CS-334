import java.util.Random;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

public class A1_Q5b implements PlugIn {
	
	public void run(String arg) {
		IJ.newImage("RandomGaussian", "RGB", 640, 480, 0);
		
		ImagePlus im = IJ.getImage();
		ImageProcessor ip = im.getProcessor();
		int w = ip.getWidth();
		int h = ip.getHeight();
		int[] RGB = new int[3];
		Random r = new Random();
		
		// iterate
		for (int u = 0; u < w; u++)
		{
			for (int v = 0; v < h; v++)
			{
				RGB[0] = (int) Math.round(r.nextGaussian() * 50 + 150);
				RGB[1] = (int) Math.round(r.nextGaussian() * 50 + 150);
				RGB[2] = (int) Math.round(r.nextGaussian() * 50 + 150);
				ip.putPixel(u, v, RGB);
			}
		}
		
		im.updateAndDraw();	// redraw the modified image
	}
	
}