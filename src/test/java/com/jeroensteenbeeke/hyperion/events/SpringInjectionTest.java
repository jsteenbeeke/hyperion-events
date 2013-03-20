package com.jeroensteenbeeke.hyperion.events;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import com.jeroensteenbeeke.hyperion.events.beans.CounterService;
import com.jeroensteenbeeke.hyperion.events.beans.impl.CounterServiceImpl;
import com.jeroensteenbeeke.hyperion.events.data.TestEvent;
import com.jeroensteenbeeke.hyperion.events.data.TestEventHandler;

public class SpringInjectionTest {
	@Configuration
	public static class TestConfig {
		@Bean
		public IEventDispatcher eventDispatcher() {
			DefaultEventDispatcher dispatcher = new DefaultEventDispatcher();
			dispatcher.setScanPackages(Lists
					.newArrayList("com.jeroensteenbeeke.hyperion.events.data"));

			return dispatcher;
		}

		@Bean
		public CounterService counterService() {
			return new CounterServiceImpl(5);
		}
	}

	private static ApplicationContext testContext = null;

	@BeforeClass
	public static void createApplicationContext() {
		testContext = new AnnotationConfigApplicationContext(TestConfig.class);
	}

	@Test
	public void testDispatcherInitialized() {
		IEventDispatcher dispatcher = testContext
				.getBean(IEventDispatcher.class);

		assertNotNull(dispatcher);
	}

	@Test
	public void testEventPropagated() {
		IEventDispatcher dispatcher = testContext
				.getBean(IEventDispatcher.class);

		dispatcher.dispatchEvent(new TestEvent());

		assertTrue(TestEventHandler.called > 0);
	}

	@AfterClass
	public static void cleanup() {
		testContext = null;
	}
}
