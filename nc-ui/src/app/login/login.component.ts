import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth/service/auth.service';
import { LogInRequest } from '../model/LogInRequest';
import { JWTAuth } from '../model/Jwt-Auth';
import { TokenStorageService } from '../auth/token-storage/token-storage.service';
import { SessionStorageService } from '../SessionStorage/session-storage.service';
import { ShareService } from '../Share/share.service';
import { ProfileDTO } from '../model/profile-dto';
import { RouterService } from '../Router/router.service';
import { Location } from '@angular/common';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  validEmail = true;
  errorMessage = '';
  private loginInfo: LogInRequest;
  private jwtauth: JWTAuth;
  private element;
  isLogged=false;
  private profile: ProfileDTO;
  success = false;
  constructor(private tokenStorage: TokenStorageService, private router: Router, private location: Location,
    private authService: AuthService, private sessionStorageService:SessionStorageService, private shareService:ShareService, private routerService:RouterService) { }

  register() {
    this.router.navigate(['register']);
  }

  // checkEmail() {
  //   this.authService.checkEmail(this.form.username).subscribe(data => {
  //     this.validEmail = true;
  //     this.isLoginFailed = false;


  //   }, error => {
  //     this.errorMessage = error.error.errorMessage;
  //     this.isLoginFailed = true;
  //   })
  // }

  onSubmit() {
    this.success=true;
    console.log(this.form);
    this.loginInfo = new LogInRequest(
      this.form.username, // email zapravo 
      this.form.password);

    this.authService.signIn(this.loginInfo).subscribe(
      data => {
        this.profile=data;
        console.log('huraa');
        console.log(data);
        this.sessionStorageService.saveToken(data.token);
        this.sessionStorageService.saveProfile(data);

        this.isLogged=true;
        this.shareService.sendProfile(this.profile);
        this.shareService.sendIsLogged(true);
        console.log(this.profile);
        
        if(this.routerService.getPreviousUrl() != null){
          if(this.routerService.getPreviousUrl().includes('register')){
            this.router.navigate(['']);
          }else if(this.routerService.getPreviousUrl().includes('admin')){
            this.router.navigate(['']);
          }else{
            this.location.back();
          }
        
        }else{
          this.router.navigate(['']);
        }
        
      },
      error=>{
        this.errorMessage="Wrong password, please try again.";
        alert("Pogresan password ili korisnicko ime!");
        this.success=false;
      }
    );
  }

  ngOnInit() {


  }

}