package br.com.alura.aluraesporte.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.viewmodel.EstadoAppViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: EstadoAppViewModel by viewModel()

    private val controlador by lazy {
        findNavController(R.id.main_activity_nav_host)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        controlador.addOnDestinationChangedListener { controller, destination, arguments ->
            title = destination.label
//            when(destination.id) {
//                R.id.listaProdutos -> supportActionBar?.show()
//                R.id.login -> supportActionBar?.hide()
//            }
            viewModel.appBar.observe(this) {
                it?.let { temAppBar ->
                    if (temAppBar) {
                        supportActionBar?.show()
                    } else {
                        supportActionBar?.hide()
                    }
                }
            }
        }
    }


}
