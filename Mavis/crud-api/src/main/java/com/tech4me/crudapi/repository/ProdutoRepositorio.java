package com.tech4me.crudapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tech4me.crudapi.model.Produto;

public interface ProdutoRepositorio extends MongoRepository<Produto,String>{
    
}
