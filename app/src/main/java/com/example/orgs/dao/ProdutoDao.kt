package com.example.orgs.dao

import com.example.orgs.model.Produto

class ProdutoDao {

    fun adicionaProduto(produto: Produto) {
        produtos.add(produto)
    }
    fun buscaTodos() : List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>()
    }
}