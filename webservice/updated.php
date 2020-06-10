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





//echo 'conexion correctamente';s


$codigo=$_GET['codigo'];





$query = "update devolucion set multa_d='si' where idd='$codigo'"; 





$consulta = pg_query($conexion, $query);





if(!$consulta){





}else{






}





}





pg_close($conexion);











?>



