package framework.silver.event;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;

public class ImageLoaded implements Event {
	
	public String imageUrl;
	public Image image;
	public ImageElement imageElement;
	public ImageLoaded(String imageUrl, Image image, ImageElement imageElement) {
		this.imageUrl = imageUrl;
		this.image = image;
		this.imageElement = imageElement;
	}
}
