package com.itgate.demo.Controlleur;

import com.itgate.demo.dao.customerRepository;
import com.itgate.demo.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


import com.itgate.demo.dao.orderRepository;
import com.itgate.demo.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")

@RestController
@RequestMapping("/users/Order")
public class orderController {

    @Autowired
    private orderRepository iorder;

    @Autowired
    private customerRepository userRepository ;

    @GetMapping("/All")
    public List<Order> ListOrder(){
        return iorder.findAll();

    }

    @PostMapping("/Save")
    public Order saveorder (@RequestBody Order o){

        return iorder.save(o);
    }

    @PutMapping("/modif/{Id}")
    public Order modif (@RequestBody Order order, @PathVariable Long Id){

        order.setId(Id);
        return iorder.saveAndFlush(order);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> delete (@PathVariable Long Id) {
        HashMap hashMap = new HashMap();
        try {
            iorder.delete(Id);
            hashMap.put("etat", "Order supprime");
            return hashMap;

        } catch (Exception e) {

            hashMap.put("etat", "Order non supprime");
            return hashMap;
        }


    }

            @PostMapping("/submit/{username}")
            public ResponseEntity<Order> submit(@PathVariable String username) {
                Customer client = userRepository.findByClientname(username);
                if(client == null) {
                    return ResponseEntity.notFound().build();
                }
                System.out.println(client.getCart());

                Order order = Order.createFromCart(client.getCart());
                iorder.save(order);
                return ResponseEntity.ok(order);
            }

            @GetMapping("/history/{username}")
            public ResponseEntity<List<Order>> getOrdersForUser(@PathVariable String username) {
                Customer client = userRepository.findByClientname(username);
                if(client == null) {
                    return ResponseEntity.notFound().build();
                }
                return ResponseEntity.ok(iorder.findByClient(client));
            }






}


