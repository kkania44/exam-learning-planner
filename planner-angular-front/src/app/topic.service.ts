import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from './topic';

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private url = 'http://localhost:8080/topics';

  constructor(private http: HttpClient) { }

  getAllTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(this.url);
  }

  add(newTopic: Topic): Observable<Topic> {
    return this.http.post<Topic>(this.url, newTopic, this.httpOptions);
  }

  start(topicId: number): Observable<Topic> {
    return this.http.put<Topic>(`${this.url}/${topicId}`, topicId, this.httpOptions);
  }

  update(topic: Topic): Observable<Topic> {
    return this.http.put<Topic>(this.url, topic, this.httpOptions);
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

}
