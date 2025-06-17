
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AjouterSocieteService {
  private baseUrl = 'http://localhost:8080/societes';
  private queryUrl = 'http://localhost:8080/query/societes';

  constructor(private http: HttpClient) {}

  getSocietes(): Observable<any[]> {
    return this.http.get<any[]>(this.queryUrl, httpOptions);
  }

  ajouterSociete(societe: any): Observable<any> {
    return this.http.post<any>(this.baseUrl, societe, httpOptions);
  }

  updateSociete(id: number, societe: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${id}`, societe, httpOptions);
  }

  deleteSociete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`, httpOptions);
  }
}
