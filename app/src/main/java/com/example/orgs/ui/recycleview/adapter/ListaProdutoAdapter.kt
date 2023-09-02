package com.example.orgs.ui.recycleview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class ListaProdutoAdapter(
    private val context: Context,
    produtos: List<Produto>,
    // declaração da função para o listener do adapter
    var quandoClicaNoItem: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutoAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    // utilização do inner na classe interna para acessar membros da classe superior
    // nesse caso, a utilização da variável quandoClicaNoItem
    inner class ViewHolder(private val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val nome = binding.nome
        private val descricao = binding.descricao
        private val valor = binding.valor

        // Considerando que o ViewHolder modifica de valor com base na posição
        // é necessário o uso de properties mutáveis, para evitar nullables
        // utilizamos o lateinit, properties que podem ser inicializar depois
        private lateinit var produto: Produto

        init {
            // implementação do listener do adapter
            itemView.setOnClickListener {
                // verificação da existência de valores em property lateinit
                if (::produto.isInitialized) {
                    quandoClicaNoItem(produto)
                }
            }
        }

        fun vincula(produto: Produto) {
            nome.text = produto.nome
            descricao.text = produto.descricao
            valor.text = formataParaMoedaBrasileira(produto.valor)
            binding.imageView.load(produto.imagem)


            val visibilidade = if(produto.imagem != null){
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageView.visibility = visibilidade

            binding.imageView.tentaCarregarImagem(produto.imagem)

        }

        private fun formataParaMoedaBrasileira(valor: BigDecimal): String {
            val formatador: NumberFormat = NumberFormat
                .getCurrencyInstance(Locale("pt", "br"))
            return formatador.format(valor)
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
