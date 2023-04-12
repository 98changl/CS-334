import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.PlugIn;
import ij.process.Blitter;
import ij.process.ImageProcessor;
import imagingbook.lib.ij.IjUtils;

public class A2_Q2_3 implements PlugIn {
	static double alpha = 0.1;
	
	public void run(String args)
	{
		runDialog();
		ImagePlus[] openImages = IjUtils.getOpenImages(true);
		System.out.println(alpha);
		ImageProcessor ip = openImages[0].getProcessor();
		ip = recursiveBlend(openImages, 1, ip);
		openImages[0].updateAndDraw();
	}
	
	ImageProcessor recursiveBlend(ImagePlus[] openImages, int i, ImageProcessor ipBG)
	{
		// end of recursion
		if (i >= openImages.length) {
			return ipBG;
		}
		
		ImageProcessor ipFG = openImages[i].getProcessor();
		ipFG = ipFG.duplicate();
		ipFG.multiply(1 - alpha);
		ipBG.multiply(alpha);
		ipBG.copyBits(ipFG, 0, 0, Blitter.ADD);
		
		return recursiveBlend(openImages, i + 1, ipBG);
	}
	
	void runDialog()
	{
		GenericDialog gd = new GenericDialog("Recursive Blending");
		gd.addNumericField("Alpha value:", alpha, 2);
		gd.showDialog();
		alpha = gd.getNextNumber();
	}
}
