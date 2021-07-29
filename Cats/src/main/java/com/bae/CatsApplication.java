package com.bae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.bae.rest.CatController;
import com.bae.service.CatServiceList;

@SpringBootApplication
public class CatsApplication {

	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(CatsApplication.class, args);
		CatController controller = beanBag.getBean(CatController.class);
		System.out.println(controller);

		CatServiceList service = beanBag.getBean(CatServiceList.class);
		System.out.println(service);
		// beanBag.getBean(Cat.class);
	}

}
