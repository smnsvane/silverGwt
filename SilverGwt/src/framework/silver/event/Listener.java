package framework.silver.event;

public interface Listener<E extends Event> {
	void handle(E event);
}
