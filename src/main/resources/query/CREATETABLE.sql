CREATE TABLE TipologiaEventoAvverso(
	Id SERIAL PRIMARY KEY,
	Nome varchar(30)
);

CREATE TABLE CentroVaccinale(
	Id SERIAL PRIMARY KEY,
	Nome varchar(30) UNIQUE,
	NomeIndirizzo varchar(30),
	Comune varchar(30),
	Numero varchar(5),
	Qualificatore varchar(6),
	SiglaProvincia varchar(2),
	Cap varchar(5),
	Tipologia varchar(15),
	UNIQUE(NomeIndirizzo, Comune, Numero, Qualificatore)
);

CREATE TABLE Vaccino(
	Id SERIAL PRIMARY KEY,
	Nome varchar(15)
);

CREATE TABLE EventoAvverso(
	Id SERIAL PRIMARY KEY,
	Severita numeric(1,0),
	Note varchar(256),
	TipologiaEventoAvversoId INTEGER REFERENCES TipologiaEventoAvverso(Id),
	CentroVaccinaleId INTEGER REFERENCES CentroVaccinale(Id),
	CHECK(Severita >= 0 AND Severita <= 5)
);

CREATE TABLE Vaccinato(
	CodiceFiscale varchar(16) PRIMARY KEY,
	Nome varchar(20),
	Cognome varchar(30),
	UserId varchar(25) UNIQUE,
	email varchar(30) UNIQUE,
	password varchar(64)
);


CREATE TABLE Vaccinazione(
	Id varchar(16) PRIMARY KEY,
	DataVaccinazione DATE,
	VaccinoId INTEGER REFERENCES Vaccino(Id),
	CentroVaccinaleId INTEGER REFERENCES CentroVaccinale(Id),
	VaccinatoCodiceFiscale varchar(16) REFERENCES Vaccinato(CodiceFiscale)
);