import { Component, OnInit } from '@angular/core';
import { ClienteDetalhesComponent } from '../cliente-detalhes/cliente-detalhes.component';
import { Observable } from "rxjs";
import { ClienteService } from "../cliente.service";
import { Cliente } from "../cliente";
import { Router } from '@angular/router';

@Component({
  selector: 'app-cliente-lista',
  templateUrl: './cliente-lista.component.html',
  styleUrls: ['./cliente-lista.component.css']
})


  export class ClienteListaComponent implements OnInit {
    clientes: Observable<Cliente[]>;
  
    constructor(private clienteService: ClienteService,
      private router: Router) {}
  
    ngOnInit() {
      this.reloadData();
    }
  
    reloadData() {
      this.clientes = this.clienteService.getClientesList();
    }
  
    deleteCliente(id: number) {
      this.clienteService.deleteCliente(id)
        .subscribe(
          data => {
            console.log(data);
            this.reloadData();
          },
          error => console.log(error));
    }
  
    clienteDetalhes(id: number){
      this.router.navigate(['details', id]);
    }

    updateCliente(id: number){
      this.router.navigate(['update', id]);
    }

}
