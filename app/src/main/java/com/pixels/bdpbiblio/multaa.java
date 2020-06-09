package com.pixels.bdpbiblio;

public class multaa
{
    private String Idm,Id_d,Vmulta,Multap,Idp,CodigoU,Nombres,Apellidos,Tipo_u,CodigoL,Titulo,ValorL,TipoC;

    public multaa(String idm,String id_d,String multa,String multa_d,String idp,String codigoU,String nombres,String apellidos,String tipo_u,String codigoL,String titulo,String valorL,String tipoC){
        this.Idm=idm;
        this.Id_d=id_d;
        this.Vmulta=multa;
        this.Multap=multa_d;
        this.Idp=idp;
        this.CodigoU=codigoU;
        this.Nombres=nombres;
        this.Apellidos=apellidos;
        this.Tipo_u=tipo_u;
        this.CodigoL=codigoL;
        this.Titulo=titulo;
        this.ValorL=valorL;
        this.TipoC=tipoC;
	}
    
    public String getIdm(){
        return Idm;
    }
    public void setIdd(String idd){
        Idm=idd;
    }
    public String getId_d(){
        return Id_d;
    }
    
    public String getVmulta(){
        return Vmulta;
    }
    public void setMulta(String multa){
        Vmulta=multa;
    }
    public String getMultap(){
        return Multap;
    }
    public void setMultap(String multa_d){
        Multap=multa_d;
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
