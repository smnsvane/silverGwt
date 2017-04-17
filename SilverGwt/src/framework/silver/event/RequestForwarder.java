package framework.silver.event;


import framework.silver.io.ChannelAsync;
import framework.silver.io.Callback;
import framework.silver.io.Channel;

import com.google.gwt.core.client.GWT;

public class RequestForwarder implements Listener<Event> {
	
	private final ChannelAsync endpoint = GWT.create(Channel.class);
	
	@Override
	public void handle(Event request) {
		endpoint.request(request, new Callback());
	}
}
