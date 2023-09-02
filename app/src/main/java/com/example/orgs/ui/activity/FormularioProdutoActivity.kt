package com.example.orgs.ui.activity

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.databinding.ActivityFormularioProdutoBinding
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto
import com.example.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()

        binding.imageView2.setOnClickListener {
            FormularioImagemDialog(this)
                .mostra(url) { imagem ->
                    url = imagem
                    binding.imageView2.tentaCarregarImagem(url)
                }

        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.botao
        val dao = ProdutoDao()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.adicionaProduto(produtoNovo)
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
        val produto = Produto(nome, descricao, value, url)
        return produto
    }
}