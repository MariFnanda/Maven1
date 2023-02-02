package com.tech4me.crudapi.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech4me.crudapi.compartilhado.ProdutoDto;
import com.tech4me.crudapi.model.Produto;
import com.tech4me.crudapi.repository.ProdutoRepositorio;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    @Autowired
    private ProdutoRepositorio repo;

    ModelMapper mapper = new ModelMapper();

    @Override
    public ProdutoDto criarProduto(ProdutoDto proddto) {

        Produto p = mapper.map(proddto, Produto.class);
        p = repo.save(p);
        ProdutoDto dto = mapper.map(p, ProdutoDto.class);
        return dto;
   
    }

    @Override
    public List<Produto> obterTodos() {
        return repo.findAll();
    }

    @Override
    public Optional<Produto>obterPorId(String id) {
        return repo.findById(id);
    }

    @Override
    public Produto atualizarProduto(String id, Produto produto){
        produto.setId(id);
        return repo.save(produto);
    }


    @Override
    public void deleteById(String id){
        repo.deleteById(id);
    }

    @Override
    public long count() {
        return repo.count();
    }

    
    
}
