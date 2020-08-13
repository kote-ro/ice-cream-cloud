package com.example.icecreamservice;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class IceCream {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    public IceCream(String name) {
        this.name = name;
    }
}
