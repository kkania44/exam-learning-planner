import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { Topic } from '../topic';
import { TopicService } from '../topic.service';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.css'],
  providers: [DatePipe] 
})
export class TopicsComponent implements OnInit {
  @Input() color: ThemePalette;
  topics: Topic[];
  displayedColumns = ['title', 'days for learning', 'progress', 'started on', 'update'];

  constructor(
    private topicService: TopicService,
    private datepipe: DatePipe) 
    { }

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

  start(topic: Topic): void {
    let formattedCurrentDate = this.datepipe.transform(new Date(), 'yyyy-MM-dd');
    topic.startedOn = formattedCurrentDate;
    this.topicService.start(topic.id).subscribe();
  }

}
