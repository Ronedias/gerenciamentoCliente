import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateClienteComponent } from './criar-cliente/create-cliente.component';
import { ClienteDetalhesComponent } from './cliente-detalhes/cliente-detalhes.component';
import { ClienteListaComponent } from './cliente-lista/cliente-lista.component';
import { UpdateClienteComponent } from './update-cliente/update-cliente.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    CreateClienteComponent,
    ClienteDetalhesComponent,
    ClienteListaComponent,
    UpdateClienteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
