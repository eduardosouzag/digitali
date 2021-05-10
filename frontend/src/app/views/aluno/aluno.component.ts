import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSlideToggleChange } from '@angular/material/slide-toggle';
import { throwError } from 'rxjs';
import { Endereco } from 'src/app/model/endereco';
import { EnderecoService } from 'src/app/service/endereco.service';
import { LoaderService } from 'src/app/service/loader.service';

@Component({
  selector: 'app-aluno',
  templateUrl: './aluno.component.html',
  styleUrls: ['./aluno.component.css']
})
export class AlunoComponent implements OnInit {

  public formAluno:FormGroup = new FormGroup({});

  isPlanoSaude:boolean = false;

  constructor(private formBuilder:FormBuilder, private enderecoService:EnderecoService, public loaderService:LoaderService) { }

  ngOnInit(): void {
    //Cria e associa os campos do formulário do aluno
    this.formAluno = this.formBuilder.group(
    {
      dadosPessoais: this.formBuilder.group(
      {
        cpf:     [null, Validators.required],
        nome:    [null, Validators.required],
        celular: [null, Validators.required],
        dataNascimento: [null, Validators.required],
        email: [null, [Validators.required, Validators.email]]
      }),

      endereco: this.formBuilder.group(
      {
        cep:    [null, Validators.required],
        bairro: [null, Validators.required],
        cidade: [null, Validators.required],
        estado: [null, Validators.required],
        logradouro: [null, Validators.required],
        complemento: [null, Validators.required]
      }),

      planoSaude: this.formBuilder.group(
      {
        numeroCarteira: [null, Validators.required],
        empresaPlanoSaude: [null, Validators.required]
      }),

      cardapio: this.formBuilder.group({
        turma: [null, Validators.required],
      })
    })
  }

  getErrorMessage() {
    const email = this.getField("dadosPessoais.email");
    if (email.hasError('required')) {
      return 'É necessário preencher o campo.';
    }
    return email.hasError('email') ? 'Email inválido' : '';
  }

  public onSubmit = () =>  {
    console.log("salvou")
  }

  private getField = (controlName:string) => {
    const field = this.formAluno.get(controlName);
    if (field)
      return field.value;
    else
      throwError("Campo de nome:" + controlName + "inválido!");
  }

  private setField = (controlName:string, value:any) => {
    const field = this.formAluno.get(controlName);
    if (field)
      field.setValue(value);
    else
      throwError("Campo de nome:" + controlName + "inválido!");
  }

  public consultaCep = () => {
    let cep = this.getField("endereco.cep");
    if (cep) {
      this.enderecoService.consultaCEP(cep).subscribe( (endereco) => {
        let enderecoModel:Endereco = <Endereco>endereco;
        this.setField("endereco.estado", enderecoModel.uf);
        this.setField("endereco.bairro", enderecoModel.bairro);
        this.setField("endereco.cidade", enderecoModel.localidade);
        this.setField("endereco.logradouro", enderecoModel.logradouro);
      });
    }
  }

  public planoSaudeChanged = ($event: MatSlideToggleChange) => {
    this.isPlanoSaude = $event.checked;
  }
}
