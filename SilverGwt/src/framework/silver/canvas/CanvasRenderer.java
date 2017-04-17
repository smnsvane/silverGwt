package framework.silver.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;

public abstract class CanvasRenderer {
	
	private Context2d context;
	public Context2d getContext() { return context; }
	
	private int canvasWidth, canvasHeight;
	public int getCanvasWidth() { return canvasWidth; }
	public int getCanvasHeight() { return canvasHeight; }

	public CanvasRenderer(int canvasWidth, int canvasHeight) {
		
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		
		// initialize canvas back buffer
		Canvas backbufferCanvas = Canvas.createIfSupported();
		backbufferCanvas.setCoordinateSpaceWidth(canvasWidth);
		backbufferCanvas.setCoordinateSpaceHeight(canvasHeight);
		context = backbufferCanvas.getContext2d();
	}
	
	public abstract Context2d render();
}
