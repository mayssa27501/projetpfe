import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map, retry } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AjouterBoxService {
  private ajoutUrl = 'http://localhost:8080/boxes'; // Updated to match backend /boxes
  private getUrl = 'http://localhost:8080/query/boxes';
  private modelesUrl = 'http://localhost:8080/query/modeles';
  private localesUrl = 'http://localhost:8080/query/locales';

  constructor(private http: HttpClient) {}

  getBoxes(): Observable<any[]> {
    return this.http.get<any[]>(this.getUrl, httpOptions).pipe(
      retry(2), // Retry up to 2 times for transient errors
      map(boxes => boxes.map(box => ({
        ...box,
        modele: {
          id: box.modeleId,
          name: box.modeleName,
          attributes: box.modeleAttributes || {}
        },
        localeId: box.localeId || null,
        localeName: box.localeName || 'Non affecté'
      }))),
      catchError(this.handleError('getBoxes'))
    );
  }

  getModeles(): Observable<any[]> {
    return this.http.get<any[]>(this.modelesUrl, httpOptions).pipe(
      retry(2),
      catchError(this.handleError('getModeles'))
    );
  }

  getModele(modeleId: string): Observable<any> {
    return this.http.get<any>(`${this.modelesUrl}/${modeleId}`, httpOptions).pipe(
      retry(2),
      catchError(this.handleError('getModele'))
    );
  }

  getLocales(): Observable<any[]> {
    return this.http.get<any[]>(this.localesUrl, httpOptions).pipe(
      retry(2),
      catchError(this.handleError('getLocales'))
    );
  }

  ajouterBox(box: any): Observable<any> {
    const payload = {
      ...box,
      modele: box.modele ? { id: box.modele.id } : null,
      locale: box.locale ? { id: box.locale.id } : null,
      modeleAttributes: box.modeleAttributes || {}
    };
    console.log('Sending POST payload:', JSON.stringify(payload, null, 2));
    return this.http.post<any>(this.ajoutUrl, payload, httpOptions).pipe(
      retry(2),
      catchError(this.handleError('ajouterBox'))
    );
  }

  updateBox(id: number, box: any): Observable<any> {
    const payload = {
      ...box,
      modele: box.modele ? { id: box.modele.id } : null,
      locale: box.locale ? { id: box.locale.id } : null,
      modeleAttributes: box.modeleAttributes || {}
    };
    console.log('Sending PUT payload to', `${this.ajoutUrl}/${id}`, ':', JSON.stringify(payload, null, 2));
    return this.http.put<any>(`${this.ajoutUrl}/${id}`, payload, httpOptions).pipe(
      retry(2),
      catchError(this.handleError(`updateBox (ID: ${id})`))
    );
  }

  deleteBox(id: number): Observable<void> {
    return this.http.delete<void>(`${this.ajoutUrl}/${id}`, httpOptions).pipe(
      retry(2),
      catchError(this.handleError('deleteBox'))
    );
  }

  private handleError(operation: string) {
    return (error: HttpErrorResponse): Observable<never> => {
      let errorMessage = `Operation ${operation} failed: `;
      if (error.status === 0) {
        errorMessage += 'Network error or CORS issue. Check if backend is running at http://localhost:8080 and CORS is configured for http://localhost:4200. Verify OPTIONS request in browser DevTools.';
      } else {
        errorMessage += `HTTP ${error.status} - ${error.message || JSON.stringify(error.error || 'Unknown error')}`;
      }
      console.error(errorMessage, error);
      return throwError(() => new Error(errorMessage));
    };
  }
}