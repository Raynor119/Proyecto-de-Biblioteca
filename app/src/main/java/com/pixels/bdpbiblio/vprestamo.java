package com.pixels.bdpbiblio;

public class vprestamo
{
	private String Idp,Fecha,CodigoU,Nombres,Apellidos,Tipo_u,CodigoL,Titulo,ValorL,TipoC;
	
	public vprestamo(){
		
	}
	public vprestamo(String idp,String fecha,String codigoU,String nombres,String apellidos,String tipo_u,String codigoL,String titulo,String valorL,String tipoC){
		this.Idp=idp;
		this.Fecha=fecha;
		this.CodigoU=codigoU;
		this.Nombres=nombres;
		this.Apellidos=apellidos;
		this.Tipo_u=tipo_u;
		this.CodigoL=codigoL;
		this.Titulo=titulo;
		this.ValorL=valorL;
		this.TipoC=tipoC;
	}
	public String getIdp(){
		return Idp;
	}
	public void setIdp(String idp){
		Idp=idp;
	}
	public String getFecha(){
		return Fecha;
	}
	public void setFecha(String fecha){
		Fecha=fecha;
	}
	public String getCodigoU(){
		return CodigoU;
	}
	public void setCodigoU(String codigoU){
		CodigoU=codigoU;
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
	public String getTipoU(){
		return Tipo_u;
	}
	public void setTipoU(String tipoU){
		Tipo_u=tipoU;
	}
	public String getCodigoL(){
		return CodigoL;
	}
	public void setCodigoL(String codigoL){
		CodigoL=codigoL;
	}
	public String getTitulo(){
		return Titulo;
	}
	public void setTitulo(String titulo){
		Titulo=titulo;
	}
	public String getValorL(){
		return ValorL;
	}
	public void setValorL(String valorL){
		ValorL=valorL;
	}
	public String getTipoC(){
		return TipoC;
	}
	public void setTipoC(String tipoC){
		TipoC=tipoC;
	}
}
