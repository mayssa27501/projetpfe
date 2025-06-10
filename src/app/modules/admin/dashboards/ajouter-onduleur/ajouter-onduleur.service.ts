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
export class AjouterOnduleurService {
  private ajoutUrl = 'http://localhost:8080/onduleurs';
  private getUrl = 'http://localhost:8080/query/onduleurs';
  private sitesUrl = 'http://localhost:8080/query/sites';
  private localesUrl = 'http://localhost:8080/query/locales';
  private boxesUrl = 'http://localhost:8080/query/boxes';

  constructor(private http: HttpClient) {}

  getOnduleurs(): Observable<any[]> {
    return this.http.get<any[]>(this.getUrl, httpOptions);
  }

  getSites(): Observable<any[]> {
    return this.http.get<any[]>(this.sitesUrl, httpOptions);
  }

  getLocales(): Observable<any[]> {
    return this.http.get<any[]>(this.localesUrl, httpOptions);
  }

  getBoxes(): Observable<any[]> {
    return this.http.get<any[]>(this.boxesUrl, httpOptions);
  }

  ajouterOnduleur(onduleur: any): Observable<any> {
    return this.http.post<any>(this.ajoutUrl, onduleur, httpOptions);
  }

  updateOnduleur(id: number, onduleur: any): Observable<any> {
    return this.http.put<any>(`${this.ajoutUrl}/${id}`, onduleur, httpOptions);
  }

  deleteOnduleur(id: number): Observable<void> {
    return this.http.delete<void>(`${this.ajoutUrl}/${id}`, httpOptions);
  }
}