package framework.silver.io;

import framework.silver.event.Event;
import framework.silver.event.EventBus;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class Callback implements AsyncCallback<Event> {
	
	@Override
	public void onFailure(Throwable caught) {
		caught.printStackTrace();
	}

	@Override
	public void onSuccess(Event response) {
		EventBus.inst.fireEvent(response);
	}
}
