package org.l2code.domain.useCase.interfaces;

import org.l2code.application.dto.RequisicaoEmpacotamentoDTO;

public interface IValidarPedidosRequestUseCase {
    void executar(RequisicaoEmpacotamentoDTO requisicaoEmpacotamentoDTO);
}
