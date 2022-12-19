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