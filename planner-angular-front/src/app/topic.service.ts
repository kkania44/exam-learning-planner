import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from './topic';

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private url: 'http://localhost:8080/topics';

  constructor(private http: HttpClient) { }

  getAllTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>('http://localhost:8080/topics');
  }

  add(newTopic: Topic): Observable<Topic> {
    return this.http.post<Topic>('http://localhost:8080/topics', newTopic, this.httpOptions);
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

}
