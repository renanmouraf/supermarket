import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NotificacaoService } from 'src/app/shared/services/notificacao.service';
import { FormaPagamento } from '../modelos/forma-pagamento';
import { TipoPagamento } from '../modelos/tipo-pagamento';
import { TipoPagamentoForm } from '../modelos/tipo-pagamento-form';
import { TipoPagamentoService } from '../servicos/tipo-pagamento.service';


@Component({
  selector: 'app-tipo-pagamento-formulario',
  templateUrl: './tipo-pagamento-formulario.component.html',
  styleUrls: ['./tipo-pagamento-formulario.component.scss'],
})
export class TipoPagamentoFormularioComponent implements OnInit {

  tipoForm: FormGroup;
  idTipo: number;
  tipo = {};

  formas: FormaPagamento[];

  constructor(
      private fb: FormBuilder,
      private tipoPagamentoService: TipoPagamentoService,
      private route: ActivatedRoute,
      private notificaoServico: NotificacaoService
  ) { }

  ngOnInit(): void {
    this.tipoForm = this.fb.group({
      id: undefined,
      nome: new FormControl(undefined, Validators.compose([Validators.required, Validators.maxLength(50)])),
      forma: new FormControl(FormaPagamento, Validators.compose([Validators.required])),
    });

    this.tipoPagamentoService.getFormas()
          .subscribe(formas => {
            this.formas = formas;
          },
          erro => {
            this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
            detail: 'Não foi possível efetuar a operação. Tente novamente'});
          }
        );

    this.idTipo = this.route.snapshot.params.idTipo;
    if (this.idTipo) {
        this.tipoPagamentoService.getTipoPagamentoById(this.idTipo)
            .subscribe( tipo => {
                this.updateItemForm(tipo);
            },
            erro => {
              this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
              detail: 'Não foi possível efetuar a operação. Tente novamente'});
            }
        );
    }

  }

  onSubmit(tipoPagamentoForm: TipoPagamentoForm): void {
    const tipoPagamento = new TipoPagamento(tipoPagamentoForm.id, tipoPagamentoForm.nome, tipoPagamentoForm.forma.codigo);
    this.tipoPagamentoService.salva(tipoPagamento)
      .subscribe(
        () => {
          this.tipoForm.reset();
          this.notificaoServico.notificar({severity: 'info', summary: 'Sucesso', detail: 'Operação efetuada com sucesso!'});
        },
        erro => {
          this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
          detail: 'Não foi possível efetuar a operação. Tente novamente'});
        }
      );
  }

  formInvalid(): boolean {
    return !this.tipoForm.valid;
  }

  private updateItemForm(tipo: TipoPagamento): void {
    this.tipoForm.patchValue({
        forma: tipo.forma,
        nome: tipo.nome,
        id: tipo.id
    });
  }

}
