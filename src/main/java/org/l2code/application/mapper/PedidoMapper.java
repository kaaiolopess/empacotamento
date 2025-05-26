package org.l2code.application.mapper;

import com.seumanoel.api.model.PedidoRequisicao;
import org.l2code.application.dto.PedidoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProdutoMapper.class})
public interface PedidoMapper {
    PedidoDTO toPedidoDTO(PedidoRequisicao source);
}

