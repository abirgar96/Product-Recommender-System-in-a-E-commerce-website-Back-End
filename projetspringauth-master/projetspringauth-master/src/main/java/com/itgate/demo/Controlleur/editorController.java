package com.itgate.demo.Controlleur;

import com.itgate.demo.dao.IAuthority;
import com.itgate.demo.dao.editorRepository;
import com.itgate.demo.models.Editor;
import com.itgate.demo.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itgate.demo.models.Authority;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.itgate.demo.models.Authority;
import com.itgate.demo.models.UserTokenState;
import org.springframework.security.crypto.bcrypt.*;


@CrossOrigin("*")

@RestController
@RequestMapping("/ADMIN/Editor")
public class editorController {

    @Autowired
    private editorRepository ieditor;


    @Autowired
    private StorageService storage;


    @Autowired
    private IAuthority iauthority;

    @GetMapping("/All")
    public List<Editor> ListEditor(){

        return ieditor.findAll();

    }
//    @PostMapping("/Save")
//    public Editor saveeditor (@RequestBody Editor c){
//
//        return ieditor.save(c);
//    }

    @PostMapping("/save/{idAuthority}")
    public ResponseEntity<?> saveEditor (Editor e, @PathVariable(value = "idAuthority") Long idAuthority,
                                         @RequestParam("file") MultipartFile file) {
        Authority authority = iauthority.findOne(idAuthority);
        authority.setId(idAuthority);
        e.setEnabled(true);
        e.setAuthorities(authority);
        e.setPassword(hash(e.getPassword()));

      //  storage.store(file);
        e.setImage(file.getOriginalFilename());
        ieditor.save(e);
        return ResponseEntity.ok(new UserTokenState(null, 0, e));
    }

    @PutMapping("/modif/{Id}")
    public Editor modif (@RequestBody Editor editor, @PathVariable Long Id){

        editor.setId(Id);
        return ieditor.saveAndFlush(editor);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> delete (@PathVariable Long Id) {
        HashMap hashMap = new HashMap();
        try {
            ieditor.delete(Id);
            hashMap.put("etat", "Editor supprime");
            return hashMap;

        }
        catch(Exception e){

            hashMap.put("etat","Editor non supprime");
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

}
