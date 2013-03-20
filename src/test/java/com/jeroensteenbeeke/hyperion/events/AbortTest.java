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
import com.jeroensteenbeeke.hyperion.events.data.AbortEvent;

public class AbortTest {
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

	@Test(expected = RuntimeException.class)
	public void testAbort() {
		IEventDispatcher dispatcher = testContext
				.getBean(IEventDispatcher.class);

		dispatcher.dispatchEvent(new AbortEvent());
	}

	@AfterClass
	public static void cleanup() {
		testContext = null;
	}
}
