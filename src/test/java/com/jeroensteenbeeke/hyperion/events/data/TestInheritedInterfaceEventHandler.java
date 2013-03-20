package com.jeroensteenbeeke.hyperion.events.data;

import com.jeroensteenbeeke.hyperion.events.EventResult;

public class TestInheritedInterfaceEventHandler implements EventHandlerSubType {
	@Override
	public EventResult onEvent(TestEvent event) {
		return EventResult.OK;
	}
}
