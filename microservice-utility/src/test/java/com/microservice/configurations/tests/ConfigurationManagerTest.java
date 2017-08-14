package com.microservice.configurations.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.microservice.configurations.ConfigurationManager;

import junit.framework.Assert;

public class ConfigurationManagerTest {

	@Test
	public void test() {
		
		//local property
		Assert.assertEquals("Utilities",ConfigurationManager.getInstance().get("ApplicationName"));
		//property from remote server
		
		//has key positive
		Assert.assertTrue(ConfigurationManager.getInstance().hasKey("ApplicationName"));
		//has key negetive
		Assert.assertFalse(ConfigurationManager.getInstance().hasKey("NotExist"));
	}

}
