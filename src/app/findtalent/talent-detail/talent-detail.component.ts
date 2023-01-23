import { CondidatureService } from '../../shared/services/condidature.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Condidature } from '../../shared/classes/condidature';
import { filter } from 'rxjs/operators';
import { ServicesService } from 'src/app/shared/services/services.service';


@Component({
  selector: 'app-talent-detail',
  templateUrl: './talent-detail.component.html',
  styleUrls: ['./talent-detail.component.css']
})
export class TalentDetailComponent implements OnInit {
  talent: any;
  condidatures:  any[];
  user: any;
  board: any;
  services: any;

  constructor(private condidatureService: CondidatureService, private servicesService: ServicesService) { }

  ngOnInit(): void {
    this.talent = JSON.parse(localStorage.getItem('talent'));
    this.user = JSON.parse(localStorage.getItem('userinfo'));
    console.log(this.talent);
    this.getcondidatures();
    this.getservices();
  }

  getcondidatures() {
    this.condidatureService.getcondidatureList().subscribe(
      data => {
        this.board = data;
        //console.log(data);
        this.condidatures = data;
        this.condidatures = this.condidatures.filter(s => {
          return s.user.id == this.user.id;
        })
        console.log(this.condidatures);
      },
      error => {
        //this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
        console.log(error);
      }
      
    );
    //this.condidatureService.getcondidatureList()
    
  }

  getservices() {
    this.servicesService.getserviceList().subscribe(
      data => {
        this.board = data;
        console.log(data);
        this.services = data;
        this.services = this.services.filter(s => {
          return s.user.id == this.talent.id;
        })
        console.log(this.services);
      },
      error => {
        //this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
        console.log(error);
      }
      
    );
    //this.condidatureService.getcondidatureList()
    
  }


}
