package images;

public class Filter extends ImageDecorator {
	private Image base;
	private RGB filter;
	
	public Filter(Image base, RGB filter) {
		super(base.getWidth(),base.getHeight());
		this.base=base;
		this.filter=filter;
	}
	@Override
	public RGB get(int x, int y){
		RGB temp =base.get(x, y);
		return temp.filter(filter);
	}

}
