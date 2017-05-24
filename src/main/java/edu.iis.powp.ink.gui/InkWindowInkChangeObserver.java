package edu.iis.powp.ink;

import edu.iis.powp.observer.Subscriber;

public class InkWindowInkChangeObserver implements Subscriber {

	private InkManagerWindow inkManagerWindow;
	
	public InkWindowInkChangeObserver(InkManagerWindow inkManagerWindow) {
		super();
		this.inkManagerWindow = inkManagerWindow;
	}

	public String toString() {
		return "Current command change observer for command manager window";
	}

	@Override
	public void update() {
		inkManagerWindow.updateCurrentInkField();	
	}

}
