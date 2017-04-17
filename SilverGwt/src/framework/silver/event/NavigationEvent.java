package framework.silver.event;

import framework.silver.ui.Page;

public class NavigationEvent implements Event {

	// needed for rpc
	@SuppressWarnings("unused")
	private NavigationEvent() {}
	
	public Class<? extends Page> pageClass;
	public NavigationEvent(Class<? extends Page> pageId) {
		this.pageClass = pageId;
	}
}
