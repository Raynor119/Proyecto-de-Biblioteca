package com.pixels.bdpbiblio;

public class vdevolucion
{
	private String Idd,Fecha,Fecha_p,Multa,Multa_d,Idp,CodigoU,Nombres,Apellidos,Tipo_u;
	
	public vdevolucion(String idd,String fecha,String fecha_p,String multa,String multa_d,String idp,String codigoU,String nombres,String apellidos,String tipo_u){
		this.Idd=idd;
		this.Fecha=fecha;
		this.Fecha_p=fecha_p;
		this.Multa=multa;
		this.Multa_d=multa_d;
		this.Idp=idp;
		this.CodigoU=codigoU;
		this.Nombres=nombres;
		this.Apellidos=apellidos;
		this.Tipo_u=tipo_u;
		
	}
	public String getIdd(){
		return Idd;
	}
	public void setIdd(String idd){
		Idd=idd;
	}
	public String getFecha(){
		return Fecha;
	}
	public void setFecha(String fecha){
		Fecha=fecha;
	}
	public String getFechap(){
		return Fecha_p;
	}
	public void setFechap(String fecha){
		Fecha_p=fecha;
	}
	public String getMulta(){
		return Multa;
	}
	public void setMulta(String multa){
		Multa=multa;
	}
	public String getMulta_d(){
		return Multa_d;
	}
	public void setMulta_d(String multa_d){
		Multa_d=multa_d;
	}
	public String getIdp(){
		return Idp;
	}
	public void setIdp(String idp){
		Idp=idp;
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
	
	
}
