package com.tech4me.crudapi.service;

import java.util.List;
import java.util.Optional;

import com.tech4me.crudapi.compartilhado.ProdutoDto;
import com.tech4me.crudapi.model.Produto;

public interface ProdutoService {
    ProdutoDto criarProduto(ProdutoDto produto);
    List<Produto> obterTodos();
    Optional<Produto> obterPorId(String id);

    Produto atualizarProduto(String id, Produto produto);

    void deleteById(String id);

    long count();
}
