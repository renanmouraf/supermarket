Habilitem o "strict mode" ligando (false para true) as seguintes flags no arquivo **tsconfig.json**:

```json
   "forceConsistentCasingInFileNames": true,
   "strict": true,
   "noImplicitReturns": true,
   "noFallthroughCasesInSwitch": true,
   ...
   "angularCompilerOptions": {
      "strictInjectionParameters": true,
      "strictInputAccessModifiers": true,
      "strictTemplates": true
   }
```

E arrumar os erros que aparecem.
-------

Arrumar em erros.service.ts

const location = "a";//this.injector.get<LocationStrategy>(LocationStrategy as Type<LocationStrategy>);

const url = "a";//location instanceof PathLocationStrategy ? location.path() : '';

-------

Ajustar Menu quando logado.

Centralizar página
---------------
No Crud de Supermercados: ao salvar, redirecionar para a grade.