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
export class AjouterLocaleService {
  private ajoutUrl = 'http://localhost:8080/locales';
  private getUrl = 'http://localhost:8080/query/locales';
  private sitesUrl = 'http://localhost:8080/query/sites';

  constructor(private http: HttpClient) {}

  getLocales(): Observable<any[]> {
    return this.http.get<any[]>(this.getUrl, httpOptions);
  }

  getSites(): Observable<any[]> {
    return this.http.get<any[]>(this.sitesUrl, httpOptions);
  }

  ajouterLocale(locale: any): Observable<any> {
    return this.http.post<any>(this.ajoutUrl, locale, httpOptions);
  }

  updateLocale(id: number, locale: any): Observable<any> {
    return this.http.put<any>(`${this.ajoutUrl}/${id}`, locale, httpOptions);
  }

  deleteLocale(id: number): Observable<void> {
    return this.http.delete<void>(`${this.ajoutUrl}/${id}`, httpOptions);
  }
}