package com.itgate.demo.Controlleur;

import com.itgate.demo.dao.CartRepository;
import com.itgate.demo.dao.customerRepository;
import com.itgate.demo.dao.produitRepository;
import com.itgate.demo.models.Cart;
import com.itgate.demo.models.Customer;
import com.itgate.demo.models.ModifyCartRequest;
import com.itgate.demo.models.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;
@CrossOrigin("*")

@RestController
@RequestMapping("/users/cart")
public class cartController {

    @Autowired
    private customerRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private produitRepository itemRepository;

    @PostMapping("/removeFromCart")
    public ResponseEntity<Cart> removeFromcart(@RequestBody ModifyCartRequest request) {
        Customer client = userRepository.findByClientname(request.getUsername());
        if(client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Produit item = itemRepository.findOne(request.getItemId());
        if(item==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Cart cart = client.getCart();
        IntStream.range(0, request.getQuantity())
                .forEach(i -> cart.removeProduct(item));
        cartRepository.save(cart);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/addtocart/{idcustommer}")
    public ResponseEntity<?> addTocart(@PathVariable Long idcustommer, @RequestBody ModifyCartRequest request) {
        Customer client = userRepository.findByClientname(request.getUsername());
        if(client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Produit item = itemRepository.findOne(request.getItemId());
        if(item ==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Cart cart = client.getCart();
        cart.addProduct(item);
        IntStream.range(1, request.getQuantity()).forEach(i -> cart.addProduct(item));
        cartRepository.save(cart);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/all")
    public List<Cart> addTocart2() {
        return cartRepository.findAll();
    }
}

