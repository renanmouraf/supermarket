import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { NotificacaoService } from 'src/app/shared/services/notificacao.service';
import { TipoPagamento } from '../modelos/tipo-pagamento';
import { TipoPagamentoService } from '../servicos/tipo-pagamento.service';


@Component({
  selector: 'app-tipo-pagamento-busca',
  templateUrl: './tipo-pagamento-busca.component.html',
  styleUrls: ['./tipo-pagamento-busca.component.scss'],
})
export class TipoPagamentoBuscaComponent implements OnInit {

  tipos: TipoPagamento[];
  inputSearch;

  constructor(
    private tipoPagamentoService: TipoPagamentoService,
    private confirmationService: ConfirmationService,
    private notificaoServico: NotificacaoService
  ) { }

  ngOnInit(): void {
    this.loadTiposPagamento();
  }

  dialogDelete(tipoPagamento: TipoPagamento): void {
    this.confirmationService.confirm({
        message: 'Tem certeza que deseja excluir este item?',
        acceptLabel: 'Sim',
        rejectLabel: 'Não',
        header: 'Confirmação',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
            this.deleteTipo(tipoPagamento);
        },
        reject: () => {
        }
    });
  }

  getTiposDePagamentoByName(nome: string): void {
    this.tipoPagamentoService.getByName(nome)
      .subscribe(
        tipos => {
          this.tipos = tipos;
        },
        error => {
          this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
          detail: 'Não foi possível carregar os itens. Tente novamente'});
        }
      );
  }

  private loadTiposPagamento(): void {
    this.tipoPagamentoService.getTiposPagamento()
        .subscribe(
          tipos => {
            this.tipos = tipos;
          },
          error => {
            this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
            detail: 'Não foi possível carregar os itens. Tente novamente'});
          }
        );
  }

  private deleteTipo(tipoPagamento: TipoPagamento): void {
    this.tipoPagamentoService.remove(tipoPagamento)
      .subscribe(
        () => {
          this.loadTiposPagamento();
          this.notificaoServico.notificar({severity: 'info', summary: 'Sucesso', detail: 'Operação efetuada com sucesso!'});
        },
        error => {
          this.notificaoServico.notificar({severity: 'error', summary: 'Erro', detail: 'Não foi possível excluir o registro.'});
        }
      );
  }

}
