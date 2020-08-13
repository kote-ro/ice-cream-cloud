package com.example.apigateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("icecream-service")
public interface IceCreamClient {
    @GetMapping("/icecreams")
    @CrossOrigin
    CollectionModel<IceCream> readIceCreams();
}
