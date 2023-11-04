package com.springboot.app.item.client;

import com.springboot.app.item.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "servicio-producto", url = "${urlMicroProducto}")
public interface ProductClientRest {

    @GetMapping("/productos/listar")
    List<Product> getProductList();

    @GetMapping("/productos/mostrar/{id}")
    Product getProduct(@PathVariable Long id);

}
