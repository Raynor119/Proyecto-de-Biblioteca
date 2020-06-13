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

$idp=$_GET['idp'];

$codigo=$_GET['codigo'];

echo $codigo;



$query = "insert into prestamoslibros (idp,codigo) values('".$idp."','".$codigo."')";

$consulta = pg_query($conexion, $query);


pg_close($conexion);

}



?>

