package io.one2story.contextprocesstesterapp.quoter;

public class ProfilingController implements ProfilingControllerMBean {
	private boolean enabled = true;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
