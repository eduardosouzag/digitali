import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from '../model/login';

@Injectable({
  providedIn: 'root'
})
export class ServicoService {

  api = {
    url: "http://localhost:9192/"
  }
  constructor(private http:HttpClient) {}

  public generateToken = (request: Login) => {
    return this.http.post(this.api.url + "authenticate", request, {responseType: 'text' as 'json'});
  }

  public logon = (request:Login) => {
    let tokenStr = 'Bearer ' + localStorage.getItem("token");
    const headers = new HttpHeaders().set("Authorization", tokenStr);
    return this.http.post(this.api.url + "logon", request, {headers, responseType: 'text' as 'json'});
  }
}
