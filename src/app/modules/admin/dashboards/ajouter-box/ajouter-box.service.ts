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
export class AjouterBoxService {
  private ajoutUrl = 'http://localhost:8080/boxes';
  private getUrl = 'http://localhost:8080/query/boxes';
  private modelesUrl = 'http://localhost:8080/query/modeles';

  constructor(private http: HttpClient) {}

  getBoxes(): Observable<any[]> {
    return this.http.get<any[]>(this.getUrl, httpOptions);
  }

  getModeles(): Observable<any[]> {
    return this.http.get<any[]>(this.modelesUrl, httpOptions);
  }

  getModele(modeleId: string): Observable<any> {
    return this.http.get<any>(`${this.modelesUrl}/${modeleId}`, httpOptions);
  }

  ajouterBox(box: any): Observable<any> {
    return this.http.post<any>(this.ajoutUrl, box, httpOptions);
  }

  updateBox(id: number, box: any): Observable<any> {
    return this.http.put<any>(`${this.ajoutUrl}/${id}`, box, httpOptions);
  }

  deleteBox(id: number): Observable<void> {
    return this.http.delete<void>(`${this.ajoutUrl}/${id}`, httpOptions);
  }
}