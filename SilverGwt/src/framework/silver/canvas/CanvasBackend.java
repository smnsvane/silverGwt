/**
 * contains the back-end that do not render or calculate input events
 */

package framework.silver.canvas;

public interface CanvasBackend {
	
	/**
	 * called by the CanvasWidget, at every render loop
	 */
	public void update();
}
