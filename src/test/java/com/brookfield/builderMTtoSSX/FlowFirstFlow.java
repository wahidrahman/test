package com.brookfield.builderMTtoSSX;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.junit.Test;
import org.mule.api.MuleEvent;

import com.brookfield.hub.*;

public class FlowFirstFlow extends AbstractFunctionalMunitSuite{
	private static final String FLOW_NAME = "test-first-flow";
	
	@Override
	protected String getConfigResources(){
		return super.buildIncludeFlowString("configuration","test-mytestflow");
	}
	
	@Test
	public void testIfRunning() throws Exception
	{
		final MuleEvent event = testEvent(null);
		final MuleEvent result = runFlow(FLOW_NAME,event);
		final String payload = result.getMessage().getPayloadAsString();
		System.out.println(payload);
        assertThat(payload, containsString("100"));
	}
}
