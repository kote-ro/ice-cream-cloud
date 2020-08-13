package com.example.apigateway;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class TastyIceCreamController {
    private final IceCreamClient iceCreamClient;

    public TastyIceCreamController(IceCreamClient iceCreamClient) {
        this.iceCreamClient = iceCreamClient;
    }

    private Collection<IceCream> fallback() {
        return new ArrayList<>();
    }

    @GetMapping("/tasty-icecreams")
    @CrossOrigin
    @HystrixCommand(fallbackMethod = "fallback")
    public Collection<IceCream> tastyIceCreams() {
        return iceCreamClient.readIceCreams()
                .getContent()
                .stream()
                .filter(this::isTasty)
                .collect(Collectors.toList());
    }

    private boolean isTasty(IceCream iceCream) {
        return !iceCream.getName().equals("Mochi") &&
                !iceCream.getName().equals("Blueberry") &&
                !iceCream.getName().equals("Chocolate") &&
                !iceCream.getName().equals("Fruit yogurt");
    }
}
