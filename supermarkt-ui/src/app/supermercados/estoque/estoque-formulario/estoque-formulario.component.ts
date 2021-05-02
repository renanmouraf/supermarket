import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NotificacaoService } from 'src/app/shared/services/notificacao.service';
import { ItemEstoque } from '../../modelos/item-estoque';
import { EstoqueService } from '../../servicos/estoque.service';


@Component({
  selector: 'app-estoque-formulario',
  templateUrl: './estoque-formulario.component.html',
  styleUrls: ['./estoque-formulario.component.scss'],
})
export class EstoqueFormularioComponent implements OnInit {

  estoqueForm: FormGroup;
  supermercadoId: number;
  idItemEstoque: number;
  supermercado = {};

  constructor(
    private fb: FormBuilder,
    private notificaoServico: NotificacaoService,
    private estoqueService: EstoqueService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.supermercadoId = this.route.snapshot.params.supermercadoId;
    this.idItemEstoque = this.route.snapshot.params.idItemEstoque;

    if (this.supermercadoId && this.idItemEstoque) {
        this.estoqueService.getItemEstoqueById(this.supermercadoId, this.idItemEstoque)
            .subscribe( supermercado => {
                this.updateItemForm(supermercado);
            },
            erro => {
              this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
               detail: 'Não foi possível efetuar a operação. Tente novamente'});
            }
        );
    }

    this.estoqueForm = this.fb.group({
      id: undefined,
      nome: new FormControl(undefined, Validators.compose([Validators.required, Validators.maxLength(50)])),
      descricao: new FormControl(undefined, Validators.compose([Validators.required, Validators.maxLength(50)])),
      quantidade: new FormControl(undefined, Validators.compose([Validators.required])),
      preco: new FormControl(undefined, Validators.compose([Validators.required])),
      precoPromocional: new FormControl(undefined, Validators.compose([Validators.required])),
    });
  }

  onSubmit(itemEstoque: ItemEstoque): void {
    this.estoqueService.salva(this.supermercadoId, itemEstoque)
      .subscribe(
        () => {
          this.estoqueForm.reset();
          this.notificaoServico.notificar({severity: 'info', summary: 'Sucesso', detail: 'Operação efetuada com sucesso!'});
        },
        erro => {
          this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
           detail: 'Não foi possível efetuar a operação. Tente novamente'});
        }
      );
  }

  formInvalid(): boolean {
    return !this.estoqueForm.valid;
  }

  private updateItemForm(estoque: ItemEstoque): void {
    this.estoqueForm.patchValue({
        precoPromocional: estoque.precoPromocional,
        preco: estoque.preco,
        quantidade: estoque.quantidade,
        descricao: estoque.descricao,
        nome: estoque.nome,
        id: estoque.id
    });
  }

}
