import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  private baseUrl = 'http://localhost:8080/api/messages';
  private createUrl = 'http://localhost:8080/api/messages';

  constructor(private http: HttpClient) { }

  

  getmessages(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createmessages(messages: Object): Observable<Object> {
    return this.http.post(`${this.createUrl}`, messages);
  }

  updatemessages(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deletemessages(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  
  getmessagesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
