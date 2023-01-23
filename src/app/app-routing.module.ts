
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactComponent } from './contact/contact.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MessageComponent } from './dashboard/message/message.component';
import { ActivebiddsComponent } from './dashboard/tasks/activebidds/activebidds.component';
import { PosttaskComponent } from './dashboard/tasks/posttask/posttask.component';
import { FindtalentComponent } from './findtalent/findtalent.component';
import { HomeComponent } from './home/home.component';
import { LayoutsComponent } from './layouts/layouts.component';
import { LoginComponent } from './login/login.component';
import { WorkComponent } from './work/work.component';
import { ManagetasksComponent } from './dashboard/tasks/managetasks/managetasks.component';
import { ManagebiddersComponent } from './dashboard/tasks/managebidders/managebidders.component';
import { ManagejobsComponent } from './dashboard/userjob/managejobs/managejobs.component';
import { AboutusComponent } from './aboutus/aboutus.component';
import { WorkDetailComponent } from './work/work-detail/work-detail.component';
import { TalentDetailComponent } from './findtalent/talent-detail/talent-detail.component';
import { DashboardLayoutComponent } from './dashboard/dashboard-layout/dashboard-layout.component';
import { ProfilComponent } from './profil/profil.component';
import { PostjobComponent } from './dashboard/userjob/postjob/postjob.component';
import { UpdatetaskComponent } from './dashboard/tasks/updatetask/updatetask.component';
import { UpdatetjobComponent } from './dashboard/userjob/updatetjob/updatetjob.component';
import { SignupComponent } from './signup/signup.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { InvoiceComponent } from './invoice/invoice.component';


const routes: Routes = [
  {path: '', redirectTo:'/home', pathMatch: 'full'},
  {path:'', component:LayoutsComponent,
  children: [
    {path: '', component: HomeComponent},
    {path: 'home', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: SignupComponent},
    {path: 'work', component: WorkComponent},
    {path: 'findtalent', component: FindtalentComponent},
    {path: 'contact', component: ContactComponent},
    {path: 'aboutus', component: AboutusComponent},
    {path: 'work-detail', component: WorkDetailComponent},
    {path: 'talent-detail', component: TalentDetailComponent},
    {path: 'posttask', component: PosttaskComponent},
    {path: 'checkout', component: CheckoutComponent},
    
  ]},
  {path:'dashboard', component:DashboardLayoutComponent,
  children: [
    {path: '', component: DashboardComponent},
    {path: 'messages', component: MessageComponent},
    {path: 'profile', component: ProfilComponent},        
    {path: 'task/managetasks', component:ManagejobsComponent },
    {path: 'task/posttask', component: PostjobComponent},
    {path: 'job/postjob', component: PosttaskComponent},
    {path: 'task/updatetask', component: UpdatetjobComponent },
    {path: 'job/updatejob', component: UpdatetaskComponent},
    {path: 'job/managejobs', component: ManagetasksComponent},
    
  ]},
  {path: 'login', component: LoginComponent},
  {path:'forget-password', component: ForgetPasswordComponent},
  {path: 'invoice', component: InvoiceComponent}

  
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
