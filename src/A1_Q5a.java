import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

public class A1_Q5a implements PlugIn {
	
	public void run(String arg) {
		IJ.newImage("Random", "RGB", 640, 480, 0);
		
		ImagePlus im = IJ.getImage();
		ImageProcessor ip = im.getProcessor();
		int w = ip.getWidth();
		int h = ip.getHeight();
		int[] RGB = new int[3];
		
		// iterate
		for (int u = 0; u < w; u++)
		{
			for (int v = 0; v < h; v++)
			{
				RGB[0] = (int) (256.0 * Math.random());
				RGB[1] = (int) (256.0 * Math.random());
				RGB[2] = (int) (256.0 * Math.random());
				ip.putPixel(u, v, RGB);
			}
		}
		
		im.updateAndDraw();	// redraw the modified image
	}
	
}