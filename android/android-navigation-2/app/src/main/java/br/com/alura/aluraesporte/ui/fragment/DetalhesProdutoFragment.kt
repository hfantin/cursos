package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.databinding.DetalhesProdutoBinding
import br.com.alura.aluraesporte.extensions.formatParaMoedaBrasileira
import br.com.alura.aluraesporte.ui.viewmodel.DetalhesProdutoViewModel
import br.com.alura.aluraesporte.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetalhesProdutoFragment : Fragment() {

    private val argumentos by navArgs<DetalhesProdutoFragmentArgs>()
    private val produtoId by lazy {
        argumentos.produtoId
    }
    private val viewModel: DetalhesProdutoViewModel by viewModel { parametersOf(produtoId) }
    private val loginViewModel: LoginViewModel by viewModel()
    private val controlador by lazy { findNavController() }
    private lateinit var binding: DetalhesProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_lista_produtos, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_lista_produtos_deslogar){
            loginViewModel.desloga()
            vaiParaLogin()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun vaiParaLogin() {
        val direcao = DetalhesProdutoFragmentDirections
            .acaoGlobalLogin()
        controlador.navigate(direcao)
    }

    private fun configuraBotaoComprar() {
        binding.detalhesProdutoBotaoComprar.setOnClickListener {
            viewModel.produtoEncontrado.value?.let {
                vaiParaPagamento()
            }
        }
    }

    private fun vaiParaPagamento() {
        val direcao = DetalhesProdutoFragmentDirections
            .acaoDetalhesProdutoParaPagamento(produtoId)
        controlador.navigate(direcao)
    }

    private fun buscaProduto() {
        viewModel.produtoEncontrado.observe(this) {
            it?.let { produto ->
                binding.detalhesProdutoNome.text = produto.nome
                binding.detalhesProdutoPreco.text = produto.preco.formatParaMoedaBrasileira()
            }
        }
    }

}