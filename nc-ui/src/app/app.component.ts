import { Component } from '@angular/core';
import { AuthService } from './auth/service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  private user = JSON.parse(localStorage.getItem('user'));
  private role = localStorage.getItem('role');
  
  

  constructor(private router: Router,
    private authService: AuthService) { }

  loggedIn(){
    if(this.user){
      return true; 
    }else{
      return false;
    }
  }

  notLoggedIn(){
    if(!this.user){
      return true; 
    }else{
      return false;
    }
  }

  isAdmin(){
    if(this.role == "ADMIN"){
      return true; 
    }else{
      return false;
    }
  }

  logout() {
    this.authService.logout().subscribe();
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }
}
