package com.rueggerllc.utils;

import java.io.IOException;

import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.log4j.Logger;

import com.rueggerllc.avro.MyRequest;

public class MyRequestDeserializer {
	
	private static final Logger logger = Logger.getLogger(MyRequestDeserializer.class);

	public MyRequest deSerializeMyRequestJSON(byte[] data) {
	    DatumReader<MyRequest> reader = new SpecificDatumReader<>(MyRequest.class);
	    Decoder decoder = null;
	    try {
	        decoder = DecoderFactory.get().jsonDecoder(MyRequest.getClassSchema(), new String(data));
	        return reader.read(null, decoder);
	    } catch (IOException e) {
	        logger.error("Deserialization error:" + e.getMessage());
	        return null;
	    }
	}
	
    public MyRequest deSerializeMyRequestBinary(byte[] data) {
        DatumReader<MyRequest> employeeReader = new SpecificDatumReader<>(MyRequest.class);
        Decoder decoder = DecoderFactory.get().binaryDecoder(data, null);
        try {
            return employeeReader.read(null, decoder);
        } catch (IOException e) {
            logger.error("Deserialization error" + e.getMessage());
        }
        return null;
    }
	
	
}
