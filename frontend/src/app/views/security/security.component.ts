import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { Login } from 'src/app/model/login';
import { ServicoService } from 'src/app/service/servico.service';
import { NaviagationURL } from '../components';

@Component({
  selector: 'app-security',
  templateUrl: './security.component.html',
  styleUrls: ['./security.component.css']
})
export class SecurityComponent implements OnInit {

  public formLogin:FormGroup = new FormGroup({});

  constructor(private service: ServicoService, private formBuilder:FormBuilder, private router:Router) {
  }

  ngOnInit(): void {
    this.formLogin = this.formBuilder.group({
      login: [null, Validators.required],
      senha: [null, Validators.required]
    });
  }

  logon = () => {
    if (this.formLogin.valid){
      let authRequest:Login = new Login();
      authRequest.userName = this.getField(this.formLogin, "login");
      authRequest.password = this.getField(this.formLogin, "senha");
      let resp = this.service.generateToken(authRequest);
      resp.subscribe(data => {
        let token = data.valueOf().toString();
        localStorage.setItem("token", token.toString());
        this.access(authRequest);
      });
    }
  }

  private access = (auth:Login) => {
    this.service.logon(auth).subscribe(data => {
      localStorage.setItem("user", data.toString());
      this.router.navigate([NaviagationURL.ALUNO]);
    });
  }

  private getField = (form:FormGroup, controlName:string) => {
    const field = form.get(controlName);
    if (field)
      return field.value;
    else
      throwError("Campo de nome:" + controlName + "inválido!");
  }
}
