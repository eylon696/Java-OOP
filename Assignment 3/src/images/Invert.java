package images;

public class Invert extends ImageDecorator {
	private Image base;
	
	public Invert(Image base) {
		super(base.getWidth(),base.getHeight());
		this.base=base;
	}
	@Override
	public RGB get(int x, int y){
		RGB temp=base.get(x, y);
		return temp.invert();
	}
}
