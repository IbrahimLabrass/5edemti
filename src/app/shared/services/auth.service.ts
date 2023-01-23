import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { JwtResponse } from './jwt-response';
import { AuthLoginInfo } from './login-info';
import { SignUpInfo } from './signup-info';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'http://127.0.0.1:8080/api/auth/signin';
  private signupUrl = 'http://localhost:8080/api/auth/signup';
  private forgetUrl = 'http://localhost:8080/api/forgot-password?email=freelancer@gmail.com';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }

  forgetpassword(email): Observable<string> {
    console.log(email);
    return this.http.post<string>('http://localhost:8080/api/forgot-password?email=freelancer@gmail.com', httpOptions);
  }

}
