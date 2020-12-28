import { Component, OnInit } from '@angular/core';
import { Topic } from '../topic';
import { TopicService } from '../topic.service';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.css']
})
export class TopicsComponent implements OnInit {
  topics: Topic[];

  constructor(private topicService: TopicService) { }

  ngOnInit(): void {
    this.getAllTopics();
  }

  getAllTopics() {
    return this.topicService.getAllTopics()
    .subscribe(topics => this.topics = topics);
  }

}
