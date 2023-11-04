package com.springboot.app.item.service.impl;

import com.springboot.app.item.models.Item;
import com.springboot.app.item.models.Product;
import com.springboot.app.item.service.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {


    private final RestTemplate restTemplate;

    @Value("${listarProductos}")
    private String listarProductos;

    @Value("${listarProducto}")
    private String listarProducto;

    public ItemServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Item> findAll() {

        List<Product> productList = Arrays.asList(restTemplate.getForObject(listarProductos, Product[].class));
        return productList.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer amount) {

        Map<String, String> map = new HashMap<>();
        map.put("id", id.toString());

        Product product = restTemplate.getForObject(listarProducto, Product.class, map);

        return new Item(product, amount);
    }
}
