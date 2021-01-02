import { Component, Input, OnInit } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Topic } from '../topic';
import { TopicService } from '../topic.service';

@Component({
  selector: 'app-update-topic',
  templateUrl: './update-topic.component.html',
  styleUrls: ['./update-topic.component.css']
})
export class UpdateTopicComponent implements OnInit {
  @Input() color: ThemePalette;

  constructor(
    private topicService: TopicService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  update(newTitle: string, learningDays: number): void {
    let topic = new Topic(newTitle, learningDays);
    topic.id = this.getIdFromUrl();
    this.topicService.update(topic).subscribe();
  }

  private getIdFromUrl(): number {
    let id: number;
    this.route.params.forEach((params: Params) => {
      id = +params['id']
    });
    return id;
  }

}
