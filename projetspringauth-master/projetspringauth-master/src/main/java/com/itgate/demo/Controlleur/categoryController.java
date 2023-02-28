package com.itgate.demo.Controlleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import com.itgate.demo.dao.categoryRepository;
import com.itgate.demo.dao.produitRepository;
import com.itgate.demo.dao.IUser;
import com.itgate.demo.models.Category;
import com.itgate.demo.models.Produit;
import com.itgate.demo.models.User;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")

@RestController
@RequestMapping("/users/Category")
public class categoryController {
    @Autowired
    private categoryRepository icategory;

    @GetMapping("/All")
    public List<Category> ListCategory() {

        return icategory.findAll();

    }
    //@RequestMapping(method = RequestMethod.POST,value = "/Save", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
   @PostMapping("/Save")
    public Category savecategory(@RequestBody Category c) {

        return icategory.save(c);
    }

    @PutMapping("/modif/{Id}")
    public Category modif(@RequestBody Category category, @PathVariable Long Id) {

        category.setId(Id);
        return icategory.saveAndFlush(category);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String, String> delete(@PathVariable Long Id) {
        HashMap hashMap = new HashMap();
        try {
            icategory.delete(Id);
            hashMap.put("etat", "category supprime");
            return hashMap;

        } catch (Exception e) {

            hashMap.put("etat", "category non supprime");
            return hashMap;
        }
    }
}