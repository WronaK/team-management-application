import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TeamsDetailsService} from "../teams-details.service";
import {SprintBacklog} from "../../model/sprint.backlog";

@Component({
  selector: 'app-sprint-backlog',
  templateUrl: './sprint-backlog.component.html',
  styleUrls: ['./sprint-backlog.component.scss']
})
export class SprintBacklogComponent implements OnInit {

  // tasksPBI: Task[]  = [];
  // tasksTasks: Task[] = [];
  // tasksInProgress: Task[]  = [];
  // tasksMergeRequest: Task[]  = [];
  // tasksDone: Task[] = [];

  sprintBacklog!: SprintBacklog;

  pbi = 'PBI';
  tasks = 'TASKS';
  inProgress = 'IN-PROGRESS';
  mergeRequest = 'MERGE REQUEST';
  done = 'DONE';

  constructor(
    private route: ActivatedRoute,
    private teamsDetailsService: TeamsDetailsService
  ) {}

  ngOnInit(): void {
    this.teamsDetailsService.loadsSprintBacklog();
    this.teamsDetailsService.getSprintBacklog().subscribe(
      sprintBacklog => {if(sprintBacklog !=null) this.sprintBacklog=sprintBacklog}
    )
  }
}
