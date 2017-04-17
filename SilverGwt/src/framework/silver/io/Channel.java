package framework.silver.io;

import framework.silver.event.Event;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("channel")
public interface Channel extends RemoteService {
	
	Event request(Event request) throws IllegalArgumentException;

	Event serverPush();
}
