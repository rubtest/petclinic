package com.petclinic.demo.bytearray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ByteArrayStuff {
	public void requestHandler(HttpServletRequest request, HttpServletResponse response) {
		String taintedData = request.getParameter("name");
		ByteArrayOutputStream streamByteArrayOutput = null;
		ObjectOutput outputObject = null;
		try {
			streamByteArrayOutput = new ByteArrayOutputStream();
			outputObject = new ObjectOutputStream(streamByteArrayOutput);
			outputObject.writeObject(taintedData);
			byte[] taintedDataSerialized = streamByteArrayOutput.toByteArray();
			badSink(taintedDataSerialized);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}​
	public void badSink(byte[] dataSerialized) {
		/* unserialize data */
		ByteArrayInputStream streamByteArrayInput = null;
		ObjectInputStream streamObjectInput = null;
​
		try {
			streamByteArrayInput = new ByteArrayInputStream(dataSerialized);
			streamObjectInput = new ObjectInputStream(streamByteArrayInput);
			String data = (String) streamObjectInput.readObject();
			String osCommand = "/bin/ls ";
			Process process = Runtime.getRuntime().exec(osCommand + data);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}