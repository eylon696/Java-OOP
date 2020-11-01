package images;

public class TwoColorImage extends BaseImage {
	private RGB zero;
	private RGB one;
	private TwoDFunc func;
	
	public TwoColorImage(int width, int height, RGB zero, RGB one, TwoDFunc func) {
		super(width,height);
		this.zero=zero;
		this.one=one;
		this.func=func;
	}
	
	@Override
	public RGB get(int x, int y){
		double temp;
		temp=func.f((double)x/getWidth(), (double)y/getHeight());
		if(temp>=1)
			return one;
		if(temp<=0)
			return zero;
		//Mix the two colors if the func returned a value between 0 and 1
		return RGB.mix(one, zero , temp);

	}

}
