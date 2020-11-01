package images;

public class Mix extends BinaryImageDecorator {
	private double alpha;
	private Image base1;
	private Image base2;

	public Mix(Image base1, Image base2, double alpha) {
		super(Math.max(base1.getWidth(), base2.getWidth()), Math.max(base1.getHeight(), base2.getHeight()));// Using max																								// height
		this.alpha = alpha;
		this.base1 = base1;
		this.base2 = base2;

	}

	@Override
	public RGB get(int x, int y) {
		//If the point inside both images then color is mixed by superpose method of RGB
		if (y <= base1.getHeight() && y <= base2.getHeight() && x <= base1.getWidth() && x <= base2.getWidth())
			return RGB.mix(base1.get(x, y), base2.get(x, y), alpha);
		//If the point is in one of the images return the image's color
		if (y <= base1.getHeight() && x <= base1.getWidth())
			return base1.get(x, y);
		if ( y <= base2.getHeight()&& x <= base2.getWidth())
			return base2.get(x, y);
		//If the point is not in the either of the images return black 
		return RGB.BLACK;
	}

}
