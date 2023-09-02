package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.orgs.R
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.databinding.ActivityListaProdutoBinding
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.ui.dialog.FormularioImagemDialog
import com.example.orgs.ui.recycleview.adapter.ListaProdutoAdapter

class ListaProdutosActivity : AppCompatActivity() {

    private val dao = ProdutoDao()
    private val adapter by lazy {
        ListaProdutoAdapter(this,dao.buscaTodos())
    }
    private val binding by lazy {
        ActivityListaProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        title="App de listas"
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
//        FormularioImagemDialog(this).mostra { imagem ->
//            Log.i("ListaProdutosActivity", "onCreate: $imagem")
//        }
//        val view = TextView(this)
//        view.setText("Aqui é um texto de uma textview")
////        Toast.makeText(this, "Isso aqui é um Toast", Toast.LENGTH_SHORT).show() // só um hello world para android
//        val nome = findViewById<TextView>(R.id.textView1)
//        nome.text = "Cesta de frutas"
//        val descricao = findViewById<TextView>(R.id.textView2)
//        descricao.text = "Laranja, manga e uva"
//        val valor = findViewById<TextView>(R.id.textView3)
//        valor.text = "19,99"
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
    }

    private fun configuraFab() {
        val fab = binding.floatingActionButton
        fab.setOnClickListener {
            vaiParaForm()
        }
    }

    private fun vaiParaForm() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recycleView = binding.list
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)

        // implementação do listener para abrir a Activity de detalhes do produto
        // com o produto clicado
        adapter.quandoClicaNoItem = {
            val intent = Intent(
                this,
                DetalheProdutoActivity::class.java
            ).apply {
                // envio do produto por meio do extra
                putExtra(CHAVE_PRODUTO, it)
            }
            startActivity(intent)
        }
    }
}