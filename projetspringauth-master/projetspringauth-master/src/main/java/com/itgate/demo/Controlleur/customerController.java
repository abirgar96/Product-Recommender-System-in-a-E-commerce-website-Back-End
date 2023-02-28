package com.itgate.demo.Controlleur;

import com.itgate.demo.dao.CartRepository;
import com.itgate.demo.dao.IAuthority;
import com.itgate.demo.models.*;
import com.itgate.demo.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.itgate.demo.dao.customerRepository;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin("*")

@RestController
@RequestMapping("/users/Customer")
public class customerController {


    @Autowired
    private IAuthority iauthority;
    @Autowired
    private StorageService storage;

    @Autowired
    private customerRepository icustomer;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/All")
    public List<Customer> ListCustomer(){

        return icustomer.findAll();

    }

//    @PostMapping("/Save")
//    public Customer savecustomer (@RequestBody Customer c){
//
//        return icustomer.save(c);
//    }

    @PostMapping("/save/{idAuthority}")
    public ResponseEntity<?> saveCustomer (Customer e, @PathVariable(value = "idAuthority") Long idAuthority,
                                         @RequestParam("file") MultipartFile file) {
        Authority authority = iauthority.findOne(idAuthority);
        authority.setId(idAuthority);
        e.setEnabled(true);
        e.setAuthorities(authority);
        e.setPassword(hash(e.getPassword()));

     //   storage.store(file);
       // storage.store(file,i+file.getOriginalFilename());


        int i = (int) new Date().getTime();
        System.out.println("Integer : " + i);
        storage.store(file,i+file.getOriginalFilename());
        e.setImage(i+file.getOriginalFilename());

        Cart cart = new Cart();
        cartRepository.save(cart);
        e.setCart( cart);

        icustomer.save(e);
        return ResponseEntity.ok(new UserTokenState(null, 0, e));
    }




    @PutMapping("/modif/{Id}")
    public Customer modif (@RequestBody Customer customer, @PathVariable Long Id){

        customer.setId(Id);
        return icustomer.saveAndFlush(customer);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> delete (@PathVariable Long Id) {
        HashMap hashMap = new HashMap();
        try {
            icustomer.delete(Id);
            hashMap.put("etat", "Customer supprime");
            return hashMap;

        }
        catch(Exception e){

            hashMap.put("etat","Customer non supprime");
            return hashMap;
        }
    }
    String hash(String password) {


        String hashedPassword = null;
        int i = 0;
        while (i < 5) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            hashedPassword = passwordEncoder.encode(password);
            i++;
        }

        return hashedPassword;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storage.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
