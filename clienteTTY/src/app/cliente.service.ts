import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Estado } from './estado';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private baseUrl = 'http://localhost:8080/tty/clientes/';
  private estadoUrl = 'http://localhost:8080/tty/estados/';

  constructor(private http: HttpClient) { }

  getCliente(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createCliente(cliente: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, cliente);
  }

  updateCliente(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteCliente(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getClientesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  

  getEstados(): Observable<any> {
    return this.http.get(`${this.estadoUrl}`);
  }

}
