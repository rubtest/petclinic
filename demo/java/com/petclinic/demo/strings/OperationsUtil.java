package com.ibm.security.appscan.altoromutual.util;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;
import com.ibm.security.appscan.altoromutual.model.Account;
import com.ibm.security.appscan.altoromutual.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class OperationsUtil {
    
    	public void doApiTransfer(HttpServletRequest request, long creditActId, long debitActId,
			double amount) {
		
		try {
			User user = OperationsUtil.getUser(request);
						
		} catch (SQLException e) {
			e.getLocalizedMessage();
		}
	}
    
    
	public static User getUser(HttpServletRequest request) throws SQLException{
		
		String accessToken = request.getHeader("Authorization").replaceAll("Bearer ", "");
		
		//Get username password and date 
		String decodedToken = new String(Base64.decodeBase64(accessToken));
		StringTokenizer tokenizer = new StringTokenizer(decodedToken,":");
		String username = new String(Base64.decodeBase64(tokenizer.nextToken()));
		return OperationsUtil.getUserInfo(username);
		
	}
    
    public static User getUserInfo(String username) throws SQLException{
		if (username == null || username.trim().length() == 0)
			return null; 
		
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet =statement.executeQuery("SELECT FIRST_NAME,LAST_NAME,ROLE FROM PEOPLE WHERE USER_ID = '"+ username +"' "); /* BAD - user input should always be sanitized */
		String firstName = null;
		String lastName = null;
		String roleString = null;
		if (resultSet.next()){
			firstName = resultSet.getString("FIRST_NAME");
			lastName = resultSet.getString("LAST_NAME");
			roleString = resultSet.getString("ROLE");
		}
		
		if (firstName == null || lastName == null)
			return null;
		
		User user = new User(username, firstName, lastName);
		
		if (roleString.equalsIgnoreCase("admin"))
			user.setRole(Role.Admin);
		
		return user;
	}
}