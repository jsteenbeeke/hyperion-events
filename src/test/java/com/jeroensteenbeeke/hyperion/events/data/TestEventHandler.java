package com.jeroensteenbeeke.hyperion.events.data;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeroensteenbeeke.hyperion.events.EventHandler;
import com.jeroensteenbeeke.hyperion.events.EventResult;
import com.jeroensteenbeeke.hyperion.events.beans.CounterService;

public class TestEventHandler implements EventHandler<TestEvent> {
	public static int called = 0;

	@Autowired
	private CounterService counterService;

	@Override
	public EventResult onEvent(TestEvent event) {
		called += counterService.getNumberOfBeans();

		return EventResult.OK;
	}

}
