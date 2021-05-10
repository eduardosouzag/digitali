import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule} from '@angular/router'
import { HomeComponent } from  '../views/home/home.component'
import { AlunoComponent } from '../views/aluno/aluno.component';
import { SecurityComponent } from '../views/security/security.component';

const routes: Routes = [
  { path:'home', component: HomeComponent, data: {animation: 'HomePage'} },
  { path:'aluno', component: AlunoComponent},
  { path:'login', component: SecurityComponent},
  { path: '', redirectTo: '/login', pathMatch: 'full'}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ]
})
export class RoutingModule { }
