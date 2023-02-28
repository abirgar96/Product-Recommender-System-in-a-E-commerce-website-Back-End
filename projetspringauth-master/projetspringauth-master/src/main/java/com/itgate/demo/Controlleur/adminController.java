package com.itgate.demo.Controlleur;

import com.itgate.demo.dao.adminRepository;
import com.itgate.demo.dao.editorRepository;
import com.itgate.demo.models.Admin;
import com.itgate.demo.models.Editor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;





@RestController
@RequestMapping("/Admin")
public class adminController {

    @Autowired
    private adminRepository iadmin;
    @GetMapping("/All")
    public List<Admin> ListAdmin(){

        return iadmin.findAll();

    }
//    @PostMapping("/Save")
//    public Admin saveadmin(@RequestBody Admin c){
//
//        return iadmin.save(c);
//    }

    @PutMapping("/modif/{Id}")
    public Admin admin (@RequestBody Admin admin, @PathVariable Long Id){

        admin.setId(Id);
        return iadmin.saveAndFlush(admin);
    }

//    @DeleteMapping("/delete/{Id}")
//    public HashMap<String,String> delete (@PathVariable Long Id) {
//        HashMap hashMap = new HashMap();
//        try {
//            iadmin.delete(Id);
//            hashMap.put("etat", "Admin supprime");
//            return hashMap;
//
//        }
//        catch(Exception e){
//
//            hashMap.put("etat","Admin non supprime");
//            return hashMap;
//        }
   // }

}
