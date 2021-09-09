package com.petclinic.demo.hiberspring;

import javax.persistence.*;

@Entity
public class PaymentInfo {

	private Integer id;
	private String cardNumber;
	private String expireDate;
	private String secretNumber;
	private String username;

	public PaymentInfo(String cardNumber, String expireDate, String secretNumber) {
		this.cardNumber = cardNumber;
		this.expireDate = expireDate;
		this.secretNumber = secretNumber;
	}

	PaymentInfo() {
		// For Hibernate instantiation
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	private void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpireDate() {
		return expireDate;
	}

	private void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getSecretNumber() {
		return secretNumber;
	}

	private void setSecretNumber(String secretNumber) {
		this.secretNumber = secretNumber;
	}

	public String getUsername() {
		return username;
	}

	private void setUsername(String username) {
		this.username = username;
	}
}
