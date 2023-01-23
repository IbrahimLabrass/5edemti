import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../shared/services/auth.service';
import { AuthLoginInfo } from '../shared/services/login-info';
import { TokenStorageService } from '../shared/services/token-storage.service';
import { UserService } from './../shared/services/user.service';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;
  loginForm: FormGroup;

  constructor(private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private userService: UserService,
    private fb: FormBuilder, private router: Router) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
    this.loginForm = this.fb.group({
      username: [''],
    })
  }

  login()
  {
    console.log(this.loginForm.value);

   this.authService.forgetpassword(this.loginForm.value)
  }

}
