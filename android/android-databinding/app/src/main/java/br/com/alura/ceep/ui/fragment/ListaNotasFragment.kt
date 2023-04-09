package br.com.alura.ceep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.alura.ceep.databinding.ListaNotasBinding
import br.com.alura.ceep.ui.recyclerview.adapter.ListaNotasAdapter
import br.com.alura.ceep.ui.viewmodel.AppViewModel
import br.com.alura.ceep.ui.viewmodel.ComponentesVisuais
import br.com.alura.ceep.ui.viewmodel.ListaNotasViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaNotasFragment : Fragment() {

    private lateinit var binding: ListaNotasBinding
    private val listaNotasAdapter: ListaNotasAdapter by inject()
    private val viewModel: ListaNotasViewModel by viewModel()
    private val appViewModel: AppViewModel by activityViewModel()
    private val controlador by lazy {
        findNavController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ListaNotasBinding.inflate(inflater, container, false)
        binding.vaiParaFormularioInsercao = View.OnClickListener {
            vaiParaFormulario()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appViewModel.temComponentes = ComponentesVisuais(appBar = null)
        configuraRecyclerView()
        buscaTodasNotas()
    }

    private fun buscaTodasNotas() {
        viewModel.todas.observe(this, Observer(listaNotasAdapter::submitList))
    }

    private fun configuraRecyclerView() {
        binding.listaNotasRecyclerview.run {
            adapter = listaNotasAdapter
        }
        listaNotasAdapter.onItemClickListener = { notaSelecionada ->
            vaiParaFormulario(notaSelecionada.id)
        }
    }

    private fun vaiParaFormulario(notaId: Long = 0L) {
        ListaNotasFragmentDirections
            .acaoListaNotasParaFormularioNota(notaId).run {
                controlador.navigate(this)
            }
    }

}