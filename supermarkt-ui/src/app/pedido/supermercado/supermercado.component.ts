import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Avaliacao } from 'src/app/pedido/modelos/avaliacao';
import { AvaliacoesService } from 'src/app/pedido/servicos/avaliacoes.service';
import { PedidoService } from 'src/app/pedido/servicos/pedido.service';
import { NotificacaoService } from 'src/app/shared/services/notificacao.service';
import { ItemEstoque } from 'src/app/supermercados/modelos/item-estoque';
import { EstoqueService } from 'src/app/supermercados/servicos/estoque.service';
import { Cliente } from '../modelos/cliente';
import { Entrega } from '../modelos/entrega';
import { ItemPedido } from '../modelos/item-pedido';
import { Pedido } from '../modelos/pedido';
import { SupermercadoComAvaliacao } from '../modelos/supermercado-com-avaliacao';


@Component({
  selector: 'app-supermercado',
  templateUrl: './supermercado.component.html',
  styleUrls: ['./supermercado.component.scss']
})
export class SupermercadoComponent implements OnInit {

  supermercadoComAvaliacao: SupermercadoComAvaliacao;
  avaliacoes: Avaliacao[];
  estoqueProdutos: ItemEstoque[];
  pedido: Pedido;
  itemDoPedidoEscolhido: ItemPedido;
  adicionandoItemAoPedido = false;

  displayModalPedido = false;

  displayModalEntrega = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private avaliacoesService: AvaliacoesService,
    private pedidoService: PedidoService,
    private estoqueService: EstoqueService,
    private notificaoServico: NotificacaoService
  ) { }

  ngOnInit(): void {
    const supermercadoId = this.route.snapshot.params.supermercadoId;
    this.pedidoService.getSupermercadoComAvaliacaoPorId(supermercadoId)
      .subscribe(supermercado => {
        this.supermercadoComAvaliacao = supermercado;
        this.pedido = new Pedido();
        this.pedido.supermercado = this.supermercadoComAvaliacao.supermercado;
        this.pedido.entrega = new Entrega();
        this.pedido.entrega.cliente = new Cliente();
        this.pedido.itens = [];
      }, error => this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
        detail: 'Erro ao carregar dados do supermercado.'})
      );

    this.avaliacoesService.porIdDoSupermercado(supermercadoId)
      .subscribe(avaliacoes => {
        this.avaliacoes = avaliacoes;
    },
    error => {
        this.notificaoServico.notificar({severity: 'warn', summary: 'Aviso', detail: 'Este supermercado ainda não foi avaliado.'});
      }
    );

    this.estoqueService.estoquePorSupermercadoId(supermercadoId)
      .subscribe(estoqueProdutos => {
        this.estoqueProdutos = estoqueProdutos;
      }, error => this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
      detail: 'Erro ao carregar lista de produtos.'})
      );
  }

  escolheItem(itemEstoque: ItemEstoque): void {
    const indice = this.pedido.itens.findIndex(i => i.itemEstoque.id === itemEstoque.id);
    if (indice < 0) {
      this.itemDoPedidoEscolhido = { quantidade: 1, observacao: '', itemEstoque};
      this.adicionandoItemAoPedido = true;
    } else {
      this.itemDoPedidoEscolhido = Object.assign({}, this.pedido.itens[indice]);
    }
    this.showHideDialogPedido();
  }

  editaItemDoPedido(itemPedido: ItemPedido): void {
    this.itemDoPedidoEscolhido = Object.assign({}, itemPedido);
    this.showHideDialogPedido();
  }

  removeItemDoPedido(itemPedido: ItemPedido): void {
    this.pedido.itens = this.pedido.itens.filter(i => i.itemEstoque.id !== itemPedido.itemEstoque.id);
    this.itemDoPedidoEscolhido = undefined;
    this.adicionandoItemAoPedido = false;
  }

  fazPedido(): void {
    this.pedido.supermercado = this.supermercadoComAvaliacao.supermercado;
    this.pedido.entrega.cliente = new Cliente();
    this.showHideDialogEntrega();
  }

  salvaItemNoPedido(): void {
    if (this.adicionandoItemAoPedido) {
      this.pedido.itens.push(this.itemDoPedidoEscolhido);
    } else if (this.itemDoPedidoEscolhido) {
      const indice = this.pedido.itens.findIndex(i => i.itemEstoque.id === this.itemDoPedidoEscolhido.itemEstoque.id);
      this.pedido.itens[indice] = this.itemDoPedidoEscolhido;
    }
    this.itemDoPedidoEscolhido = undefined;
    this.adicionandoItemAoPedido = false;
  }

  calculaSubTotal(itemPedido: ItemPedido): number {
    const item = itemPedido.itemEstoque;
    const preco = item.precoPromocional || item.preco;
    return itemPedido.quantidade * preco;
  }

  totalDoPedido(): number {
    let total = this.supermercadoComAvaliacao.supermercado.taxaDeEntregaEmReais || 0;
    if (this.pedido.itens) {
      this.pedido.itens.forEach(item => {
        total += this.calculaSubTotal(item);
      });
    }
    return total;
  }

  registraEntrega(): void {
    this.pedidoService.adiciona(this.pedido)
    .subscribe(pedido => {
      this.router.navigateByUrl(`pedidos/${pedido.id}/pagamento`);
      this.showHideDialogEntrega();
    });

  }

  showHideDialogPedido(): void {
    this.displayModalPedido = (this.displayModalPedido ? false : true);
  }

  showHideDialogEntrega(): void {
    this.displayModalEntrega = (this.displayModalEntrega ? false : true);
  }

}
