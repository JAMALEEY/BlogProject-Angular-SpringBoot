import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { RegisterPayload } from './auth/register-payload';
import { LoginPayload } from './auth/login-payload';
import { JwtAutResponse } from './auth/jwt-aut-response';
import { LocalStorageService } from 'ngx-webstorage';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url = 'http://localhost:8080/api/auth/';

  constructor(
    private httpClient: HttpClient,
    private localStorageService: LocalStorageService
    ) {
    }
    register(registerPayload: RegisterPayload) :Observable<any>{
      return this.httpClient.post(
        this.url + 'signup',
        registerPayload 
        )
    }

    login(loginPayload: LoginPayload): Observable <boolean> {
      return this.httpClient.post <JwtAutResponse> (
        this.url + 'login',
        loginPayload 
      ).pipe(map(data => {
          this.localStorageService.store(
            'authenticationToken',
            data.AuthenticationToken
          );
          this.localStorageService.store(
            'username',
            data.username
          );
          return true;
      } ));
    }
}
