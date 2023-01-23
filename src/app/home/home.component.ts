import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OffresService } from '../shared/services/offres.service';
import { UserService } from '../shared/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  board: string;
  errorMessage: string;
  freelancers: any;
  offres: any;
  user: any;
  listoffre: any[]=[];

  constructor(private offreService: OffresService,private userService: UserService, private router: Router) {

   // setInterval(()=>{
      this.getUsers();
    //},4000);
   }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('userinfo'));
    console.log(this.user);
    //setInterval(()=>{
      this.getUsers();
   // },4000);
    
    this.getOffrees();
  }

  getOffrees() {
    this.offreService.getOffreList().subscribe(
      data => {
        this.board = data;
        //console.log(data);
        this.offres = data;
        this.offres = this.offres.filter(x=>{
          return x.user.id != this.user.id;
        })
        console.log(this.offres);
        for(let i=0;i<2;i++)
        {
          this.listoffre[i]= this.offres[i];
        }
      },
      error => {
        //this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
        console.log(error);
      }
      
    );
  }

  getUsers(){
    this.userService.getuserList().subscribe(
      data => {
        this.board = data;
        //console.log(data);
        //console.log(data[0].roles[0].name);
        this.freelancers = data;
        this.freelancers = this.freelancers.filter(s => {
          return s.roles[0].name == 'ROLE_FREELANCER';
        });
        console.log(this.freelancers);
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
    
    
    
  }

  detail(users)
  {
    console.log(users);
    localStorage.setItem('talent',JSON.stringify(users));
    this.router.navigate(['/talent-detail']);
  }

}
