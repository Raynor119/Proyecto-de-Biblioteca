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


$query = "select idm,id_d,vmulta,multap,idp,usuario.codigo,nombres,apellidos,tipo_u,(libro.codigo) as codigoL,titulo,valorl,tipo_coleccion from(((((( multa inner join devolucion on multa.id_d=devolucion.idd)inner join prestamo on devolucion.id_p=prestamo.idp )inner join usuario on prestamo.codigo_u=usuario.codigo)inner join persona on usuario.codigo=persona.codigo) inner join libro on prestamo.codigo_l=libro.codigo) inner join coleccion on libro.idcoleccion=coleccion.id)"; 


$consulta = pg_query($conexion, $query);


if(!$consulta){


}else{


while($row = pg_fetch_array($consulta)){


$flag[] =$row; 


}


$tt=json_encode($flag);


if(!$flag){


}else{ 


print(json_encode($flag));


}


}


pg_close($conexion);


}



?>

