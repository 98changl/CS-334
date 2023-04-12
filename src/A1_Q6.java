import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class A1_Q6 implements PlugInFilter {
	
	public int setup(String arg, ImagePlus im) 
	{
		return DOES_RGB;
	}
	
	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();
		
		int red = 0, green = 0, blue = 0, white = 0, black = 0;
		int[] RGB = new int[3];
		
		// iterate
		for (int u = 0; u < w; u++)
		{
			for (int v = 0; v < h; v++)
			{
				ip.getPixel(u, v, RGB);
				
				if (isRed(RGB) == true)
					red++;
				
				if (isGreen(RGB) == true)
					green++;
				
				if (isBlue(RGB) == true)
					blue++;
				
				if (isWhite(RGB) == true)
					white++;
				
				if (isBlack(RGB) == true)
					black++;
			}
		}
		
		GenericDialog gd = new GenericDialog("Results");
		
		// red
		String msg = "There are '";
		msg = msg.concat(Integer.toString(red));
		msg = msg.concat("' pure red pixels.");
		gd.addMessage(msg);
		
		// green
		msg = "There are '";
		msg = msg.concat(Integer.toString(green));
		msg = msg.concat("' pure green pixels.");
		gd.addMessage(msg);	
		
		// blue
		msg = "There are '";
		msg = msg.concat(Integer.toString(blue));
		msg = msg.concat("' pure blue pixels.");
		gd.addMessage(msg);	
		
		// white
		msg = "There are '";
		msg = msg.concat(Integer.toString(white));
		msg = msg.concat("' pure white pixels.");
		gd.addMessage(msg);	
		
		// black
		msg = "There are '";
		msg = msg.concat(Integer.toString(black));
		msg = msg.concat("' pure black pixels.");
		gd.addMessage(msg);	
		
		gd.showDialog();
	}
	
	private boolean isRed(int[] RGB) {
		if (RGB[0] == 255 && RGB[1] == 0 && RGB[2] == 0) {
			return true;
		}
		return false;
	}
	
	private boolean isGreen(int[] RGB) {
		if (RGB[0] == 0 && RGB[1] == 255 && RGB[2] == 0) {
			return true;
		}
		return false;
	}
	
	private boolean isBlue(int[] RGB) {
		if (RGB[0] == 0 && RGB[1] == 0 && RGB[2] == 255) {
			return true;
		}
		return false;
	}
	
	private boolean isWhite(int[] RGB) {
		if (RGB[0] == 255 && RGB[1] == 255 && RGB[2] == 255) {
			return true;
		}
		return false;
	}
	
	private boolean isBlack(int[] RGB) {
		if (RGB[0] == 0 && RGB[1] == 0 && RGB[2] == 0) {
			return true;
		}
		return false;
	}
}
