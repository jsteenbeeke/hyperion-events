package com.jeroensteenbeeke.hyperion.events;

public interface IEventDispatcher {
	void dispatchEvent(Event<?> event);
}
