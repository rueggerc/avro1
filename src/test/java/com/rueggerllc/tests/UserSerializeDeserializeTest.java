package com.rueggerllc.tests;


import java.io.File;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.rueggerllc.avro.User;

public class UserSerializeDeserializeTest {

	private static final Logger logger = Logger.getLogger(UserSerializeDeserializeTest.class);

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
	@Ignore
	public void serializeUsersToFile() {
		try {
			logger.info("Serialize Users To File Begin");
			
			User user1 = new User();
			user1.setName("Alyssa");
			user1.setFavoriteNumber("42");
			user1.setFavoriteColor("yellow");
			// Leave favorite color null

			// Alternate constructor
			User user2 = new User("Ben", "7", "red");

			// Construct via builder
			User user3 = User.newBuilder()
			             .setName("Charlie")
			             .setFavoriteColor("blue")
			             .setFavoriteNumber("33")
			             .build();
			
			DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
			DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
			dataFileWriter.create(user1.getSchema(), new File("output/users.avro"));
			dataFileWriter.append(user1);
			dataFileWriter.append(user2);
			dataFileWriter.append(user3);
			dataFileWriter.close();
			
			logger.info("Serialize Users To File END");
	        
		} catch (Exception e) {
			logger.error("ERROR:\n", e);
		}
	}
	
	@Test
	public void deserializeUsersFromFile() {
		try {
			logger.info("DeSerialize Users From File Begin");
			
			// Deserialize Users from disk
			File file = new File("output/users.avro");
			DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
			DataFileReader<User> dataFileReader = new DataFileReader<User>(file, userDatumReader);
			User user = null;
			while (dataFileReader.hasNext()) {
				
			// Reuse user object by passing it to next(). This saves us from
			// allocating and garbage collecting many objects for files with
			// many items.
			user = dataFileReader.next(user);
			System.out.println(user);
			}
			logger.info("DeSerialize Users From File END");
	        
		} catch (Exception e) {
			logger.error("ERROR:\n", e);
		}
	}
	


}