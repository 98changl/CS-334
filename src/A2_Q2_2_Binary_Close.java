import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;
import imagingbook.pub.morphology.BinaryMorphologyFilter;
import imagingbook.pub.morphology.BinaryMorphologyFilter.OpType;

public class A2_Q2_2_Binary_Close implements PlugInFilter {
	static double radius = 2.0;
	static OpType op = OpType.Close;
	
	public int setup(String arg, ImagePlus imp)
	{
		return DOES_8G;
	}
	
	public void run(ImageProcessor ip)
	{
		BinaryMorphologyFilter bmf = new BinaryMorphologyFilter.Disk(radius);
		bmf.applyTo((ByteProcessor) ip, op);
	}
}
