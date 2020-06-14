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

$query = "SELECT idd,devolucion.fecha,prestamo.fecha as fecha_p,multa,multa_d,idp,usuario.codigo,nombres,apellidos,tipo_u FROM (((prestamo inner join usuario on prestamo.codigo_u=usuario.codigo)inner join persona on usuario.codigo=persona.codigo)inner join devolucion on prestamo.idp=devolucion.id_p)";
$consulta = pg_query($conexion, $query);

if(!$consulta){

}else{
    $flag[]=null;



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


