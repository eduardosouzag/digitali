import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './views/home/home.component';
import { HeaderComponent } from './views/header/header.component';
import { RoutingModule } from './routing/routing.module';
import { MaterialModule } from './material/material.module';
import { SidenavListComponent } from './views/sidenav-list/sidenav-list.component';
import { AlunoComponent } from './views/aluno/aluno.component';
import { MatNativeDateModule } from '@angular/material/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { InputFormComponent } from './views/component-screen/input-form/input-form.component';
import { InputFormDatepickerComponent } from './views/component-screen/input-form-datepicker/input-form-datepicker.component';
import { DividerComponent } from './views/component-screen/divider/divider.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SecurityComponent } from './views/security/security.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    SidenavListComponent,
    AlunoComponent,
    InputFormComponent,
    InputFormDatepickerComponent,
    DividerComponent,
    SecurityComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    RoutingModule,
    MaterialModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    HttpClientModule,
    FlexLayoutModule,
    BrowserModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
