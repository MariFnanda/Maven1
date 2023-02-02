package com.tech4me.crudapi.view.controller.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech4me.crudapi.compartilhado.ProdutoDto;
import com.tech4me.crudapi.model.Pessoa;
import com.tech4me.crudapi.model.Produto;
import com.tech4me.crudapi.service.ProdutoService;
import com.tech4me.crudapi.view.controller.model.ProdutoRequest;
import com.tech4me.crudapi.view.controller.model.ProdutoResponse;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/primeiro")
public class PrimeiroController {
    //localhost:8080/api/primeiro

    @Autowired
    private ProdutoService servico;

    ModelMapper mapper = new ModelMapper();

    @GetMapping
    public ResponseEntity <List<Produto>> obterProduto (){
        return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criarNovoProduto (@RequestBody ProdutoRequest prodrequest){

        ProdutoDto dtorequest = mapper.map(prodrequest, ProdutoDto.class);
        ProdutoDto dtoresponse = servico.criarProduto(dtorequest);
        ProdutoResponse prodresponse = mapper.map(dtoresponse, ProdutoResponse.class);

        return new ResponseEntity<>(prodresponse, HttpStatus.CREATED);
    }
    

    @GetMapping(value="/{id}")
    public ResponseEntity<Produto> obterPorId(@PathVariable String id){
        Optional<Produto> prod = servico.obterPorId(id);

        if(prod.isPresent()){
            return new ResponseEntity<>(prod.get(), HttpStatus.FOUND);
        }
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/{id}")
    public Produto atualizarProduto(@PathVariable String id, @RequestBody Produto produto){
        return servico.atualizarProduto(id, produto);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity <String> excluirProduto(@PathVariable String id) {
        servico.deleteById(id);
        return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
    }

    @GetMapping(value="/counting")
    public long count(String counting){
        return servico.count();
    }
    

}
