package testresource;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

/**
 * Resource for testing the code node simulation process.
 */
public class TestMap1 {

	HttpServletRequest request;

	public void test1() {
		Map<String, String> map = new TreeMap<>();
		map.put("a", "alice");
		map.put("b", "bob");

		addSourceToMap(map, "x");

		String query = "SELECT data FROM users WHERE id='" + map.get("x") + "'";

		Connection dbConnection = DriverManager.getConnection("someurl", "someusername", null);
		Statement sqlStatement = dbConnection.createStatement();
		sqlStatement.execute(query);
	}

	private void addSourceToMap(Map<String, String> map, String key) {
		String id = request.getParameter("id");
		map.put(key, id);
	}
}
