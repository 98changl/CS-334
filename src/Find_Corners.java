/*
float[] hp = {2f/9, 5f/9, 2f/9};

float[] hd = {0.5f, 0, -0.5f};
float[] hb = 
	{1f/64, 6f/64, 15f/64, 20f/64, 15f/64, 6f/64, 1f/64};
FloatProcessor Ix = I.convertToFloatProcessor();
FloatProcessor Iy = I.convertToFloatProcessor();
Filter.convolveX(Ix, hp);
Filter.convolveX(Ix, hd);
Filter.convolveY(Iy, hp);
Filter.convolveY(Iy, hd);
A = ImageMath.sqr(Ix);
B = ImageMath.sqr(Iy);
C = ImageMath.mult(Ix, Iy);

Filter.convolveXY(A, hb);
Filter.convolveXY(B, hb);
Filter.convolveXY(C, hb);
private FloatProcessor makeCrf(float alpha) {
	FloatProcessor Q = new FloatProcessor(M, N);
	final float[] pA = (float[]) A.getPixels();
	final float[] pB = (float[]) B.getPixels();
	final float[] pC = (float[]) C.getPixels();
	final float[] pQ = (float[]) Q.getPixels();
	for (int i = 0; i < M * N; I++) {
		float a = pA[i], b = pB[i], c = pC[i];
		float det = a * b - c * c;
		float trace = a + b;
		pQ[i] = det - alpha * (trace * trace);
	}
	return Q;
}
class Corner implements Comparable<Corner> {
	final float x, y, q;
	
	public Corner (float x, float y, float q) {
		this.x = x;
		this.y = y;
		this.q = q;
	}
	@Override
	public int compareTo(Corner c2) {
		if (this.q > c2.q) return -1;
		if (this.q < c2.q) return 1;
		else return 0;
	}
	
}
List<Corner> collectCorners(FloatProcessor Q, float tH, int border) {
	List<Corner> C = new ArrayList<Corner>();
	for (int v = border; v < N - border; v++) {
		for (int u = border; u < M - border; u++) {
			float q = Q.getf(u, v);
			if (q > tH && isLocalMax(Q, u, v)) {
				Corner c = new Corner(u, v, q);
				C.add(c);
			}
		}
	}
	return C;
}
boolean isLocalMax (FloatProcessor Q, int u, int v) {
	if (u <= 0 || u >= M - 1 || v <= 0 || v >= N - 1) {
		return false;
	}
	else {
		float[] q = (float[]) Q.getPixels();
		int i0 = (v - 1) * M + u;
		int i1 = v * M + u;
		int i2 = (v + 1) * M + u;
		float q0 = q[i1];
		return
				q0 >= q[i0 - 1] && q0 >= q[i0] && q0 >= q[i0 + 1] &&
				q0 >= q[i1 - 1] &&                q0 >= q[i1 + 1] &&
				q0 >= q[i2 - 1] && q0 >= q[i2] && q0 >= q[i2 + 1];
	}
}
List<Corner> cleanupCorners(List<Corner> C, double dmin) {
	double dmin2 = dmin * dmin;
	// sort corners by descending q-value
	Collections.sort(C);
	// we use an array of corners for efficiency reasons
	Corner[] Ca = C.toArray(new Corner[C.size()]);
	List<Corner> Cclean = new ArrayList<Corner>(C.size());
	for (int i = 0; i < Ca.length; i++) {
		Corner c0 = Ca[i];	// get next strongest corner
		if (c0 != null) {
			Cclean.add(c0);
			// delete all remaining corners cj too cloase to c0:
			for (int j = i + 1; j < Ca.length; j++) {
				Corner cj = Ca[j];
				if (cj != null && c0.dist2(cj) < dmin2)
					Ca[j] = null;	// delete corner cj from Ca
			}
		}
	}
	return Cclean;
}
public List<Corner> findCorners() {
	FloatProcessor Q = makeCrf((float)params.alpha);
	List<Corner> corners =
		collectCorners(Q, (float)params.tH, params.border);
	if (params.doCleanUp) {
		corners = cleanupCorners(corners, params.dmin);
	}
	return corners;
}
public class FindCorners implements PlugInFilter {
	
	public void run(ImageProcessor ip) {
		HarrisCornerDetector cd = new HarrisCornerDetector(ip);
		List<Corner> corners = cd.findCorners();
		ColorProcessor R = ip.convertToColorProcessor();
		drawCorners(R, corners);
		(new ImagePlus("Result", R)).show();
	}
	
	void drawCorners(ImageProcessor ip,
						List<Corner> corners) {
		ip.setColor(cornerColor);
		for (Corner c: corners) {
			drawCorner(ip, c);
		}
	}
	
	void drawCorner(ImageProcessor ip, Corner c) {
		int size = cornerSize;
		int x = Math.round(c.getX());
		int y = Math.round(c.getY());
		ip.drawLine(x - size, y, x + size, y);
		ip.drawLine(x, y - size, x, y + size);
	}
}
*/