package com.pixels.bdpbiblio;

public class libro
{
	String Codigo,Titulo,Edicion,Ciudad,Anno,Editorial,Descripcion,Valorl,Nombres,Apellidos;
	public libro(String codigo,String titulo,String edicion,String ciudad,String anno,String editorial,String descripcion,String valorl,String nombres,String apellidos){
		this.Codigo=codigo;
		this.Titulo=titulo;
		this.Edicion=edicion;
		this.Ciudad=ciudad;
		this.Anno=anno;
		this.Editorial=editorial;
		this.Descripcion=descripcion;
		this.Valorl=valorl;
		this.Nombres=nombres;
		this.Apellidos=apellidos;
	}
	public String getCodigo(){
		return Codigo;
	}
	public String getTitulo(){
		return Titulo;
	}
	public String getEdicion(){
		return Edicion;
	}
	public String getCiudad(){
		return Ciudad;
	}
	public String getAnno(){
		return Anno;
	}
	public String getEditorial(){
		return Editorial;
	}
	public String getDescripcion(){
		return Descripcion;
	}
	public String getValorl(){
		return Valorl;
	}
	public String getNombres(){
		return Nombres;
	}
	
	public String getApellidos(){
		return Apellidos;
	}

}
