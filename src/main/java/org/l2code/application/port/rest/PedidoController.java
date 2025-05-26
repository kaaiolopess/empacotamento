package org.l2code.application.port.rest;

import com.seumanoel.api.controller.ApiApi;
import com.seumanoel.api.model.RequisicaoEmpacotamento;
import com.seumanoel.api.model.RespostaEmpacotamento;
import org.l2code.application.mapper.PedidoRequestMapper;
import org.l2code.domain.useCase.EmpacotamentoUseCase;
import org.l2code.domain.useCase.interfaces.IEmpacotamentoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController implements ApiApi {

    private final IEmpacotamentoUseCase empacotamentoUseCase;
    private final PedidoRequestMapper pedidoRequestMapper;

    public PedidoController(EmpacotamentoUseCase empacotamentoUseCase, PedidoRequestMapper pedidoRequestMapper) {
        this.empacotamentoUseCase = empacotamentoUseCase;
        this.pedidoRequestMapper = pedidoRequestMapper;
    }

    @Override
    public ResponseEntity<RespostaEmpacotamento> empacotarPedidos(RequisicaoEmpacotamento pedidosRequest) {
        empacotamentoUseCase.executar(pedidoRequestMapper.toPedidoDTO(pedidosRequest));
        return ResponseEntity.ok().build();
    }
}
