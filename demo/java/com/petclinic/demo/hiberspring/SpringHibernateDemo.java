package com.petclinic.demo.hiberspring;

import org.hibernate.cfg.Configuration;
import org.owasp.esapi.ESAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;

@Controller
public class SpringHibernateDemo {

	private static Configuration hibernateConfig;

	public static void init(Configuration configuration) {
		SpringHibernateDemo.hibernateConfig = configuration;
	}

	@RequestMapping("/creditCardNo")
	public String paymentInfoHandler(@RequestParam String uname) {
		String whereClause = "WHERE username = '".concat(uname).concat("'");
		String table = PaymentInfo.class.getSimpleName();

		String sanitizedUname = ESAPI.encoder().encodeForHTML(uname);

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Welcome ")
				.append(sanitizedUname)
				.append(", your credit card number is ")
				.append(getDataFromDatabase(table, whereClause));
		return stringBuffer.toString();
	}

	private String getDataFromDatabase(String table, String whereClause) {
		EntityManager entityManager = hibernateConfig.buildSessionFactory().createEntityManager();

		return entityManager.createQuery("FROM " + table + " " + whereClause, PaymentInfo.class)
				       .getSingleResult()
				       .getCardNumber();
	}
}
