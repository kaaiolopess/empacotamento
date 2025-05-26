package org.l2code.domain.useCase.interfaces;

import org.l2code.application.dto.PedidosRequestDTO;
import org.l2code.domain.dto.RespostaEmpacotamento;

public interface IEmpacotamentoUseCase {
    RespostaEmpacotamento executar(PedidosRequestDTO pedidos);
}
