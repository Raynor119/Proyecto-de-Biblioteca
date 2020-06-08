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

$codigo=$_GET['codigo'];

$nombre=$_GET['nombre'];

$apellido=$_GET['apellido'];


$query = "insert into persona values('".$codigo."','".$nombre."','".$apellido."')";

$consulta = pg_query($conexion, $query);


pg_close($conexion);

}



?>


