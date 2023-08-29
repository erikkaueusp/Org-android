package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.databinding.ActivityListaProdutoBinding
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
        super.onCreate(savedInstanceState)
        configuraRecyclerView()
        setContentView(binding.root)
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
        configuraFab()
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
    }
}