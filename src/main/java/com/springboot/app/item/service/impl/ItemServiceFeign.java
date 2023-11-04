package com.springboot.app.item.service.impl;

import com.springboot.app.item.client.ProductClientRest;
import com.springboot.app.item.models.Item;
import com.springboot.app.item.service.ItemService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class ItemServiceFeign implements ItemService {


    private final ProductClientRest clientFeign;

    public ItemServiceFeign(ProductClientRest clientFeign) {
        this.clientFeign = clientFeign;
    }

    @Override
    public List<Item> findAll() {
       return clientFeign.getProductList().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer amount) {
        return new Item(clientFeign.getProduct(id), amount);
    }
}
