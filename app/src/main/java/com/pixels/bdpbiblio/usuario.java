package com.pixels.bdpbiblio;

public class usuario
{
	String Codigo,Nombres,Apellidos,Telefono,Tipo_U;
	public usuario(String codigo,String nombres,String apellidos,String telefono,String tipo_u){
		this.Codigo=codigo;
		this.Nombres=nombres;
		this.Apellidos=apellidos;
		this.Telefono=telefono;
		this.Tipo_U=tipo_u;
	}
	public String getCodigo(){
		return Codigo;
	}
	public void setCodigo(String codigo){
		Codigo=codigo;
	}
	public String getNombres(){
		return Nombres;
	}
	public void setNombres(String nombres){
		Nombres=nombres;
	}
	public String getApellidos(){
		return Apellidos;
	}
	public void setApellidos(String apelledios){
		Apellidos=apelledios;
	}
	public String getTelefono(){
		return Telefono;
	}
	public void setTelefono(String telefono){
		Telefono=telefono;
	}
	public String getTipoU(){
		return Tipo_U;
	}
	public void setTipoU(String tipoU){
		Tipo_U=tipoU;
	}
}
