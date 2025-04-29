package com.revisao.ecommerce.services;

import com.revisao.ecommerce.dto.PedidoDTO;
import com.revisao.ecommerce.entities.Pedido;
import com.revisao.ecommerce.entities.StatusDoPedido;
import com.revisao.ecommerce.entities.Usuario;
import com.revisao.ecommerce.repositories.PedidoRepository;
import com.revisao.ecommerce.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;
    
    
    public PedidoDTO inserir(PedidoDTO dto){
        Pedido pedido = new Pedido();
        pedido.setMomento(Instant.now());
        pedido.setStatus(StatusDoPedido.AGUARDANDO_PAGAMENTO);
        Usuario user = usuarioRepository.getReferenceById(dto.getClienteId());
        pedido.setCliente(user);
        pedido = pedidoRepository.save(pedido);
        
        return new PedidoDTO(pedido);
    }

    public List<PedidoDTO> getAll(){
        List<Pedido> lista = pedidoRepository.findAll();

        return lista.stream().map(x -> new PedidoDTO(x)).toList() ;
    }

    public Pedido getById(Long id){
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

    }

    public  PedidoDTO  updtPedido(PedidoDTO dto, Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(pedido.isPresent()){
            Pedido updtPedido = pedido.get();
            if(dto.getStatus() != updtPedido.getStatus()) updtPedido.setStatus(dto.getStatus());
           updtPedido = pedidoRepository.save(updtPedido);
           return new PedidoDTO(updtPedido);
        }
        else {
            return null;
        }
    }

    public  boolean dellById(Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(pedido.isPresent()){
            pedidoRepository.deleteById(id);
            return  true;
        } else{
            return false;
        }
    }


}
