<?php
    
    $host = "127.0.0.1";
    $port = "5432";
    $user = "postgres";
    $database="biblioteca";
    $password="67895421d";
    $conexion=pg_connect("host=$host port=$port user=$user dbname=$database password=$password");

    //si fallara la conexion con la BD 

    if (!$conexion) {    
        echo "error en la conexion"; 
    }
    else
    { 
    //echo 'conexion correctamente';
    $codigo=$_GET['codigo'];

    $query = "SELECT prestamo.idp,prestamo.fecha,usuario.codigo,nombres,apellidos,tipo_u,(libro.codigo) as codigoL,titulo,valorl,tipo_coleccion FROM (((((prestamo inner join usuario on prestamo.codigo_u=usuario.codigo)inner join persona on usuario.codigo=persona.codigo)inner join prestamoslibros on prestamo.idp=prestamoslibros.idp)inner join libro on prestamoslibros.codigo=libro.codigo)inner join coleccion on libro.idcoleccion=coleccion.id) where usuario.codigo='$codigo' order by idp desc"; 
    $consulta = pg_query($conexion, $query);
    if(!$consulta){
    }else{
        while($row = pg_fetch_array($consulta)){
           $flag[] =$row;  
        }
        
            
         
            print(json_encode($flag));
        
    }
    pg_close($conexion);
    }

?>
