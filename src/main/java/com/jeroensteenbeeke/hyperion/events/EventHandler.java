package com.jeroensteenbeeke.hyperion.events;

public interface EventHandler<T extends Event<?>> {
	EventResult onEvent(T event);
}
