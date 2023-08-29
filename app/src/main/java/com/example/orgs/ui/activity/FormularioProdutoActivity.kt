package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {

    val dao = ProdutoDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = findViewById<Button>(R.id.botao)
        botaoSalvar.setOnClickListener {

            val produto = criaProduto()
            Log.i("pegaLog", "pegando input nome: $produto")
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = findViewById<EditText>(R.id.nome)
        val nome = campoNome.text.toString()

        val campoDesc = findViewById<EditText>(R.id.descricao)
        val descricao = campoDesc.text.toString()

        val campoValor = findViewById<EditText>(R.id.valor)
        val valor = campoValor.text.toString()
        var value: BigDecimal
        if (!valor.isBlank()) {
            value = BigDecimal(valor)
        } else {
            value = BigDecimal.ZERO
        }
        val produto = Produto(nome, descricao, value)
        dao.adicionaProduto(produto)
        return produto
    }
}