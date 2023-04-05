package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.databinding.ListaProdutosBinding
import br.com.alura.aluraesporte.ui.recyclerview.adapter.ProdutosAdapter
import br.com.alura.aluraesporte.ui.viewmodel.LoginViewModel
import br.com.alura.aluraesporte.ui.viewmodel.ProdutosViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaProdutosFragment : Fragment() {

    private val viewModel: ProdutosViewModel by viewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    private val adapter: ProdutosAdapter by inject()
    private val controlador by lazy {
        findNavController()
    }

    private lateinit var binding: ListaProdutosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verificaSeEstaLogado()
        setHasOptionsMenu(true)
        buscaProdutos()
    }

    private fun verificaSeEstaLogado() {
        if (loginViewModel.naoEstaLogado()) {
            vaiParaLogin()
        }
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
        val direcao = ListaProdutosFragmentDirections.acaoGlobalLogin() //acaoListaProdutosParaLogin()
        controlador.navigate(direcao)
    }

    private fun buscaProdutos() {
        viewModel.buscaTodos().observe(this) { produtosEncontrados ->
            produtosEncontrados?.let {
                adapter.atualiza(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListaProdutosBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        requireActivity().title = "produtos"
        configuraRecyclerView()
    }

    private fun configuraRecyclerView() {
        val divisor = DividerItemDecoration(context, VERTICAL)
        binding.listaProdutosRecyclerview.addItemDecoration(divisor)
        adapter.onItemClickListener = { produtoSelecionado ->
            vaiParaDetalhesDoProduto(produtoSelecionado.id)
        }
        binding.listaProdutosRecyclerview.adapter = adapter
    }

    private fun vaiParaDetalhesDoProduto(produtoId: Long) {
        val direcao = ListaProdutosFragmentDirections
            .acaoListaProdutosParaDetalhesProduto(produtoId)
        controlador.navigate(direcao)
    }

}
