package se.cambio.trainings.restdemo_cambio.controllers.dto;

import java.util.UUID;

public class Patient {

	private String id;
	private String name;
	private int age;

	public Patient(String name, int age) {

		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
