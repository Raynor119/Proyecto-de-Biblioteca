create table persona(codigo varchar(13) primary key not null,nombres varchar(255) not null,apellidos varchar(255) not null);

create table libro(codigo varchar(6) primary key not null,
				   titulo text not null,edicion text not null,ciudad text not null,anno int not null,
				   editorial text not null,descripcion text not null,valorl bigint not null,idcoleccion varchar not null);
create table autor(codigo varchar(13) primary key not null);
alter table autor
add foreign key (codigo) references persona(codigo);
create table librosautores(codigo varchar(6) not null,codigoa varchar(13) not null);
alter table librosautores
add primary key (codigo,codigoa),
add foreign key (codigo) references libro(codigo),
add foreign key (codigoa) references autor(codigo);
create table dia (id int primary key not null,dias_sala int not null,dias_reserva int not null,dias_U int not null,dias_D int not null,Dias_A int not null,Dias_O int not null);

create table coleccion(id varchar primary key not null,nombredecoleccion text not null,
					   tipo_coleccion text not null default 'reserva',check(tipo_coleccion='reserva' or tipo_coleccion='sala'),id_dias int not null);

alter table coleccion
add foreign key (id_dias) references dia(id);


alter table libro
add foreign key (idcoleccion) references coleccion(id);

create table usuario(codigo varchar(13) primary key not null,tipo_u varchar not null default 'estudiante',
					 check(tipo_u ='estudiante' or tipo_u ='docentes' or tipo_u ='administrativo' or tipo_u ='otros'),telefono bigint null);
alter table usuario
add foreign key (codigo) references persona(codigo);

create table prestamo(idp serial,codigo_u varchar(13) not null,
					  fecha date not null default now());
alter table prestamo
add primary key (idp),
add foreign key (codigo_u) references usuario(codigo);

create table devolucion(idd serial,id_p int not null,fecha date not null,multa bigint,multa_d text null);
alter table devolucion
add primary key (idd),
add foreign key (id_p) references prestamo(idp);

create table prestamoslibros(idp int not null,codigo varchar(6) not null);
alter table prestamoslibros
add primary key (idp,codigo),
add foreign key (idp) references prestamo(idp),
add foreign key (codigo) references libro(codigo);

SET DateStyle TO European;


insert into persona values('gjgm123','Gabriel José','García Márquez');
insert into persona values('w1e231','Jose','Ramon Chaves');
insert into persona values('dgs144','Jose','Garcia Garcia');
insert into persona values('wad143','Javier','Navarro');
insert into persona values('w1e21w','Jesus','Avila Granados');
insert into persona values('e124wq','Vanesa','Redondo');
insert into persona values('4wqsd1','Luis','Melero Marcos');
insert into persona values('7dfsaq','Gustavo','Hernandez Sanchez');
insert into persona values('wxca12','Fernando','Pardo Ruiz');
insert into persona values('6s12da','Sara','Carballal');

insert into autor values('gjgm123');
insert into autor values('w1e231');
insert into autor values('dgs144');
insert into autor values('wad143');
insert into autor values('w1e21w');
insert into autor values('e124wq');
insert into autor values('4wqsd1');
insert into autor values('7dfsaq');
insert into autor values('wxca12');
insert into autor values('6s12da');

insert into dia values(1,10,3,2,5,5,1);

insert into coleccion values('21wea','Literatura','sala',1);
insert into coleccion values('sda12','Fisica','reserva',1);

insert into libro values('qw3425','Cien años de soledad','edicion de 2017','Ciudad de Mexico',1967,'Random','El libro se compone de 20 capítulos no titulados, en los cuales se narra una historia con una estructura cíclica temporal, puesto que los acontecimientos del pueblo y de la familia Buendía, así como los nombres de los personajes, se repiten una y otra vez, fusionando la fantasía con la realidad.',80000,'21wea');
insert into libro values('xc4513','Derecho administrativo mínimo','edicion de 2020','Salamanca',2020,'Amarante','Editorial Amarante presenta DERECHO ADMINISTRATIVO MÍNIMO. Última obra del prestigioso jurista José Ramón Chaves (Magistrado especialista de lo contencioso-administrativo. Doctor en Derecho)',70000,'sda12');
insert into libro values('sa4372','Croata','Primera Edicion','Madrid',2020,'Planeta','Croata, utilizando la arrolladora fuerza de sus personajes, sube a las más altas esferas de la sociedad para mostrarnos las mayores bajezas del ser humano.',21000,'21wea');
insert into libro values('re6432','Así empezó todo (El origen de la música Pop en Madrid)','Primera Edicion','Madrid',2020,'Amarante','Desgraciadamente, y con mucha frecuencia, se produce el olvido de hechos y personas pioneras de algo que, al desarrollarse en el tiempo, marcan un jalón cultural.',22000,'21wea');
insert into libro values('fq4451','El libro del azafrán (historias y leyendas del oro rojo)','Primera Edicion','Mexico',2020,'Amarante','El azafrán es, sin duda, la especia más antigua y legendaria de la historia de la humanidad. Sus orígenes hay que buscarlos en Oriente Medio, concretamente en la península de Anatolia, allá por el III milenio a.C.;',22000,'sda12');
insert into libro values('za1473','El legado de los malditos','Primera Edicion','Madrid',2020,'Amarante','Estamos en 1840. Años atrás, el prestigioso médico y profesor Don Alfonso Valbuena, se vio obligado a fingir su propia muerte y desaparecer durante años viviendo como un mendigo en las peligrosas calles de Madrid.',25000,'21wea');
insert into libro values('hc4521','El silencio de la lengua','Primera Edicion','Buenos Aires',2020,'Amarante','Transitar por los angostos espacios de la poesía, es como mirar al abismo donde el caminante puede despeñar su realidad, urgir el abandono y hundirse, siempre con dolor, en lo más profundo del ser. Vaciarse para descubrir, en la ausencia, toda la inmediatez de su agonía, todo el miedo a la nada que duerme.',16000,'21wea');
insert into libro values('ld4124','Espacios olvidados en la era de la globalización: Una historia del Barrio de los Pizarrales','Primera Edicion','Madrid',2020,'Amarante','El presente ensayo histórico es una aproximación a la historia de un barrio de clase trabajadora, ubicado en Salamanca: “Los Pizarrales”, a través del estudio de tres generaciones, desde que este comenzase a ser poblado a principios del siglo XX, en forma de asentamientos ',19000,'21wea');
insert into libro values('ty2451','Te espero en la próxima estación','Primera Edicion','Caracas',2020,'El Planeta','Parecía que Fernando Pardo lo había contado todo, pero no. Los trenes y tranvías reinician la marcha empujados por una misteriosa trama que se cierne sobre sus protagonistas.',20000,'21wea');
insert into libro values('fv2141','La mujer del otro','Primera Edicion','Buenos Aires',2020,'Amarante','Editorial Amarante presenta la última obra del escritor almeriense José Navarro quien vuelve a sorprendernos con su impagable caleidoscopio de personajes y narraciones memorialísticas. En esta ocasión "La mujer del otro" se convierte en la novela que más protagonismo le da a su personaje “alter ego” Fabián Cartujano.',19000,'21wea');
insert into libro values('lk2344','Lo inefable del amor','Primera Edicion','Madrid',2020,'El Planeta','A veces el para siempre dura un poco menos, pero no por ello se convierte en mentira. Esta historia es un viaje a la concepción de un amor, un elogio a los buenos momentos mientras se plantea el devenir de la pareja. Porque, ¿y si el para siempre es más verdad si se sabe frenar a tiempo? ',15000,'21wea');



insert into librosautores values('qw3425','gjgm123');
insert into librosautores values('xc4513','w1e231');
insert into librosautores values('sa4372','dgs144');
insert into librosautores values('re6432','wad143');
insert into librosautores values('fq4451','w1e21w');
insert into librosautores values('za1473','e124wq');
insert into librosautores values('hc4521','4wqsd1');
insert into librosautores values('ld4124','7dfsaq');
insert into librosautores values('ty2451','wxca12');
insert into librosautores values('fv2141','6s12da');
insert into librosautores values('lk2344','6s12da');
insert into librosautores values('lk2344','wxca12');




