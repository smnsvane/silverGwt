package framework.silver.canvas;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class CanvasWidget {
	
	private FlowPanel panel = new FlowPanel();
	public FlowPanel getPanel() { return panel; }
	
	private Context2d context;
	private Canvas canvas;
	public Canvas getCanvas() { return canvas; }
	
	private CanvasRenderer renderer;
	private CanvasBackend backend;
	public void setBackend(CanvasBackend backend) { this.backend = backend; } 
	
	public CanvasWidget(CanvasRenderer renderer) {
		
		panel.getElement().setId(getClass().getName());
		
		// Verify browser have HTML5 Canvas support
		Canvas canvas = Canvas.createIfSupported();
		if (canvas == null) {
			panel.add(new Label("Your browser do not support HTML5 Canvas"));
			return;
		}
		
		this.renderer = renderer;
		
		// initialize canvas
		this.canvas = canvas;
		canvas.setWidth(renderer.getCanvasWidth()+"px");
		canvas.setHeight(renderer.getCanvasHeight()+"px");
		canvas.setCoordinateSpaceWidth(renderer.getCanvasWidth());
		canvas.setCoordinateSpaceHeight(renderer.getCanvasHeight());
		context = canvas.getContext2d();
		panel.add(canvas);
	}
	
	private boolean running = false;
	/**
	 * Start the animation loop
	 */
	public void start() {
		AnimationCallback animCallback = new AnimationCallback() {
			@Override 
			public void execute(double timestamp) {
				
				update();
				
				if (running)
					AnimationScheduler.get().requestAnimationFrame(this, context.getCanvas()); 
			}
		};
		running = true;
		AnimationScheduler.get().requestAnimationFrame(animCallback, context.getCanvas()); 
	}
	public void stop() {
		running = false;
	}
	
	private void update() {
		
		// update CanvasBackend
		if (backend != null)
			backend.update();
		
		// render graphics via the CanvasRenderer
		Context2d backbufferContext = renderer.render();
		
		// Draw back buffer to screen
		context.drawImage(backbufferContext.getCanvas(), 0, 0);
	}
}
