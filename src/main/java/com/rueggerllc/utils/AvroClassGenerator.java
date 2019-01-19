package com.rueggerllc.utils;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.compiler.specific.SpecificCompiler;
import org.apache.log4j.Logger;

public class AvroClassGenerator {
	
	private static final Logger logger = Logger.getLogger(AvroClassGenerator.class);
	
    public void generateAvroClasses() throws IOException {
        SpecificCompiler compiler = new SpecificCompiler(new Schema.Parser().parse(new File("src/main/resources/avroMyRequest-schema.avsc")));
        compiler.compileToDestination(new File("src/main/resources"), new File("src/main/java"));
    }
    
    
    public static void main(String[] args) {
    	try {
	    	AvroClassGenerator generator = new AvroClassGenerator();
	    	generator.generateAvroClasses();
	    	logger.info("CLASSES GENERATED");
    	} catch (Exception e) {
    		logger.info("ERROR:\n" + e);
    	}
    }

}
