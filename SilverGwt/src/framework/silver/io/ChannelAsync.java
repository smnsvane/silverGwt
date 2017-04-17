package framework.silver.io;

import framework.silver.event.Event;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChannelAsync {
	
	void request(Event request, AsyncCallback<Event> callback);

	void serverPush(AsyncCallback<Event> callback);
}
