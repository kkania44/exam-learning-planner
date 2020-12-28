import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Subtopic } from './subtopic';
import { Observable, pipe, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SubtopicService {
  private url = "http://localhost:8080/subtopics"

  constructor(private http: HttpClient) { }

  getSubtopicsForTopic(id: number): Observable<Subtopic[]> {
      return this.http.get<Subtopic[]>(`${this.url}/topic/${id}`)
      .pipe(catchError(this.handleError));
  }

  handleError(error: HttpErrorResponse) {
    return throwError(error.error || 'Server error');
  }
}

