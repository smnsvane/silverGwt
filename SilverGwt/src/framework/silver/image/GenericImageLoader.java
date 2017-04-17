/**
 * eager loading of images can be done via the extending class
 */

package framework.silver.image;

import java.util.HashMap;

import framework.silver.event.EventBus;
import framework.silver.event.ImageLoad;
import framework.silver.event.ImageLoaded;
import framework.silver.event.Listener;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public abstract class GenericImageLoader implements Listener<ImageLoad> {
	
	private HashMap<String, Image> images = new HashMap<String, Image>();
	private HashMap<String, ImageElement> imageElements = new HashMap<String, ImageElement>();
	
	public GenericImageLoader() {
		EventBus.inst.addListener(ImageLoad.class, this);
	}
	
	@Override
	public void handle(ImageLoad event) {
		String urlKey = event.imageUrl;
		if (!images.containsKey(urlKey))
			load(urlKey);
		EventBus.inst.fireEvent(new ImageLoaded(urlKey, images.get(urlKey), imageElements.get(urlKey)));
	}
	
	public void load(final String imageUrl) {
		final Image img = new Image(imageUrl);
		
		// bind image to to page to force load
		img.setVisible(false);
		RootPanel.get().add(img);
		
		img.addLoadHandler(new LoadHandler() {
			@Override
			public void onLoad(LoadEvent event) {
				
				// store image
				images.put(imageUrl, img);
				
				// convert image to image element
				ImageElement imgElem = (ImageElement) img.getElement().cast();
				
				// store image element
				imageElements.put(imageUrl, imgElem);
				
				// remove image to prevent memory leak
				RootPanel.get().remove(img);
			}
		});
	}
}
