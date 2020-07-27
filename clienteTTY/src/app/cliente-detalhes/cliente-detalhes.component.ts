import { Cliente } from '../cliente';
import { Component, OnInit, Input } from '@angular/core';
import { ClienteService } from '../cliente.service';
import { ClienteListaComponent } from '../cliente-lista/cliente-lista.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cliente-details',
  templateUrl: './cliente-detalhes.component.html',
  styleUrls: ['./cliente-detalhes.component.css']
})
export class ClienteDetalhesComponent implements OnInit {

  id: number;
  cliente: Cliente;

  constructor(private route: ActivatedRoute,private router: Router,
    private clienteService: ClienteService) { }

  ngOnInit() {
    this.cliente = new Cliente();

    this.id = this.route.snapshot.params['id'];
    
    this.clienteService.getCliente(this.id)
      .subscribe(data => {
        console.log(data)
        this.cliente = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['clientes']);
  }
}