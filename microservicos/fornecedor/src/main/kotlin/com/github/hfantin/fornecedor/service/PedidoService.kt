package com.github.hfantin.fornecedor.service

import com.github.hfantin.fornecedor.dto.ItemDoPedidoDTO
import com.github.hfantin.fornecedor.model.Pedido
import com.github.hfantin.fornecedor.model.PedidoItem
import com.github.hfantin.fornecedor.model.Produto
import com.github.hfantin.fornecedor.repository.PedidoRepository
import com.github.hfantin.fornecedor.repository.ProdutoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class PedidoService {
    @Autowired
    private lateinit var pedidoRepository: PedidoRepository
    @Autowired
    private lateinit var produtoRepository: ProdutoRepository

    fun realizaPedido(itens: List<ItemDoPedidoDTO>): Pedido {

        val pedidoItens = toPedidoItem(itens)
        val pedido = Pedido().apply {
            this.itens =  pedidoItens
            this.tempoDePreparo = itens.size
        }
        return pedidoRepository.save(pedido)
    }

    fun getPedidoPorId(id: Long) = pedidoRepository.findById(id).orElse(Pedido())

    private fun toPedidoItem(itens: List<ItemDoPedidoDTO>): List<PedidoItem> {
        val idsProdutos = itens.map { it.id }
        val produtosDoPedido: List<Produto> = produtoRepository.findByIdIn(idsProdutos)
        return itens.map {
            val p = produtosDoPedido.find { p -> p.id === it.id }

            PedidoItem().apply {
                this.quantidade = it.quantidade
                this.produto = p
            }
        }
    }
}