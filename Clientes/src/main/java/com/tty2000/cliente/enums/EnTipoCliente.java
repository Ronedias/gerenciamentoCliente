package com.tty2000.cliente.enums;

import java.util.ArrayList;
import java.util.List;

public enum EnTipoCliente {
	
	PESSOA(new Integer(0),""),
	PESSOA_FISICA(new Integer(1),"PESSOA FÍSICA"),
	PESSOA_JURIDICA (new Integer(2),"PESSOA JURÍDICA");
	
	
	
	private Integer codigo;
	private String descricao;
	
	private EnTipoCliente(Integer codigo, String descricao) {
	 this.codigo = codigo;
	 this.descricao = descricao;
	}
	
	public static String getDescricao(Integer codigo){
		for (EnTipoCliente cliente : values()) {
			if(codigo != null){
				if(cliente.codigo.intValue() == codigo.intValue()){
					return cliente.getDescricao();
				}
			}
		}
		return null;
	}
	
	public static EnTipoCliente getEnTipoCliente(Integer codigo){
		for (EnTipoCliente cliente : values()) {
			if(codigo != null){
				if(cliente.codigo.intValue() == codigo.intValue()){
					return cliente;
				}
			}
		}
		return null;
	}
	
	public static List<String> getListDescricaoTipoCliente(){
		List<String> listaDescricaoTipoCliente = new ArrayList<String>();
		for (EnTipoCliente tipoCliente : values()) {
			listaDescricaoTipoCliente.add(tipoCliente.descricao);
		}
		return listaDescricaoTipoCliente;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

}
