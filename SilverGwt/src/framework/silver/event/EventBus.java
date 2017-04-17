package framework.silver.event;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings({"rawtypes", "unchecked"})
public final class EventBus {
	
	private EventBus() {}
	public static final EventBus inst = new EventBus();
	
	private HashMap<Class<? extends Event>, ArrayList<Listener>> listenerMap =
			new HashMap<Class<? extends Event>, ArrayList<Listener>>();
	
	public String printListeners() {
		StringBuilder sb = new StringBuilder();
		for (Class<? extends Event> key : listenerMap.keySet())
			for (Listener l : listenerMap.get(key))
				sb.append(key+" -> "+l+"\n");
		return sb.toString();
	}
	
	public void addListener(Class<? extends Event> eventClass, Listener listener) {
		
		// init handler list if there is no list
		if (!listenerMap.containsKey(eventClass))
			listenerMap.put(eventClass, new ArrayList());
		
		ArrayList<Listener> listeners = listenerMap.get(eventClass);
		listeners.add(listener);
	}
	
	public void fireEvent(Event event) {
		
		if (listenerMap.containsKey(event.getClass())) {
			ArrayList<Listener> handlers = listenerMap.get(event.getClass());
			if (!handlers.isEmpty())
				for (Listener handler : handlers)
					handler.handle(event);
			else
				System.err.println("Empty listener list for "+event.getClass());
		} else
			System.err.println("Missing listener list for "+event.getClass());
	}
}
