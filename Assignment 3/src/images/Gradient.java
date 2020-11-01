package images;

public class Gradient extends BaseImage {
	private RGB start;
	private RGB end;
	//                                      RED          YELLOW
	public Gradient(int width, int height, RGB start, RGB end) {
		super(width,height);
		this.start=start;
		this.end=end;
		
	}
	@Override
	public RGB get(int x, int y) {
              //    YELLOW     RED
		return RGB.mix(end, start, (double)x/getWidth());
	//	return RGB.superpose(end, start);
	}
}
