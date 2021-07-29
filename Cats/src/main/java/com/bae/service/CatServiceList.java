package com.bae.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.data.Cat;

@Service
public class CatServiceList implements CatService {
	private List<Cat> cats = new ArrayList<>();

	@Override
	public Cat createCat(Cat cat) {
		System.out.println(cat);
		this.cats.add(cat);
		return this.cats.get(this.cats.size() - 1);
	}

	@Override
	public List<Cat> getAllCats() {
		return this.cats;
	}

	@Override
	public Cat getCat(int id) {
		Cat found = this.cats.get(id);
		return found;
	}

	@Override
	public Cat replaceCat(int id, Cat newCat) {
		return this.cats.set(id, newCat);
	}

	@Override
	public String deleteCat(int id) {
		this.cats.remove(id);
		return "Deleted cat at index: " + id;
	}

	@Override
	public List<Cat> getByName(String name) {
		return null;
	}

}
