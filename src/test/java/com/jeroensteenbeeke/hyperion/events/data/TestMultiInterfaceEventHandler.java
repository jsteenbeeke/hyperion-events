package com.jeroensteenbeeke.hyperion.events.data;

import java.io.Serializable;

import com.jeroensteenbeeke.hyperion.events.EventHandler;
import com.jeroensteenbeeke.hyperion.events.EventResult;

public class TestMultiInterfaceEventHandler implements Serializable,
		Foo<String>, EventHandler<TestEvent> {
	private static final long serialVersionUID = 1L;

	@Override
	public EventResult onEvent(TestEvent event) {

		return EventResult.OK;
	}
}
