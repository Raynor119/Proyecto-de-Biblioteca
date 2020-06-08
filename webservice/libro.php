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


//$nombre=$_GET['nombre'];



//echo $nombre;




$query = "select libro.codigo,titulo,edicion,ciudad,anno,editorial,descripcion,valorl,nombres,apellidos from (((librosautores inner join libro on librosautores.codigo=libro.codigo) inner join autor on librosautores.codigoa=autor.codigo) inner join persona on autor.codigo=persona.codigo) where libro.codigo='$codigo'";



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



