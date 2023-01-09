use JuegoOnline;

create table Usuario(
    Id char(9),
    Nome char(100),
    CodigoUnico char(4) UNIQUE,
    primary key (Id)
);

create table Servidor(
    Id char(9),
    Nome char(100),
    Rexion char(200),
    primary key (Id)
);

create table Mapa(
    Id char(9),
    Nome char(100),
    Dificultad int,
    primary key (Id)
);

create table Servidor_Mapa(
    Id_Servidor char(9),
    Id_Mapa char(9),
    foreign key (Id_Mapa) references Mapa(id)
        on update cascade
        on delete cascade,
    foreign key (Id_Servidor) references Servidor(id)
        on update cascade
        on delete cascade,
    primary key (Id_Servidor,Id_Mapa)
);

create table Persoaxe(
    Id char(9),
    Nome char(100),
    Id_Usuario char(9),
    Id_Servidor char(9),
    foreign key (Id_Usuario) references Usuario(id)
        on update cascade
        on delete cascade,
    foreign key (Id_Servidor) references Servidor(id)
        on update cascade
        on delete cascade,
    primary key (Id)
);

create table Zona(
    Id char(9),
    Nome char(100),
    Ancho int,
    Alto int,
    Id_Mapa char(9),
    foreign key (Id_Mapa) references Mapa(id)
        on update cascade
        on delete cascade,
    primary key (Id)
);


--Consultas--

/*
O número de personaxes por usuario, devolvendo o nome de usuario e o número de personaxes sempre que o número sexa igual ou maior que 1.
*/
SELECT u.Nome, u.Id AS "ID Usuario", COUNT(u.Id) AS "Numero de Persoaxes"
FROM persoaxe p JOIN usuario u
ON u.Id = p.Id_Usuario
GROUP BY u.Id
HAVING COUNT(u.Id) > 0;
/*
O número de personaxes dun usuario X, devolvendo seu nome e número.
*/

/*
Os personaxes dun usuario X, devolvendo o nome do usuario, de cada personaxe e o servidor no que están.
*/

/*
O número de personaxes de cada usuario en cada servidor. Devolvendo o nome de usuario, número de personaxes e nome de servidor.
*/

/*
Os X servidores con máis personaxes ordenados de maior a menor, devolvendo o nome e o número. X é o parámetro que determina o número a delimitar, por exemplo os 3 con máis.
*/

/*
O número de servidores de X rexión. 
*/

/*
O número de servidores de cada rexión.
*/

/*
As zonas dun mapa con id X, devolvendo o nome da zona, o alto e o ancho.
*/