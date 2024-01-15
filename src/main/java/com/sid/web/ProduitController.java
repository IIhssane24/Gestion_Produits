package com.sid.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sid.dao.ProduitRepository;
import com.sid.entity.Produit;

import jakarta.validation.Valid;

@Controller
public class ProduitController {

	@Autowired
	private ProduitRepository produitRepository;
	
	//chaque methode return un string 
	//qui represent le nom de vue qu'il va etre afficher 
	@RequestMapping(value="/index")
	public String index(Model model,@RequestParam(name="page",defaultValue ="0") int p,
			@RequestParam(name="size",defaultValue ="5")  int s,
			@RequestParam(name="mc",defaultValue ="") String mc) {
		//model juste un objet qui stocke les resultat qui il vont etre afficher
		//je recupere la liste aprtir de couche metier
		Pageable pageable=PageRequest.of(p, s);
		Page<Produit>  pageProduits=produitRepository. findByDesContains(mc, pageable);
		model.addAttribute("listProduits", pageProduits.getContent());
		int[] pages=new int[pageProduits.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size",s);
		model.addAttribute("pageCourante", p); 
		model.addAttribute("mc",mc);
		return "produits";
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(Long id ,int page,int size,String mc) {
		produitRepository.deleteById(id);
		return "redirect:/index?page="+page+"&size="+size+"&mc="+mc;
	}
	
	@RequestMapping(value = "/form",method = RequestMethod.GET)
	public String formProduit(Model model) {
		model.addAttribute("produit", new Produit());
		return "FormProduit";
	}
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(Model model,@Valid Produit produit,BindingResult bindingResult) {
		//BindingResult: il fait la validation et tous les errors il stock dans la collection
		//apres on va check if bindingResult has error then in the form.html
		//Ajouter les annotations de validation  pour l'entity
		//dans le controller utiliser annotation @Valid et on declare une collection binding result
		//et em fait la verfication
		//ou niveau de formulaire en ajoute th:error correspond au champs concerne
		if(bindingResult.hasErrors()) {
			return "FormProduit";
		}
		produitRepository.save(produit);
		return "Confirmation";
	}
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(Model model,Long id) {
		Optional<Produit> p = produitRepository.findById(id);
		 if(p.isPresent()){
			 Produit produit = p.get();
			  model.addAttribute("produit",produit);
			  return "EditProduit";
		 }
		 return "produits";
		
	}
}
