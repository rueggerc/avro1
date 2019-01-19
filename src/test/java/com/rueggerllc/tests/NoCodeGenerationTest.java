package com.rueggerllc.tests;


import java.io.File;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NoCodeGenerationTest {

	private static final Logger logger = Logger.getLogger(NoCodeGenerationTest.class);

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
	
	// https://avro.apache.org/docs/1.8.1/gettingstartedjava.html
	@Test
	public void serializeUsersToFile() {
		try {
			logger.info("Serialize Users To File Begin");

			// Get Schema
			Schema schema = new Schema.Parser().parse(new File("src/main/resources/user.avsc"));
			
			logger.info("========== WRITE OBJECTS BEGIN ===");
			GenericRecord user1 = new GenericData.Record(schema);
			user1.put("name", "Alyssa");
			user1.put("favorite_number", "256");
			user1.put("favorite_color", "blue");

			GenericRecord user2 = new GenericData.Record(schema);
			user2.put("name", "Ben");
			user2.put("favorite_number", "7");
			user2.put("favorite_color", "red");
			
			logger.info("========== READ OBJECTS BEGIN ===");
			File file = new File("output/users_nogen.avro");
			DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
			DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
			dataFileWriter.create(schema, file);
			dataFileWriter.append(user1);
			dataFileWriter.append(user2);
			dataFileWriter.close();
			
			// Deserialize users from disk
			DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
			DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
			GenericRecord user = null;
			while (dataFileReader.hasNext()) {
				// Reuse user object by passing it to next(). This saves us from
				// allocating and garbage collecting many objects for files with
				// many items.
				user = dataFileReader.next(user);
				Object name = user.get("name");
				System.out.println("NAME=" + name + " TYPE=" + name.getClass());
				System.out.println(user);
			}

			logger.info("Serialize Users To File END");
		
		} catch (Exception e) {
			logger.error("ERROR:\n", e);
		}
	}
	
	@Test
	public void deserializeUsersFromFile() {
		try {
			logger.info("DeSerialize Users From File Begin");
			logger.info("DeSerialize Users From File END");
	        
		} catch (Exception e) {
			logger.error("ERROR:\n", e);
		}
	}
	


}