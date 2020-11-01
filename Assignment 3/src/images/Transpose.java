package images;

public class Transpose extends ImageDecorator {
	private Image base;

	public Transpose(Image base) {
		super(base.getHeight(), base.getWidth());
		this.base = base;
	}

	//This time we send to get y,x and not x,y
	@Override
	public RGB get(int x, int y) {
		RGB temp=base.get(y, x);
		return temp;
	}

}
