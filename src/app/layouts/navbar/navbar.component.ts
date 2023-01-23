import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/shared/services/token-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isConneted: string;
  connected: string;
  user: any;
  userup: any;

  constructor(private router: Router, private token: TokenStorageService) {
    setInterval(() => {
      this.user = JSON.parse(localStorage.getItem('userinfo'));
      this.userup = JSON.parse(localStorage.getItem('userupdate'));
      }, 5000);
   }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('userinfo'));
    this.userup = JSON.parse(localStorage.getItem('userupdate'));
    console.log(this.user)
    this.isConneted = "no";
    this.connected = window.sessionStorage.getItem('connected');
    if(this.connected == 'yes')
    this.isConneted = "yes";
  }

  logout() {
    this.token.signOut();
    this.router.navigate(['/login']);
  }

}
