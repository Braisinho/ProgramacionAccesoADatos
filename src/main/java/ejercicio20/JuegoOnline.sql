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
    foreign key (Id_Mapa) references Mapa(id) on update cascade on delete cascade,
    foreign key (Id_Servidor) references Servidor(id) on update cascade on delete cascade,
    primary key (Id_Servidor, Id_Mapa)
);

create table Persoaxe(
    Id char(9),
    Nome char(100),
    Id_Usuario char(9),
    Id_Servidor char(9),
    foreign key (Id_Usuario) references Usuario(id) on update cascade on delete cascade,
    foreign key (Id_Servidor) references Servidor(id) on update cascade on delete cascade,
    primary key (Id)
);

create table Zona(
    Id char(9),
    Nome char(100),
    Ancho int,
    Alto int,
    Id_Mapa char(9),
    foreign key (Id_Mapa) references Mapa(id) on update cascade on delete cascade,
    primary key (Id)
);

--Consultas--
/*
 O número de personaxes por usuario, devolvendo o nome de usuario e o número de personaxes sempre que o número sexa igual ou maior que 1.
 */
SELECT
    u.Nome,
    COUNT(u.Id) AS Numero_Persoaxe
FROM
    persoaxe p
    JOIN usuario u ON u.Id = p.Id_Usuario
GROUP BY
    u.Id
HAVING
    COUNT(u.Id) > 0;

/*
 O número de personaxes dun usuario X, devolvendo seu nome e número.
 Preguntar Odilo
 */
select
    u.Nome,
    COUNT(u.Nome) as Numero_Persoaxe
from
    persoaxe p
    join usuario u on p.Id_Usuario = u.Id
where
    Id_Usuario = 12;

/*
 Os personaxes dun usuario X, devolvendo o nome do usuario, de cada personaxe e o servidor no que están.
 */
select
    p.Nome as Nome_Persoaxe,
    p.Id as Numero,
    p.Id_Servidor,
    u.Nome as Nome_Usuario
from
    persoaxe p
    JOIN usuario u ON u.Id = p.Id_Usuario
where
    p.Id_Usuario = 12;

/*
 O número de personaxes de cada usuario en cada servidor. Devolvendo o nome de usuario, número de personaxes e nome de servidor.
 */
select
    u.Nome as Nome_Usuario,
    s.Nome as Nome_Servidor,
    COUNT(p.Id) as Numero_Personaxes
from
    persoaxe p
    JOIN Servidor s on p.Id_Servidor = s.Id
    JOIN Usuario u on p.Id_Usuario = u.Id
GROUP BY
    s.Nome,
    u.Nome;

/*
 Os X servidores con máis personaxes ordenados de maior a menor, devolvendo o nome e o número. X é o parámetro que determina o número a delimitar, por exemplo os 3 con máis.
 */
select
    s.Nome,
    COUNT(p.Id)
from
    persoaxe p
    JOIN Servidor s on p.Id_Servidor = s.Id
GROUP BY
    s.Nome
order by
    COUNT(p.Id) desc
limit
    3;

/*
 O número de servidores de X rexión. 
 */
select
    Rexion,
    COUNT(Nome) as Numero_Servidores
from
    servidor
where
    Rexion like 'EUW'
GROUP by
    Rexion;

/*
 O número de servidores de cada rexión.
 */
select
    Rexion,
    COUNT(Nome) as Numero_Servidores
from
    servidor
GROUP by
    Rexion;

/*
 As zonas dun mapa con id X, devolvendo o nome da zona, o alto e o ancho.
 */
select
    Nome,
    Alto,
    Ancho
from
    zona
where
    Id_Mapa = 5;

/*
 rankServers() que mostre por pantalla os 5 servidores con máis personaxes da forma "O servidor X ten Y personaxes".
 */
select
    s.Nome,
    COUNT(p.Id)
from
    persoaxe p
    join servidor s on p.Id_Servidor = s.Id
GROUP by
    s.Nome
order by
    COUNT(p.Id) desc
limit
    5;

/*
 listServers() que mostre o nome dos servidores por rexión coa forma:
 Rexión X
 Servidor1
 Servidor2
 Rexión Y
 Servidor3
 */
select
    Nome,
    Rexion
from
    servidor
order by
    Rexion,
    Nome;

/*getUserPJ(id) que mostre por pantalla o número de personaxes dun usuario en concreto, o número de personaxes por servidor e seus nomes da seguinte forma:
 X (Y personaxes)
    Servidor1
        pj1
    Servidor3
        pj2
        pj3
 */

select
    Id_Servidor,
    Nome
from
    persoaxe
where 
    Id_Usuario = 5;

/*
 userPJs() que mostre por pantalla todos os usuarios e o 
 número de personaxes que ten. Mostra 5 por liña co número entre parénteses.
*/    
select
    u.Nome,
    COUNT(u.Nome) as Nº_de_Persoaxes
from
    persoaxe p 
    join usuario u on p.Id_Usuario = u.Id
 GROUP by
    u.Nome   
order by
    u.nome;    

/*
areaMap(id) que mostre o área dun mapa cun id en concreto. (área = ancho x alto)*/
*/