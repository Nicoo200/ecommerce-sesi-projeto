package com.revisao.ecommerce.controllers;


import com.revisao.ecommerce.dto.ProdutoDTO;

import com.revisao.ecommerce.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {


    @Autowired
    ProdutoService produtoService;

    @PostMapping("/new")
    public ProdutoDTO insert(@RequestBody @Valid ProdutoDTO dto) {
        return produtoService.insert(dto);
    }

    @GetMapping
    public List<ProdutoDTO> findAll() {
        return produtoService.findAll();
    }

    @GetMapping("/page")
    public Page<ProdutoDTO> findPageable(Pageable pageable) {
        return produtoService.findPageable(pageable);
    }

    @GetMapping("/{id}")
    public ProdutoDTO findById(@PathVariable Long id) {
        return produtoService.findById(id);
    }

    @PutMapping("/{id}")
    public ProdutoDTO update(@PathVariable Long id, @RequestBody @Valid ProdutoDTO dto) {
        return produtoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produtoService.delete(id);
    }
}

