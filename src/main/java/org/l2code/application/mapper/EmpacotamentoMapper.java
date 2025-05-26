package org.l2code.application.mapper;

import com.seumanoel.api.model.CaixaResposta;
import com.seumanoel.api.model.PedidoResposta;
import org.l2code.domain.dto.CaixaEmpacotada;
import org.l2code.domain.dto.PedidoEmpacotado;
import org.l2code.domain.dto.RespostaEmpacotamento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpacotamentoMapper {
    com.seumanoel.api.model.RespostaEmpacotamento toRespostaEmpacotamento(RespostaEmpacotamento response);
    List<PedidoResposta> toPedidosResposta(List<PedidoEmpacotado> pedidos);
    PedidoResposta toPedidoResposta(PedidoEmpacotado pedido);
    CaixaResposta toCaixaResposta(CaixaEmpacotada caixa);
}
