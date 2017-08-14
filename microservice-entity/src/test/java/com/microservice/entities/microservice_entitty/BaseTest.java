package com.microservice.entities.microservice_entitty;

import java.util.Map;

import org.junit.Test;

import com.microservice.entities.Base;
import com.microservice.entities.microservice_entitty.base.TestEntity;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * This class is used to test the Base
 * @author gaurav
 *
 */
public class BaseTest extends TestCase {

	
	@Test
	public void testToJSONFromJSON() {
		TestEntity testEntity = new TestEntity();
		String json1 = Base.toJSON(testEntity);
		TestEntity newTestEntity = Base.fromJSON(json1, TestEntity.class);
		String json2 =  Base.toJSON(newTestEntity);
		Assert.assertEquals(json1, json2);
		Assert.assertEquals(json1, json2);
	}
	
	@Test	
	public void testEqualsPositive() {
		TestEntity testEntity = new TestEntity();
		TestEntity testEntity2 = Base.clone(testEntity);
		Assert.assertTrue(testEntity.equals(testEntity2));
		Assert.assertTrue(Base.equals(testEntity, testEntity2));
	}

	@Test
	public void testEqualsNegetive() {
		TestEntity testEntity = new TestEntity();
		TestEntity testEntity2 = Base.clone(testEntity);
		testEntity2.setIdInt(-1);
		Assert.assertFalse(testEntity.equals(testEntity2));
		Assert.assertFalse(Base.equals(testEntity, testEntity2));
	}
	
	@Test
	public void testToJSON() {
		TestEntity testEntity = new TestEntity();
		String json = testEntity.toJSON();
		Assert.assertNotNull(json);
		String json2 = Base.toJSON(testEntity);
		Assert.assertEquals(json, json2);
	}
	
	@Test	
	public void testToMap() {
		TestEntity testEntity = new TestEntity();
		Map<String, Object> map = testEntity.toMap();
		Assert.assertNotNull(map);
		Map<String, Object> map2= Base.toMap(testEntity);
		Assert.assertTrue(Base.equals(map, map2));
		
		TestEntity testEntity2 = Base.fromMap(map, TestEntity.class);
		Assert.assertEquals(testEntity, testEntity2);
	}
	
	@Test
	public void testMapToJSON() {
		TestEntity testEntity = new TestEntity();
		Map<String,Object> map = testEntity.toMap();
		String jsonFromMap =  Base.mapToJson(map);
		Map<String,Object>  mapFromjson = Base.jsonToMap(jsonFromMap);
		String json = Base.mapToJson(mapFromjson);
		Assert.assertTrue(Base.equals(map, mapFromjson));
		Assert.assertEquals(jsonFromMap, json);
	}
	
	@Test
	public void testXML() {
		TestEntity testEntity = new TestEntity();
		String xml = testEntity.toXML();
		String xml1 = Base.toXML(testEntity);
		TestEntity testEntity2 = Base.fromXML(xml1, TestEntity.class);
		Assert.assertEquals(xml, xml1);
		Assert.assertEquals(testEntity, testEntity2);
		
	}
	
	@Test
	public void testHasCode() {
		TestEntity testEntity = new TestEntity();
		int hashCode = testEntity.hashcode();
		Assert.assertTrue(hashCode != 0);
	}
	
	@Test
	public void testClone() {
		TestEntity testEntity = new TestEntity();
		TestEntity testEntity2 = Base.clone(testEntity);
		Assert.assertEquals(testEntity, testEntity2);
	}
}
