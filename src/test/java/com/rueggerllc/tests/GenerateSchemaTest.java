package com.rueggerllc.tests;


import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GenerateSchemaTest {


	private static final Logger logger = Logger.getLogger(GenerateSchemaTest.class);

	@BeforeClass
	public static void setupClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setupTest() throws Exception {
	}

	@After
	public void tearDownTest() throws Exception {
	}
	
	@Test
	public void createSchema() {
		logger.info("Create Schema Begin");
		
		Schema clientIdentifier = SchemaBuilder.record("ClientIdentifier")
			.namespace("com.rueggerllc.avro")
			.fields().requiredString("hostName").requiredString("ipAddress")
			.endRecord();
		
		Schema avroMyRequest = SchemaBuilder.record("MyRequest")
			.namespace("com.rueggerllc.avro")
			.fields().requiredLong("requestTime")
			.name("clientIdentifier")
				.type(clientIdentifier)
				.noDefault()
			.name("employeeNames")
				.type()
				.array()
				.items()
				.stringType()
				.arrayDefault(null)
			.name("active")
				.type()
				.enumeration("Active")
				.symbols("ROCK","PAPER", "SCISSORS")
				.noDefault()
			.endRecord();
		
		
		String schemaString = avroMyRequest.toString();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(schemaString);
		String prettyJsonString = gson.toJson(je);
		logger.info("\n"+prettyJsonString);
		
		
	}
	
	

}