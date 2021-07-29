package com.bae;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.data.Cat;
import com.bae.data.repos.CatRepo;
import com.bae.service.CatServiceDB;

@SpringBootTest
@ActiveProfiles("test")
public class CatServiceDBUnitTest {

	@Autowired
	private CatServiceDB service;

	@MockBean
	private CatRepo repo;

	@Test
	void testUpdate() {

		int id = 1;

		Cat testCat = new Cat(id, "Malcom", "Moggy", 3, true, "Black");
		Cat testNewCat = new Cat(id, "George", "Himalayan", 1, true, "Black");

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(testCat));
		Mockito.when(this.repo.save(new Cat(id, "George", "Himalayan", 1, true, "Black"))).thenReturn(testNewCat);

		Cat actual = this.service.replaceCat(id, testNewCat);
		assertThat(actual).isEqualTo(testNewCat);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(new Cat(id, "George", "Himalayan", 1, true, "Black"));
	}

	@Test
	void testDelete() {
		int id = 1;
		assertThat(this.service.deleteCat(id)).isEqualTo("Deleted: " + id);
	}

	@Test
	void testFindById() {
		int id = 1;
		Cat expected = new Cat(id, "George", "Himalayan", 1, true, "Black");

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(expected));

		Assertions.assertThat(this.service.getCat(id)).isEqualTo(expected);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);

	}

	@Test
	void testFindAll() {
		int id = 1;
		Cat testCat = new Cat(id, "George", "Himalayan", 1, true, "Black");
		testCat.setId(id);
		List<Cat> cats = List.of(testCat);

		Mockito.when(this.repo.findAll()).thenReturn(cats);

		Assertions.assertThat(this.service.getAllCats()).isEqualTo(cats);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();

	}

	@Test
	void testFindByName() {
		List<Cat> testCat = List.of(new Cat(1, "George", "Himalayan", 1, true, "Black"));

		String search = "George";

		Mockito.when(this.repo.findByNameIgnoreCase(search)).thenReturn(testCat);

		Assertions.assertThat(this.service.getByName(search)).isEqualTo(testCat);

		Mockito.verify(this.repo, Mockito.times(1)).findByNameIgnoreCase(search);
	}

}
