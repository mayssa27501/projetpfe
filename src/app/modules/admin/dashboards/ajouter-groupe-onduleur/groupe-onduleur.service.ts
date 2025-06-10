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
export class GroupeOnduleurService {
  private ajoutUrl = 'http://localhost:8080/groupe-onduleurs';
  private getUrl = 'http://localhost:8080/query/groupe-onduleurs';
  private sitesUrl = 'http://localhost:8080/query/sites';
  private onduleursUrl = 'http://localhost:8080/query/onduleurs'; // Nouvel endpoint

  constructor(private http: HttpClient) {}

  getGroupeOnduleurs(): Observable<any[]> {
    return this.http.get<any[]>(this.getUrl, httpOptions);
  }

  getSites(): Observable<any[]> {
    return this.http.get<any[]>(this.sitesUrl, httpOptions);
  }

  getOnduleurs(): Observable<any[]> {
    return this.http.get<any[]>(this.onduleursUrl, httpOptions);
  }

  ajouterGroupeOnduleur(groupe: any): Observable<any> {
    return this.http.post<any>(this.ajoutUrl, { groupeOnduleur: groupe, onduleurIds: groupe.onduleurIds }, httpOptions);
  }

  updateGroupeOnduleur(id: number, groupe: any): Observable<any> {
    return this.http.put<any>(`${this.ajoutUrl}/${id}`, { groupeOnduleur: groupe, onduleurIds: groupe.onduleurIds }, httpOptions);
  }

  deleteGroupeOnduleur(id: number): Observable<void> {
    return this.http.delete<void>(`${this.ajoutUrl}/${id}`, httpOptions);
  }
}