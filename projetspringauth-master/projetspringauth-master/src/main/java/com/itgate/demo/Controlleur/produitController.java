package com.itgate.demo.Controlleur;

import com.itgate.demo.dao.categoryRepository;
import com.itgate.demo.dao.produitRepository;
import com.itgate.demo.models.Category;
import com.itgate.demo.models.Produit;
import com.itgate.demo.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Repository;


import java.awt.print.Pageable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/users/Produit")
public class produitController {


    @Autowired
    private categoryRepository icategory;
    @Autowired
    private produitRepository iproduit;

    @Autowired
    private StorageService storage;

    @GetMapping("/All")
    public List<Produit> ListProduit() {
        return iproduit.findAll();
    }

    @GetMapping("/GetOne/{id}")
    public Produit  GetOneProduct(@PathVariable Long id){

        return iproduit.findOne(id);

    }



    @RequestMapping("/Save/{idcategory}")
    public Produit saveProduct(Produit p, @RequestParam("file") MultipartFile file,@PathVariable Long idcategory) {
      Category category= icategory.findOne(idcategory);
      p.setCategory(category);
        int i = (int) new Date().getTime();
        System.out.println("Integer : " + i);
        storage.store(file, i + file.getOriginalFilename());
        p.setPicture(i + file.getOriginalFilename());
        return iproduit.save(p);

    }

    @PutMapping("/modif/{Id}")
    public Produit modif(@RequestBody Produit produit, @PathVariable Long Id) {

        produit.setId(Id);
        return iproduit.saveAndFlush(produit);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String, String> delete(@PathVariable Long Id) {
        HashMap hashMap = new HashMap();
        try {
            iproduit.delete(Id);
            hashMap.put("etat", "Produit supprime");
            return hashMap;

        } catch (Exception e) {

            hashMap.put("etat", "Produit non supprime");
            return hashMap;
        }
    }


//    @RequestMapping("/Save")
//    public Produit saveProduct(Produit p, @RequestParam("file") MultipartFile file) {
//        storage.store(file);
//        p.setPicture(file.getOriginalFilename());
//        return iproduit.save(p);
//
//    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storage.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

//    @PostMapping("/Save")
//       public Produit saveproduit (@RequestBody Produit p){
//
//          return iproduit.save(p);
//      }


    @GetMapping("/FindByPage")
    public Page<Produit> ListProduit(int page, int size){
        return iproduit.findByPage(new PageRequest(page,size));
    }


}