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
