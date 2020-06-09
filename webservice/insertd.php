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

$id=$_GET['id_p'];

$fecha=$_GET['fecha'];

$multa=$_GET['multa'];
$multad=$_GET['d'];



$query = "insert into devolucion (id_p,fecha,multa,multa_d) values('".$id."','".$fecha."','".$multa."','".$multad."')";

$consulta = pg_query($conexion, $query);


pg_close($conexion);

}



?>





