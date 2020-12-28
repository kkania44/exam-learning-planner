import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subtopic } from '../subtopic';
import { SubtopicService } from "../subtopic.service";

@Component({
  selector: 'app-subtopics',
  templateUrl: './subtopics.component.html',
  styleUrls: ['./subtopics.component.css']
})
export class SubtopicsComponent implements OnInit {
  subtopics: Subtopic[];
  errorMessage: string;

  constructor(
    private subtopicService: SubtopicService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    let id: number;
    this.route.params.forEach((params: Params) => {
      id = +params['id'];
      this.getSubtopicsForTopic(id);
    });
    
  }

  getSubtopicsForTopic(topicId: number): void {
    this.subtopicService.getSubtopicsForTopic(topicId)
    .subscribe(subtopics => this.subtopics = subtopics,
      error => this.errorMessage = error);
  }

}
