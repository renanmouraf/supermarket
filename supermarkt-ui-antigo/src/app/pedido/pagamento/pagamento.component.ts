import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SelectItem } from 'primeng/api';
import { SupermercadoService } from 'src/app/admin/supermercado/servicos/supermercado.service';
import { PedidoService } from 'src/app/pedido/servicos/pedido.service';
import { NotificacaoService } from 'src/app/shared/services/notificacao.service';
import { Pagamento } from '../modelos/pagamento';
import { Pedido } from '../modelos/pedido';
import { PagamentoService } from '../servicos/pagamento.service';


@Component({
  selector: 'app-pagamento',
  templateUrl: './pagamento.component.html',
  styleUrls: ['./pagamento.component.scss']
})
export class PagamentoComponent implements OnInit {

  pedido: Pedido;
  tiposPagamento: SelectItem[] = [];
  pagamento: Pagamento;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private pagamentoService: PagamentoService,
    private pedidoService: PedidoService,
    private supermercadoService: SupermercadoService,
    private notificaoServico: NotificacaoService
  ) { }

  ngOnInit(): void {
    const pedidoId = this.route.snapshot.params.pedidoId;
    this.pedidoService.porId(pedidoId)
      .subscribe((pedido: Pedido) => {
        this.pedido = pedido;
        this.pagamento = new Pagamento();
        this.pagamento.pedido = pedido;
        this.pagamento.valor = pedido.total;
        this.supermercadoService.tiposPagamento(pedido.supermercado)
          .subscribe(tiposPagamento => {
            tiposPagamento.forEach(tipo => {
              this.tiposPagamento.push({label: tipo.nome, value: tipo});
            });
          }
            , error => this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
            detail: 'Erro ao carregar tipos de pagamento.'}));
      }
      , error => {
        this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
        detail: 'Erro ao inicializar pedido.'});
      }
      );
  }

  criaPagamento(): void {
    this.pagamentoService.cria(this.pagamento)
      .subscribe(pagamento => {
        this.pagamento = pagamento;
      }
      , error => {
        this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
        detail: 'Erro ao criar pagamento.'});
      }
      );
  }

  confirmaPagamento(): void {
    this.pagamentoService.confirma(this.pagamento)
      .subscribe(pagamento => this.router.navigateByUrl(`pedidos/${pagamento.pedido.id}/situacao`),
       error => this.notificaoServico.notificar({severity: 'error', summary: 'Erro', detail: 'Erro na confirmação de pagamento.'}));
  }

  cancelaPagamento(): void {
    this.pagamentoService.cancela(this.pagamento)
      .subscribe(() => this.router.navigateByUrl(``),
      error => this.notificaoServico.notificar({severity: 'error', summary: 'Erro', detail: 'Não foi possível cancelar o pagamento.'}));
  }

}
