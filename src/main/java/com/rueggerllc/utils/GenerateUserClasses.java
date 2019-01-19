package com.rueggerllc.utils;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.compiler.specific.SpecificCompiler;
import org.apache.log4j.Logger;

public class GenerateUserClasses {
	
	private static final Logger logger = Logger.getLogger(GenerateUserClasses.class);
	
    public void generateAvroClasses() throws IOException {
        SpecificCompiler compiler = new SpecificCompiler(new Schema.Parser().parse(new File("src/main/resources/user.avsc")));
        compiler.compileToDestination(new File("src/main/resources"), new File("src/main/java"));
    }
    
    
    public static void main(String[] args) {
    	try {
	    	GenerateUserClasses generator = new GenerateUserClasses();
	    	generator.generateAvroClasses();
	    	logger.info("CLASSES GENERATED");
    	} catch (Exception e) {
    		logger.info("ERROR:\n" + e);
    	}
    }

}
