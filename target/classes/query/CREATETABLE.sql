CREATE TABLE TipologiaEventoAvverso(
	Id SERIAL PRIMARY KEY,
	Nome varchar(30) NOT NULL
);

CREATE TABLE CentroVaccinale(
	Id SERIAL PRIMARY KEY,
	Nome varchar(30) UNIQUE NOT NULL,
	NomeIndirizzo varchar(30) NOT NULL,
	Comune varchar(30) NOT NULL,
	Numero varchar(5) NOT NULL,
	Qualificatore varchar(6) NOT NULL,
	SiglaProvincia varchar(2) NOT NULL,
	Cap varchar(5) NOT NULL,
	Tipologia varchar(15) NOT NULL,
	UNIQUE(NomeIndirizzo, Comune, Numero, Qualificatore)
);

CREATE TABLE Vaccino(
	Id SERIAL PRIMARY KEY,
	Nome varchar(15) NOT NULL
);

CREATE TABLE EventoAvverso(
	Id SERIAL PRIMARY KEY,
	Severita numeric(1,0) NOT NULL,
	Note varchar(256),
	TipologiaEventoAvversoId INTEGER REFERENCES TipologiaEventoAvverso(Id),
	CentroVaccinaleId INTEGER REFERENCES CentroVaccinale(Id),
	CHECK(Severita >= 0 AND Severita <= 5)
);

CREATE TABLE Vaccinato(
	CodiceFiscale varchar(16) PRIMARY KEY,
	Nome varchar(20) NOT NULL,
	Cognome varchar(30) NOT NULL,
	UserId varchar(25) UNIQUE,
	email varchar(30) UNIQUE,
	password varchar(64)
);


CREATE TABLE Vaccinazione(
	Id varchar(16) PRIMARY KEY,
	DataVaccinazione DATE NOT NULL,
	VaccinoId INTEGER REFERENCES Vaccino(Id),
	CentroVaccinaleId INTEGER REFERENCES CentroVaccinale(Id),
	VaccinatoCodiceFiscale varchar(16) REFERENCES Vaccinato(CodiceFiscale)
);