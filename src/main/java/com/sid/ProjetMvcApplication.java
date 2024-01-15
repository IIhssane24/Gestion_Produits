package com.sid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.sid.dao.ProduitRepository;
import com.sid.entity.Produit;

@SpringBootApplication
public class ProjetMvcApplication {

	public static void main(String[] args) {
		ApplicationContext ctx= SpringApplication.run(ProjetMvcApplication.class, args);
//	    ProduitRepository produitRepository=ctx.getBean(ProduitRepository.class);
//	    produitRepository.save(new Produit("samsung-34",9000,30));
//	    produitRepository.save(new Produit("hdp839",7000,70));
//	    produitRepository.save(new Produit("JJ-2e2e",6700,20));
//	    produitRepository.save(new Produit("DP-22",4500,10));
	}

}
