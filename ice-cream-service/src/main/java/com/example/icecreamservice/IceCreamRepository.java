package com.example.icecreamservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface IceCreamRepository extends JpaRepository<IceCream, Long> {

}
