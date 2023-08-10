package utils;

import java.util.Locale;

import com.github.javafaker.Faker;
import java.util.Random;


public class DataFaker {
	private Locale locale = new Locale("en");
	private Faker faker = new Faker(locale);

	public static DataFaker getDataFaker() {
		return new DataFaker();
	}

	public String getFirstName() {
		return faker.address().firstName();
	}

	public String getLastName() {
		return faker.address().lastName();
	}

	public String getEmail() {
		return faker.internet().emailAddress();
	}

	public String getPhone() {
		return faker.phoneNumber().phoneNumber();
	}

	public String getCity() {
		return faker.address().city();
	}

	public String getCityName() {
		return faker.address().cityName();
	}

	public String getCompanyName() {
		return faker.company().name();
	}

	public String getPassword() {
		return faker.internet().password(8, 12, true, true);
	}


	public String getAddress() {
		return faker.address().fullAddress();
	}

	public String getZip() {
		return faker.address().zipCode();
	}


	public String getGender() {
		return new Random().nextInt(2) == 0 ? "Male" : "Female";
	}

	public String getCountry() {
		return faker.country().name();
	}
}