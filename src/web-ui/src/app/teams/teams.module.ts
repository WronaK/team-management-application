import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MaterialModule} from "../material.module";
import {SprintBacklogComponent} from "./sprint-backlog/sprint-backlog.component";
import {TaskStatesComponent} from "./task-states/task-states.component";
import {ReactiveFormsModule} from "@angular/forms";
import {DragDropModule} from "@angular/cdk/drag-drop";
import { MyTeamsComponent } from './my-teams/my-teams.component';
import { AddTeamComponent } from './add-team/add-team.component';
import {MatCardModule} from "@angular/material/card";
import { TeamComponent } from './team/team.component';
import { InformationTeamComponent } from './information-team/information-team.component';
import { MenuTeamComponent } from './menu-team/menu-team.component';
import { MembersComponent } from './members/members.component';
import { ProjectsComponent } from './projects/projects.component';

@NgModule({
  declarations: [
    SprintBacklogComponent,
    TaskStatesComponent,
    MyTeamsComponent,
    AddTeamComponent,
    TeamComponent,
    InformationTeamComponent,
    MenuTeamComponent,
    MembersComponent,
    ProjectsComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    DragDropModule,
    MatCardModule,
  ],
  exports: [
    TeamComponent,
    MyTeamsComponent
  ]
})
export class TeamsModule { }
