package framework.silver.event;

public class ImageLoad implements Event {
	
	public Listener<ImageLoaded> listener;
	public String imageUrl;
	public ImageLoad(Listener<ImageLoaded> listener, String imageUrl) {
		this.listener = listener;
		this.imageUrl = imageUrl;
	}
}
