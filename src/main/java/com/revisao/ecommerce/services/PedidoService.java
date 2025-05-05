package com.revisao.ecommerce.services;

import com.revisao.ecommerce.dto.ItemDoPedidoDTO;
import com.revisao.ecommerce.dto.PedidoDTO;
import com.revisao.ecommerce.entities.*;
import com.revisao.ecommerce.repositories.ItemDoPedidoRespository;
import com.revisao.ecommerce.repositories.PedidoRepository;
import com.revisao.ecommerce.repositories.ProdutoRepository;
import com.revisao.ecommerce.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService
{

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ItemDoPedidoRespository itemDoPedidoRepository;

    @Transactional
    public PedidoDTO inserir(PedidoDTO dto)
    {
        Pedido pedido = new Pedido();
        pedido.setMomento(Instant.now());
        pedido.setStatus(StatusDoPedido.AGUARDANDO_PAGAMENTO);

        Usuario user = usuarioRepository.getReferenceById(dto.getClienteId());

        pedido.setCliente(user);

        for(ItemDoPedidoDTO itemDto : dto.getItems()){
            Produto produto = produtoRepository.getReferenceById(itemDto.getProdutoId());
            ItemDoPedido item = new ItemDoPedido(pedido, produto, itemDto.getQuantidade(), itemDto.getPreco());
            pedido.getItems().add(item);
        }

        pedido = pedidoRepository.save(pedido);
        itemDoPedidoRepository.saveAll(pedido.getItems());
        return new PedidoDTO(pedido);
    }

}
