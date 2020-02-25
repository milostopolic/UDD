import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AlertModule } from 'ngx-bootstrap';

import { NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';
import { AuthInterceptor } from './auth/auth-interceptor';
import { LoginComponent } from './login/login.component';
import { AddNewWorkComponent } from './add-new-work/add-new-work.component';
import { SearchComponent } from './search/search.component';

const ChildRoutes =
  [
  ]

  const RepositoryChildRoutes =
  [
    
  ]

const Routes = [  
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "newWork",
    component: AddNewWorkComponent 
  },
  {
    path: "search",
    component: SearchComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AddNewWorkComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(Routes),
    HttpClientModule, 
    HttpModule,
    AlertModule.forRoot(),
    NgMultiSelectDropDownModule.forRoot()
  ],
  
  providers:  [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
