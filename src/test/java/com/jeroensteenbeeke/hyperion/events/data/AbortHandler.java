package com.jeroensteenbeeke.hyperion.events.data;

import com.jeroensteenbeeke.hyperion.events.EventHandler;
import com.jeroensteenbeeke.hyperion.events.EventResult;

public class AbortHandler implements EventHandler<AbortEvent> {

	@Override
	public EventResult onEvent(AbortEvent event) {
		return EventResult.abort("You suck");
	}

}
