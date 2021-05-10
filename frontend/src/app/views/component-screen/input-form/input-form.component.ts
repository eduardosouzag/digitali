import { Component, Input, OnInit, forwardRef, Output } from '@angular/core';
import { MatFormFieldControl } from '@angular/material/form-field';


@Component({
  selector: 'app-input-form',
  templateUrl: './input-form.component.html',
  styleUrls: ['./input-form.component.css'],
  providers: [
    {
      provide: MatFormFieldControl,
      useExisting: InputFormComponent
    }
  ]
})
export class InputFormComponent implements OnInit  {

  constructor() { }

  @Input() label:String = '';
  @Input('formControlName') controlName:string= '';

  ngOnInit(): void {
  }

  // getErrorMessage() {
  //   const email = this.getField("dadosPessoais.email");
  //   if (email.hasError('required')) {
  //     return 'É necessário preencher o campo.';
  //   }
  //   return email.hasError('email') ? 'Email inválido' : '';
  // }
}
