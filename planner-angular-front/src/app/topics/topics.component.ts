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

  add(title: string, daysForLearning: number): void {
    title = title.trim();
    if (!title || !daysForLearning) {
      return;
    }
    let topic = new Topic(title, daysForLearning);
    this.topicService.add(topic)
      .subscribe(topic => this.topics.push(topic));
  }

}
