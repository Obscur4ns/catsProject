package com.bae.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "fullName", unique = true)
	private String name;
	private String breed;
	private int age;
	private boolean cutie;
	private String colouring;

	public Cat(int id, String name, String breed, int age, boolean cutie, String colouring) {
		super();
		this.id = id;
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.cutie = cutie;
		this.colouring = colouring;
	}

	public Cat(String name, String breed, int age, boolean cutie, String colouring) {
		super();
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.cutie = cutie;
		this.colouring = colouring;
	}

	public Cat() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		if (age != other.age)
			return false;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (cutie != other.cutie)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isCutie() {
		return cutie;
	}

	public void setCutie(boolean cutie) {
		this.cutie = cutie;
	}

	public String getColouring() {
		return colouring;
	}

	public void setColouring(String colouring) {
		this.colouring = colouring;
	}

	@Override
	public String toString() {
		return "Cat [Name: " + name + "Age: " + age + "Breed: " + breed + "Colouring: " + colouring + "Cutie?" + cutie
				+ "]";
	}

}