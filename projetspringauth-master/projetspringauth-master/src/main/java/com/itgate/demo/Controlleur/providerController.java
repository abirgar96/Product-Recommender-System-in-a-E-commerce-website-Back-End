package com.itgate.demo.Controlleur;

import com.itgate.demo.dao.IAuthority;
import com.itgate.demo.models.Authority;
import com.itgate.demo.models.UserTokenState;
import com.itgate.demo.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import com.itgate.demo.dao.providerRepository;
import com.itgate.demo.models.Mail;
import com.itgate.demo.models.Provider;
import com.itgate.demo.utils.EmailService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")

@CrossOrigin("*")
@RequestMapping("/users/Provider")
public class providerController {

   @Autowired
   private IAuthority iAuthority;

    @Autowired
    private EmailService emailService;

    @Autowired
    private StorageService storage;

    @Autowired
    private providerRepository iprovider;
    @GetMapping("/All")
    public List<Provider> ListProvider(){

        return iprovider.findAll();

    }



    @PostMapping("/save/{idAuthority}")
    public ResponseEntity<?> saveProvider(Provider p, @PathVariable(value = "idAuthority") Long idAuthority,@RequestParam("file") MultipartFile file) {
        Authority authority = iAuthority.findOne(idAuthority);
        authority.setId(idAuthority);
        p.setEnabled(true);
        p.setAuthorities(authority);
        p.setPassword(hash(p.getPassword()));

     //   storage.store(file);
        p.setImage(file.getOriginalFilename());
        iprovider.save(p);




        return ResponseEntity.ok(new UserTokenState(null, 0, p));


    }

    @PutMapping("/modif/{Id}")
    public Provider modif (@RequestBody Provider provider, @PathVariable Long Id){

        provider.setId(Id);
        return iprovider.saveAndFlush(provider);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> delete (@PathVariable Long Id) {
        HashMap hashMap = new HashMap();
        try {
            iprovider.delete(Id);
            hashMap.put("etat", "Provider supprime");
            return hashMap;

        }
        catch(Exception e){

            hashMap.put("etat","Provider non supprime");
            return hashMap;
        }
    }

    @PostMapping(value="/sendMail")
    public String sendMail(@RequestBody Mail mail){
        System.out.println("Spring Mail - Sending Simple Email with JavaMailSender Example");
        mail.setFrom("abirgardabbou@gmail.com");
        mail.setTo(mail.getTo());
        mail.setSubject(mail.getSubject());
        mail.setContent(mail.getContent());
        emailService.sendSimpleMessage(mail);
        return "ok";
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

}
