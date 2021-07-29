package com.bae.service;

import java.util.List;

import com.bae.data.Cat;

public interface CatService {

	public Cat createCat(Cat cat);

	public List<Cat> getAllCats();

	public Cat getCat(int id);

	public Cat replaceCat(int id, Cat newCat);

	public String deleteCat(int id);

	List<Cat> getByName(String name);

}
