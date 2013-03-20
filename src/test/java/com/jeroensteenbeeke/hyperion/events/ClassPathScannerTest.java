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
package com.jeroensteenbeeke.hyperion.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.jeroensteenbeeke.hyperion.events.DefaultEventDispatcher.ClasspathScanner;
import com.jeroensteenbeeke.hyperion.events.beans.impl.CounterServiceImpl;
import com.jeroensteenbeeke.hyperion.events.data.TestEvent;
import com.jeroensteenbeeke.hyperion.events.data.TestEventHandler;
import com.jeroensteenbeeke.hyperion.events.data.TestInheritedEventHandler;
import com.jeroensteenbeeke.hyperion.events.data.TestInheritedInterfaceEventHandler;
import com.jeroensteenbeeke.hyperion.events.data.TestSubclassOfInheritedInterfaceEventHandler;

public class ClassPathScannerTest {
	@Test
	public void testMatchesEventHandler() {
		ClasspathScanner scanner = new ClasspathScanner(EventHandler.class);

		List<Class<?>> classes = scanner
				.getComponentClasses("com.jeroensteenbeeke.hyperion.events.data");

		assertTrue(classes.contains(TestEventHandler.class));
	}

	@Test
	public void testGetGenericType() {
		assertEquals("Direct Interface Implementation", TestEvent.class,
				DefaultEventDispatcher.getEventClass(TestEventHandler.class));

		assertEquals("Superclass Interface Implementation", TestEvent.class,
				DefaultEventDispatcher
						.getEventClass(TestInheritedEventHandler.class));

		assertEquals(
				"Super-interface implementation",
				TestEvent.class,
				DefaultEventDispatcher
						.getEventClass(TestInheritedInterfaceEventHandler.class));
		assertEquals(
				"Subclass of super-interface implementation",
				TestEvent.class,
				DefaultEventDispatcher
						.getEventClass(TestSubclassOfInheritedInterfaceEventHandler.class));

		assertNull("Non-implementation",
				DefaultEventDispatcher.getEventClass(CounterServiceImpl.class));
	}
}
