package com.rueggerllc.tests;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rueggerllc.avro.Active;
import com.rueggerllc.avro.ClientIdentifier;
import com.rueggerllc.avro.MyRequest;
import com.rueggerllc.utils.MyRequestDeserializer;
import com.rueggerllc.utils.MyRequestSerializer;

public class SerializeTest {

	private static final Logger logger = Logger.getLogger(SerializeTest.class);

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
	public void serializeRequestJSON() {
		try {
			logger.info("Serialize JSON Begin");

			// Build Object
	        MyRequest request = buildObject();
	        
	        // Serialize
	        MyRequestSerializer serializer = new MyRequestSerializer();
			byte[] data = serializer.serializeMyRequestJSON(request);
			assertTrue(Objects.nonNull(data));
			assertTrue(data.length > 0);
			
			logger.info("Data Length=" + data.length);
//			String foo = new String(data);
//			System.out.println("Foo=" + foo);
			logger.info("Serialize JSON END");
	        
		} catch (Exception e) {
			logger.error("ERROR:\n", e);
		}
	}
	
	@Test
	public void serializeRequestBinary() {
		try {
			logger.info("Serialize Binary Begin");
			
			// Build Object
	        MyRequest request = buildObject();
	        
	        // Serializer
	        MyRequestSerializer serializer = new MyRequestSerializer();
	        byte[] data = serializer.serializeMyRequestBinary(request);
			assertTrue(Objects.nonNull(data));
			assertTrue(data.length > 0);
			
			logger.info("Data Length=" + data.length);
//			String foo = new String(data);
//			System.out.println("Foo=" + foo);
			logger.info("Serialize Binary END");
	        
		} catch (Exception e) {
			logger.error("ERROR:\n", e);
		}
	}
	
	@Test
	public void deserializeRequestJSON() {
		try {
			logger.info("Deserialize JSON Begin");

			// Build Object and Serialize
	        MyRequest request = buildObject();
	        byte[] data = serializeJSON(request);
	        
	        // Deserialize JSON
	        MyRequestDeserializer deserializer = new MyRequestDeserializer();
	        MyRequest deserializedRequest = deserializer.deSerializeMyRequestJSON(data);
	        
	        logger.info("Deserialize host=" + deserializedRequest.getClientIdentifier().getHostName());
	        
			logger.info("Deserialize JSON END");
	        
		} catch (Exception e) {
			logger.error("ERROR:\n", e);
		}
	}	

	
	@Test
	public void deserializeRequestBinary() {
		try {
			logger.info("Deserialize Binary Begin");

			// Build Object and Serialize
	        MyRequest request = buildObject();
	        byte[] data = serializeBinary(request);
	        
	        // Deserialize JSON
	        MyRequestDeserializer deserializer = new MyRequestDeserializer();
	        MyRequest deserializedRequest = deserializer.deSerializeMyRequestBinary(data);
	        
	        logger.info("Deserialize host=" + deserializedRequest.getClientIdentifier().getHostName());
	        
			logger.info("Deserialize Binary END");
	        
		} catch (Exception e) {
			logger.error("ERROR:\n", e);
		}
	}	

	
	
	private byte[] serializeJSON(MyRequest request) {
        MyRequestSerializer serializer = new MyRequestSerializer();
		byte[] data = serializer.serializeMyRequestJSON(request);
		return data;
	}
	
	private byte[] serializeBinary(MyRequest request) {
        MyRequestSerializer serializer = new MyRequestSerializer();
		byte[] data = serializer.serializeMyRequestBinary(request);
		return data;
	}
	
	
	
	// Utility
	private MyRequest buildObject() {
		
        ClientIdentifier clientIdentifier = ClientIdentifier.newBuilder()
	            .setHostName("localhost")
	            .setIpAddress("255.255.255.0")
	            .build();

        List<CharSequence> employees = new ArrayList();
        employees.add("James");
        employees.add("Alice");
        employees.add("David");
        employees.add("Han");

        MyRequest request = MyRequest.newBuilder()
            .setRequestTime(01l)
            .setActive(Active.ROCK)
            .setClientIdentifier(clientIdentifier)
            .setEmployeeNames(employees)
            .build();
        
        return request;
	      
	}
	
	
	

}