package org.l2code.domain.useCase;

import lombok.RequiredArgsConstructor;
import org.l2code.application.dto.RequisicaoEmpacotamentoDTO;
import org.l2code.application.exception.ValidationException;
import org.l2code.domain.useCase.interfaces.IValidarPedidosRequestUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class ValidarPedidosRequestUseCase implements IValidarPedidosRequestUseCase {

    public void validar(RequisicaoEmpacotamentoDTO request) {
        if (request == null || request.getPedidos() == null || request.getPedidos().isEmpty()) {
            throw new ValidationException("A lista de pedidos não pode ser nula ou vazia.");
        }

        request.getPedidos().forEach(pedido -> {
            if (pedido.getPedidoId() == null) {
                throw new ValidationException("Pedido sem ID encontrado.");
            }

            if (pedido.getProdutos() == null || pedido.getProdutos().isEmpty()) {
                throw new ValidationException("Pedido " + pedido.getPedidoId() + " não contém produtos.");
            }

            pedido.getProdutos().forEach(produto -> {
                if (produto.getProdutoId() == null || produto.getProdutoId().isBlank()) {
                    throw new ValidationException("Produto sem ID no pedido " + pedido.getPedidoId());
                }

                if (produto.getDimensoes() == null) {
                    throw new ValidationException("Produto " + produto.getProdutoId() + " no pedido " + pedido.getPedidoId() + " está sem dimensões.");
                }
            });
        });
    }
}
