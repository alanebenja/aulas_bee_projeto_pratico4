package com.ambev.projetopratico4.rest;

import com.ambev.projetopratico4.model.Produto;
import com.ambev.projetopratico4.repository.ProdutoRepository;
import com.ambev.projetopratico4.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/produtos")
public class ProdutoController {
    private final ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<String> cadastrarProduto(@RequestBody Produto produto) {
        produtoRepository.save(produto);
        return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable String id) {
        Optional<Produto> produto = produtoService.findById(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/consultar/{nome}")
    public ResponseEntity<List<Produto>> consultarProdutos(@PathVariable String nome) {
        List<Produto> produto = produtoService.consultarPorNome(nome);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<List<Produto>> consultarProdutos() {
        List<Produto> produto = produtoService.consultarTodos();
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
