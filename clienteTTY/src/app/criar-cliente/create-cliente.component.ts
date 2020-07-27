import { ClienteService } from '../cliente.service';
import { Estado } from '../estado';
import { Cliente } from '../cliente';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder,Validators, AbstractControl } from 
       "@angular/forms"



@Component({
  selector: 'app-create-cliente',
  templateUrl: './create-cliente.component.html',
  styleUrls: ['./create-cliente.component.css']
})


export class CreateClienteComponent implements OnInit {

  estados: Estado[];

  cliente: Cliente = new Cliente();
  submitted = false;
  estadoId: number;
  tipoId: number;
  cidadeId: number;
   

  constructor(
    private clienteService: ClienteService,
    private router: Router,
    ) { }

  ngOnInit() {
    
    this.clienteService.getEstados().subscribe( dados =>{
      this.estados = dados;
      console.log(dados);
    });

  }

  newCliente(): void {
    this.submitted = false;
    this.cliente = new Cliente();
  }

  save() {
    this.clienteService.createCliente(this.cliente)
      .subscribe(data => console.log(data), error => console.log(error));
    this.cliente = new Cliente();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/clientes']);
  }

  onAddEstado(){ 
    this.estadoId = +this.estadoId;
    this.cliente.idEstado = this.estadoId;
    console.log(this.estadoId);
    console.log(this.cliente); 
  }

  onAddTipo(){ 
    this.tipoId = +this.tipoId;
    this.cliente.tipoCliente = this.tipoId;
    console.log(this.tipoId);     
    console.log(this.cliente);  
  }

  onAddCidade(){ 
    this.cidadeId = +this.cidadeId;
    this.cliente.idCidade = this.cidadeId;
    console.log(this.cidadeId);
    console.log(this.cliente); 
  }

}