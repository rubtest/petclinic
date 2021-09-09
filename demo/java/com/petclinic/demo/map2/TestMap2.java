package testresource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class TestMap2 {

	HttpServletRequest request;

	public void test2(HttpServletRequest request, HttpServletResponse response) {
		String taintedData = request.getParameter("name");
		HashMap<Integer, String> dataMap = new HashMap<>();
		dataMap.put(0, taintedData);
		dataMap.put(1, "Static data");
		dataMap.put(2, taintedData);
		sink(dataMap);
		safe(dataMap);
	}

	public void sink(HashMap<Integer, String> dataHashMap) {
		String data = dataHashMap.get(2);
​
		String osCommand = "/bin/ls ";
		Process process = Runtime.getRuntime().exec(osCommand + data);
		process.waitFor();
	}

	public void safe(HashMap<Integer, String> dataHashMap) {
		String data = dataHashMap.get(1);
​
		String osCommand = "/bin/ls ";
		Process process = Runtime.getRuntime().exec(osCommand + data);
		process.waitFor();
	}
}
