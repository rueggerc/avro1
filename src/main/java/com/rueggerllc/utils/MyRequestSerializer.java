package com.rueggerllc.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.log4j.Logger;

import com.rueggerllc.avro.MyRequest;

public class MyRequestSerializer {
	
	private static final Logger logger = Logger.getLogger(MyRequestSerializer.class);

	public byte[] serializeMyRequestJSON(MyRequest request) {
		DatumWriter<MyRequest> writer = new SpecificDatumWriter<>(MyRequest.class);
		byte[] data = new byte[0];
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Encoder jsonEncoder = null;
		try {
			jsonEncoder = EncoderFactory.get().jsonEncoder(MyRequest.getClassSchema(), stream);
			writer.write(request, jsonEncoder);
			jsonEncoder.flush();
			data = stream.toByteArray();
		} catch (IOException e) {
			logger.error("Serialization error:" + e.getMessage());
		}
		return data;
	}
	
	public byte[] serializeMyRequestBinary(MyRequest request) {
		DatumWriter<MyRequest> writer = new SpecificDatumWriter<>(MyRequest.class);
		byte[] data = new byte[0];
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Encoder jsonEncoder = null;
		try {
			jsonEncoder = EncoderFactory.get().binaryEncoder(stream,null);
			writer.write(request, jsonEncoder);
			jsonEncoder.flush();
			data = stream.toByteArray();
		} catch (IOException e) {
			logger.error("Serialization error:" + e.getMessage());
		}
		return data;
	}
	
	
	
}
