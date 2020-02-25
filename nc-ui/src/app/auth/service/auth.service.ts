import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SignUpRequest } from '../../model/SignUpRequest';
import { LogInRequest } from '../../model/LogInRequest';
import { JWTAuth } from '../../model/Jwt-Auth';

const authUrl = "http://localhost:8080/api/auth/" 

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private loginUrl = authUrl + 'login';
  private signupUrl = authUrl + 'signup';
  private emailCheck = authUrl + 'checkEmail';
  private getLoggedUrl = authUrl + 'getLogged';
  private validEmailUrl = authUrl + 'validEmail/';
  private confirmUserUrl = authUrl + 'confirm/';
  private logoutUrl = authUrl + 'user/logout';
  private updUser = authUrl + 'updateUser';


  constructor(private http: HttpClient) {
  }

  
  // attemptAuth(credentials: LogInRequest): Observable<JWTAuth> {
  //   return this.http.post<JWTAuth>(this.loginUrl, credentials);
  // }
  
  // signUp(info: SignUpRequest): Observable<any> {
  //   return this.http.post<string>(this.signupUrl, info);
  // } 
  
  getLogged(): Observable<any> {
    return this.http.get(this.getLoggedUrl);
  }
  
  logout() :Observable<any> {
    return this.http.get(this.logoutUrl);
  }

  // validEmail(email: String) :Observable<any> {
  //   return this.http.get(this.validEmailUrl+email);
  // }

  // checkEmail(email: String) :Observable<any> {
  //   return this.http.post(this.emailCheck, email);
  // }

  signIn(loginParams): Observable<any>{
    return this.http.post(this.loginUrl,loginParams);
  }

  
}
