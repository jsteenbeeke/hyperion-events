/**
 * Copyright 2013 Jeroen Steenbeeke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

		return EventResult.ok();
	}

}
