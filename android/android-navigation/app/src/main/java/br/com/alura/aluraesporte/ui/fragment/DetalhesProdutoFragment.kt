package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.alura.aluraesporte.databinding.DetalhesProdutoBinding
import br.com.alura.aluraesporte.extensions.formatParaMoedaBrasileira
import br.com.alura.aluraesporte.model.Produto
import br.com.alura.aluraesporte.ui.activity.CHAVE_PRODUTO_ID
import br.com.alura.aluraesporte.ui.viewmodel.DetalhesProdutoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetalhesProdutoFragment : Fragment() {

    private val produtoId by lazy { requireArguments().getLong(CHAVE_PRODUTO_ID) }
    private val viewModel: DetalhesProdutoViewModel by viewModel { parametersOf(produtoId) }
    private lateinit var binding: DetalhesProdutoBinding
    var quandoProdutoComprado: (produto: Produto) -> Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetalhesProdutoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buscaProduto()
        configuraBotaoComprar()
    }

    private fun configuraBotaoComprar() {
        binding.detalhesProdutoBotaoComprar.setOnClickListener {
            viewModel.produtoEncontrado.value?.let(quandoProdutoComprado)
        }
    }

    private fun buscaProduto() {
        viewModel.produtoEncontrado.observe(this, Observer {
            it?.let { produto ->
                binding.detalhesProdutoNome.text = produto.nome
                binding.detalhesProdutoPreco.text = produto.preco.formatParaMoedaBrasileira()
            }
        })
    }

}