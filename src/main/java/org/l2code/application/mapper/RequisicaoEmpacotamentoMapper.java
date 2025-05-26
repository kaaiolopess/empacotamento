package org.l2code.application.mapper;


import com.seumanoel.api.model.Dimensoes;
import com.seumanoel.api.model.Pedido;
import com.seumanoel.api.model.Produto;
import com.seumanoel.api.model.RequisicaoEmpacotamento;
import org.l2code.application.dto.DimensoesDTO;
import org.l2code.application.dto.PedidoDTO;
import org.l2code.application.dto.ProdutoDTO;
import org.l2code.application.dto.RequisicaoEmpacotamentoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RequisicaoEmpacotamentoMapper {

    // DTO → Model
    public static RequisicaoEmpacotamento toModel(RequisicaoEmpacotamentoDTO dto) {
        if (dto == null) return null;

        List<Pedido> pedidosModel = dto.getPedidos() == null ? null :
                dto.getPedidos().stream()
                        .map(RequisicaoEmpacotamentoMapper::toModel)
                        .collect(Collectors.toList());

        return new RequisicaoEmpacotamento(pedidosModel);
    }

    public static Pedido toModel(PedidoDTO dto) {
        if (dto == null) return null;

        List<Produto> produtosModel = dto.getProdutos() == null ? null :
                dto.getProdutos().stream()
                        .map(RequisicaoEmpacotamentoMapper::toModel)
                        .collect(Collectors.toList());

        Pedido pedido = new Pedido();
        pedido.setPedidoId(dto.getPedidoId());
        pedido.setProdutos(produtosModel);
        return pedido;
    }

    public static Produto toModel(ProdutoDTO dto) {
        if (dto == null) return null;

        Produto produto = new Produto();
        produto.setProdutoId(dto.getProdutoId());
        produto.setDimensoes(toModel(dto.getDimensoes()));
        return produto;
    }

    public static Dimensoes toModel(DimensoesDTO dto) {
        if (dto == null) return null;

        Dimensoes dimensoes = new Dimensoes();
        dimensoes.setAltura(dto.getAltura());
        dimensoes.setLargura(dto.getLargura());
        dimensoes.setComprimento(dto.getComprimento());
        return dimensoes;
    }

    // Model → DTO
    public static RequisicaoEmpacotamentoDTO toDTO(RequisicaoEmpacotamento model) {
        if (model == null) return null;

        List<PedidoDTO> pedidosDTO = model.getPedidos() == null ? null :
                model.getPedidos().stream()
                        .map(RequisicaoEmpacotamentoMapper::toDTO)
                        .collect(Collectors.toList());

        return RequisicaoEmpacotamentoDTO.builder()
                .pedidos(pedidosDTO)
                .build();
    }

    public static PedidoDTO toDTO(Pedido model) {
        if (model == null) return null;

        List<ProdutoDTO> produtosDTO = model.getProdutos() == null ? null :
                model.getProdutos().stream()
                        .map(RequisicaoEmpacotamentoMapper::toDTO)
                        .collect(Collectors.toList());

        return PedidoDTO.builder()
                .pedidoId(model.getPedidoId())
                .produtos(produtosDTO)
                .build();
    }

    public static ProdutoDTO toDTO(Produto model) {
        if (model == null) return null;

        return ProdutoDTO.builder()
                .produtoId(model.getProdutoId())
                .dimensoes(toDTO(model.getDimensoes()))
                .build();
    }

    public static DimensoesDTO toDTO(Dimensoes model) {
        if (model == null) return null;

        return DimensoesDTO.builder()
                .altura(model.getAltura())
                .largura(model.getLargura())
                .comprimento(model.getComprimento())
                .build();
    }
}