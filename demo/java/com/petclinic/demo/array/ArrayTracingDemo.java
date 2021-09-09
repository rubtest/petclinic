package com.petclinic.demo.array;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.MySQLCodec;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class ArrayTracingDemo {

	private Connection connection;
	private String[][] data = new String[3][3];

	public ArrayTracingDemo(Connection connection) {
		this.connection = connection;
	}

	public void requestHandler(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		prepareQuery1(request.getParameter("name"), request.getParameter("secretAnswer"));
		prepareQuery2(request.getParameter("name"), request.getParameter("secretAnswer"));
		prepareQuery3(request.getParameter("name"), request.getParameter("secretAnswer"));
		executeQueries();
	}

	private void prepareQuery1(String name, String answer) {
		data[0][0] = "COL_" + name;
		data[0][1] = "'" + name + "'";
		data[0][2] = "\"" + answer + "\"";
	}

	private void prepareQuery2(String name, String answer) {
		data[1][0] = ESAPI.encoder().encodeForSQL(new MySQLCodec(MySQLCodec.Mode.STANDARD), name);
		data[1][1] = "'my value'";
		data[1][2] = "'" + ESAPI.encoder().encodeForSQL(new MySQLCodec(MySQLCodec.Mode.STANDARD), answer) + "'";
	}

	private void prepareQuery3(String name, String answer) {
		data[2][0] = "MyColumn";
		data[2][1] = name;
		data[2][2] = answer;
	}

	private void executeQueries() throws SQLException {
		// Query 1
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM MyTable WHERE ")
				.append(data[0][0])
				.append(" = ")
				.append(data[0][1])
				.append(" AND Secret = ")
				.append(data[0][2]);
		connection.createStatement().executeQuery(sb.toString());

		// Query 2
		StringBuilderOwnImpl sbOwn = new StringBuilderOwnImpl();
		sbOwn.append("DELETE FROM MyTable WHERE ");
		sbOwn.append(data[1][0]);
		sbOwn.append(" = ");
		sbOwn.append(data[1][1]);
		sbOwn.append(" AND Secret = ");
		sbOwn.append(data[1][2]);
		connection.createStatement().executeQuery(sbOwn.toString());

		// Query 3
		String val1 = ESAPI.encoder().encodeForSQL(new MySQLCodec(MySQLCodec.Mode.STANDARD), data[2][1]);
		String val2 = ESAPI.encoder().encodeForSQL(new MySQLCodec(MySQLCodec.Mode.STANDARD), data[2][2]);
		connection.createStatement().executeQuery("SELECT * FROM MyTable WHERE " + data[2][0] + " = \"" + val1 +
		                                          "\" AND Secret = '" + val2 + "'");
	}

	private class StringBuilderOwnImpl {
		private String res = "";

		public void append(String s) {
			res = res + s;
		}

		@Override
		public String toString() {
			return res;
		}
	}
}
