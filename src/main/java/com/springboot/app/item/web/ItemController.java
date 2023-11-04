package com.springboot.app.item.web;

import com.springboot.app.item.models.Item;
import com.springboot.app.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService service;

    @GetMapping("/item/listar")
    ResponseEntity<List<Item>> getItemList(){

        var response = service.findAll();

        return(Objects.nonNull(response))
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/item/id/{id}/amount/{amount}")
    ResponseEntity<Item> getItem(@PathVariable Long id, @PathVariable Integer amount){

        var response = service.findById(id, amount);

        return(Objects.nonNull(response))
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
