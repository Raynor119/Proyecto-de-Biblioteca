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

$query = "select titulo from libro";
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

