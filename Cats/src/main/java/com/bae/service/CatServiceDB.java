package com.bae.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bae.data.Cat;
import com.bae.data.repos.CatRepo;

@Service
@Primary
public class CatServiceDB implements CatService {

	private CatRepo repo;

	public CatServiceDB(CatRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Cat createCat(Cat cat) {
		return this.repo.save(cat);
	}

	@Override
	public List<Cat> getAllCats() {
		return this.repo.findAll();
	}

	@Override
	public List<Cat> getByName(String name) {
		return this.repo.findByNameIgnoreCase(name);
	}

	@Override
	@Transactional
	public Cat getCat(int id) {
		Cat found = this.repo.findById(id).get();
		return found;
	}

	@Override
	public Cat replaceCat(int id, Cat newCat) {
		Cat found = this.repo.findById(id).get();
		System.out.println("FOUND: " + found);
		found.setAge(newCat.getAge());
		found.setName(newCat.getName());
		found.setBreed(newCat.getBreed());
		found.setCutie(newCat.isCutie());
		found.setColouring(newCat.getColouring());
		Cat updated = this.repo.save(found);
		System.out.println("UPDATED: " + updated);
		return updated;
	}

	@Override
	public String deleteCat(int id) {
		this.repo.deleteById(id);
		if (this.repo.existsById(id)) {
			return "Not deleted: " + id;
		} else {
			return "Deleted: " + id;
		}
	}

}
