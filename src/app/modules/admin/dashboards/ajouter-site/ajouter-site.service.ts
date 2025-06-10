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
export class AjouterSiteService {
  private ajoutUrl = 'http://localhost:8080/sites';
  private getUrl = 'http://localhost:8080/query/sites';

  constructor(private http: HttpClient) {}

  getSites(): Observable<any[]> {
    return this.http.get<any[]>(this.getUrl, httpOptions);
  }

  ajouterSite(site: any): Observable<any> {
    return this.http.post<any>(this.ajoutUrl, site, httpOptions);
  }

  updateSite(id: number, site: any): Observable<any> {
    return this.http.put<any>(`${this.ajoutUrl}/${id}`, site, httpOptions);
  }

  deleteSite(id: number): Observable<void> {
    return this.http.delete<void>(`${this.ajoutUrl}/${id}`, httpOptions);
  }
}