import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegisterPayload } from './auth/register-payload';
import { LoginPayload } from './auth/login-payload';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url = 'http://localhost:8080/api/auth/';

  constructor(
    private httpClient: HttpClient
    ) {
    }
    register(registerPayload: RegisterPayload) :Observable<any>{
      return this.httpClient.post(
        this.url + 'signup',
        registerPayload 
        )
    }

    login(loginPayload: LoginPayload){
      return this.httpClient.post(
        this.url + 'login',
        loginPayload 
      )
    }
}
