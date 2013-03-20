package com.jeroensteenbeeke.hyperion.events.beans.impl;

import com.jeroensteenbeeke.hyperion.events.beans.CounterService;

public class CounterServiceImpl implements CounterService {
	private final int beans;

	public CounterServiceImpl(int beans) {
		super();
		this.beans = beans;
	}

	@Override
	public int getNumberOfBeans() {
		return beans;
	}

}
