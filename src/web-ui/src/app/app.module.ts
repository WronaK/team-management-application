import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainLayoutComponent } from './main-layout/main-layout.component';
import { ProductBacklogComponent } from './product-backlog/product-backlog.component';
import { MaterialModule } from './material.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatSelectModule} from "@angular/material/select";
import { LoginPageComponent } from './login-page/login-page.component';
import {ReactiveFormsModule} from "@angular/forms";
import { RegistrationPageComponent } from './authorization/registration-page/registration-page.component';
import {MatMenuModule} from "@angular/material/menu";
import { HomePageComponent } from './home-page/home-page.component';
import { JoinTeamComponent } from './join-team/join-team.component';


@NgModule({
  declarations: [
    AppComponent,
    MainLayoutComponent,
    ProductBacklogComponent,
    LoginPageComponent,
    RegistrationPageComponent,
    SprintBacklogComponent,
    TaskStatesComponent,
    HomePageComponent,
    JoinTeamComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatExpansionModule,
    MatMenuModule,
    DragDropModule
  ],

  entryComponents: [
    AddTaskToProductBacklogComponent,
    ShowTaskFromProductBacklogComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
