
import { HttpErrorResponse } from '@angular/common/http';
import { ErrorHandler, Injectable, Injector, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { NotificacaoService } from '../shared/services/notificacao.service';
import { ErrosService } from './servicos/erros.service';

@Injectable()
export class ErrosHandler implements ErrorHandler {

    constructor(
        private injector: Injector,
        private ngZone: NgZone
    ) {}

    handleError(error: Error | HttpErrorResponse): void {
        const notificaoServico = this.injector.get(NotificacaoService);
        const errosService = this.injector.get(ErrosService);
        const router = this.injector.get(Router);

        if (error instanceof HttpErrorResponse) {
            // Erro servidor
            if (!navigator.onLine) {
                notificaoServico.notificar({severity: 'error', summary: 'Erro', detail: 'Sem conexão.'});
                return;
            }

            notificaoServico.notificar({severity: 'error', summary: 'Erro', detail: `${error.status} - ${error.message}`});
        } else {
            notificaoServico.notificar({severity: 'error', summary: 'Erro', detail: ` ${error.message}`});
        }

        errosService
        .log(error)
        .subscribe(errorWithContextInfo => {
            this.ngZone.run(() => router.navigate(['/error'], { queryParams: errorWithContextInfo })).then();
        });

    }
}

