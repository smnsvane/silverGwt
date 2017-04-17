package framework.silver.event;

public class ListenTimeoutHandler implements Listener<ListenTimeoutEvent> {

	@Override
	public void handle(ListenTimeoutEvent event) {
		
		EventBus.inst.fireEvent(new ListenEvent());
	}
}
