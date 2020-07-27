import { ClienteDetalhesComponent } from './cliente-detalhes/cliente-detalhes.component';
import { CreateClienteComponent } from './criar-cliente/create-cliente.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClienteListaComponent } from './cliente-lista/cliente-lista.component';
import { UpdateClienteComponent } from './update-cliente/update-cliente.component';

const routes: Routes = [
  { path: '', redirectTo: 'cliente', pathMatch: 'full' },
  { path: 'clientes', component: ClienteListaComponent },
  { path: 'add', component: CreateClienteComponent },
  { path: 'update/:id', component: UpdateClienteComponent },
  { path: 'details/:id', component: ClienteDetalhesComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }