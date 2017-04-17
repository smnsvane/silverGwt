package framework.silver.ui;

import java.util.HashMap;

import framework.silver.event.EventBus;
import framework.silver.event.Listener;
import framework.silver.event.NavigationEvent;

import com.google.gwt.user.client.ui.RootPanel;

public class Presenter implements Listener<NavigationEvent> {
	
	private HashMap<Class<? extends Page>, Page> pages =
			new HashMap<Class<? extends Page>, Page>();
	
	public void registerPage(Page page) {
		Class<? extends Page> pageClass = page.getClass();
		pages.put(pageClass, page);
	}
	
	private Page startPage;
	public void registerStartPage(Page startPage) {
		if (this.startPage != null)
			throw new Error("Start page already registered");
		
		this.startPage = startPage;
		registerPage(startPage);
	}
	
	private RootPanel root;
	public void bindRootPanel(String rootPanelId) {
		root = RootPanel.get(rootPanelId);
	}
	
	public void run() {
		if (startPage == null)
			throw new Error("Start page is missing, use 'registerStartPage' method to set a class that implements "+Page.class);
		navigate(startPage);
	}
	
	public Presenter() {
		// register the navigation listener
		EventBus.inst.addListener(NavigationEvent.class, this);
	}
	
	@Override
	public void handle(NavigationEvent event) {
		
		if (!pages.containsKey(event.pageClass))
			throw new Error("Unknown page id: '"+event.pageClass+"' in "+getClass());
		
		Page page = pages.get(event.pageClass);
		navigate(page);
	}
	
	private Page current;
	private void navigate(Page page) {
		
		if (current != null)
			current.checkOut();
		current = page;
		
		page.checkIn();
		
		root.clear();
		root.add(page.asWidget());
	}
}
