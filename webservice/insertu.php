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


$tipo=$_GET['tipou'];


$telefono=$_GET['telefono'];



$query = "insert into usuario values('".$codigo."','".$tipo."','".$telefono."')";


$consulta = pg_query($conexion, $query);



pg_close($conexion);


}




?>



