package com.example.orgs.ui.recycleview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.model.Produto

class ListaProdutoAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutoAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val nome = binding.nome
        private val descricao = binding.descricao
        private val valor = binding.valor

        fun vincula(produto: Produto) {
            nome.text = produto.nome
            descricao.text = produto.descricao
            valor.text = produto.valor.toPlainString()

        }
    }

    //view Holder responsavel pelo processo de bind
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflar view
        val inflater = LayoutInflater.from(context)
//        val view = inflater.inflate(R.layout.produto_item, parent, false)
        val binding = ProdutoItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    // indica o item atual do adapter mostrando a possição
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    // indica a quantidade de itens o size
    override fun getItemCount(): Int = produtos.size


    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
