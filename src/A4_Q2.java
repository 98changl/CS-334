import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import imagingbook.pub.color.quantize.ColorQuantizer;
import imagingbook.pub.color.quantize.MedianCutQuantizer;

public class A4_Q2 implements PlugInFilter {
	
	public int setup(String arg, ImagePlus im) 
	{
		return DOES_RGB + NO_CHANGES;
	}
	
	public void run(ImageProcessor ip)
	{
		ColorProcessor cp = ip.convertToColorProcessor();
		int colors = 128;
		
		// create a quantizer:
		ColorQuantizer q = new MedianCutQuantizer(cp, colors);
		
		// quantize cp to an indexed image
		ByteProcessor idxIp = q.quantize(cp);
		(new ImagePlus("Quantized Index Image with 128 Colors", idxIp)).show();
		
		colors = 64;
		q = new MedianCutQuantizer(cp, colors);
		idxIp = q.quantize(cp);
		(new ImagePlus("Quantized Index Image with 64 Colors", idxIp)).show();
		
		colors = 16;
		q = new MedianCutQuantizer(cp, colors);
		idxIp = q.quantize(cp);
		(new ImagePlus("Quantized Index Image with 16 Colors", idxIp)).show();
	}
}
