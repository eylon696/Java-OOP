package images;

public abstract class ImageDecorator implements Image {
	private int width;
	private int height;
	
	public ImageDecorator (int width,int height) {
		this.width=width;
		this.height=height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public abstract RGB get(int x, int y); 

}
