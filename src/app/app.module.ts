import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { LayoutsComponent } from './layouts/layouts.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { HomeComponent } from './home/home.component';
import { FindtalentComponent } from './findtalent/findtalent.component';
import { GetintouchComponent } from './getintouch/getintouch.component';
import { AboutusComponent } from './aboutus/aboutus.component';
import { LoginComponent } from './login/login.component';
import { OffreComponent } from './offre/offre.component';
import { ListeoffreComponent } from './listeoffre/listeoffre.component';
import { ListeprojetComponent } from './listeprojet/listeprojet.component';
import { ListecondidatureComponent } from './listecondidature/listecondidature.component';
import { ProfilComponent } from './profil/profil.component';
import { ContactComponent } from './contact/contact.component';
import { PortfeuilleComponent } from './portfeuille/portfeuille.component';

import { GestionoffreComponent } from './gestionoffre/gestionoffre.component';
import { RechercheworkComponent } from './recherchework/recherchework.component';
import { WorkComponent } from './work/work.component';
import { UserComponent } from './user/user.component';
import { MessageComponent } from './dashboard/message/message.component';
import { DashboardComponent } from './dashboard/dashboard.component';

import { ManagetasksComponent } from './dashboard/tasks/managetasks/managetasks.component';
import { ManagebiddersComponent } from './dashboard/tasks/managebidders/managebidders.component';
import { ActivebiddsComponent } from './dashboard/tasks/activebidds/activebidds.component';
import { PosttaskComponent } from './dashboard/tasks/posttask/posttask.component';
import { ManagejobsComponent } from './dashboard/userjob/managejobs/managejobs.component';
import { ManagecondidatesComponent } from './dashboard/userjob/managecondidates/managecondidates.component';
import { PostjobComponent } from './dashboard/userjob/postjob/postjob.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { WorkDetailComponent } from './work/work-detail/work-detail.component';
import { TalentDetailComponent } from './findtalent/talent-detail/talent-detail.component';
import { DashboardLayoutComponent } from './dashboard/dashboard-layout/dashboard-layout.component';
import { PostworkComponent } from './postwork/postwork.component';
import { environment } from '../environments/environment';

import{AngularFireDatabaseModule} from '@angular/fire/database';
import{AngularFirestore, AngularFirestoreModule} from '@angular/fire/firestore';
import{AngularFireModule}from '@angular/fire';
import{AngularFireAuth} from '@angular/fire/auth';
import firebase from 'firebase/app';
import { UpdatetaskComponent } from './dashboard/tasks/updatetask/updatetask.component';
import { UpdatetjobComponent } from './dashboard/userjob/updatetjob/updatetjob.component';
import { SignupComponent } from './signup/signup.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { InvoiceComponent } from './invoice/invoice.component';
    
      // Initialize Firebase
      export const config = {
        apiKey: "AIzaSyBif6NBoBC2ySLZgt1HhVxEQ5Wb1GoegH8",
        authDomain: "edemti-f1cb4.firebaseapp.com",
        projectId: "edemti-f1cb4",
        storageBucket: "edemti-f1cb4.appspot.com",
        messagingSenderId: "629004329701",
        appId: "1:629004329701:web:1e70966abfa4a9c2992a0c",
        measurementId: "G-KK2TTXHM7L"
      };
      firebase.initializeApp(config);


@NgModule({
  declarations: [
    AppComponent,
    LayoutsComponent,
    NavbarComponent,
    FooterComponent,
    HomeComponent,
    FindtalentComponent,
    GetintouchComponent,
    AboutusComponent,
    LoginComponent,
    OffreComponent,
    ListeoffreComponent,
    ListeprojetComponent,
    ListecondidatureComponent,
    ProfilComponent,
    ContactComponent,
    PortfeuilleComponent,
    SignupComponent,
    GestionoffreComponent,
    RechercheworkComponent,
    WorkComponent,
    UserComponent,
    MessageComponent,
    DashboardComponent,
    ManagejobsComponent,
    ManagecondidatesComponent,
    PostjobComponent,
    ManagetasksComponent,
    ManagebiddersComponent,
    ActivebiddsComponent,
    PosttaskComponent,
    WorkDetailComponent,
    TalentDetailComponent,
    DashboardLayoutComponent,
    PostworkComponent,
    UpdatetaskComponent,
    UpdatetjobComponent,
    ForgetPasswordComponent,
    CheckoutComponent,
    InvoiceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    //AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireDatabaseModule,
    AngularFirestoreModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [AngularFireAuth,
    AngularFirestore,],
  bootstrap: [AppComponent]
})
export class AppModule { }
