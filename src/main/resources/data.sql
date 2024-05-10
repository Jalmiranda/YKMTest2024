
-- Limpieza previa

DROP TABLE IF EXISTS Ships;

-- Se ha pedido una tabla de naves pero no se ha definido nada de ella, asi que se crea con libre albedrio

CREATE TABLE Ships (
	ID int NOT NULL AUTO_INCREMENT,
	MODEL char(30) NOT NULL,
	PURPOSE char(30),
	SIZE_LENGTH decimal(10,2),
	SIZE_HEIGHT decimal(10,2),
	SIZE_WIDTH decimal(10,2),
	WEIGHT decimal(10,2),
	PILOT char(30),
	FRANCHISE char(30) NOT NULL,
	PRIMARY KEY (ID, MODEL)
);

INSERT INTO Ships (MODEL, PURPOSE, SIZE_LENGTH, SIZE_HEIGHT, SIZE_WIDTH, WEIGHT, PILOT, FRANCHISE) values ('X-Wing', 'Combat', 13.4, 2.4, 11.7, 10.0, 'Luke Skywalker', 'Star Wars');
INSERT INTO Ships (MODEL, PURPOSE, PILOT, FRANCHISE) values ('Axalara T9', 'Escape energy storms', 'Lewa', 'BIONICLE');
INSERT INTO Ships (MODEL, PURPOSE, SIZE_LENGTH, SIZE_HEIGHT, SIZE_WIDTH, WEIGHT, PILOT, FRANCHISE) values ('Caucasuskabuto Castle', 'Civilian transport', 160.7, 78.0, 120.8, 11000.0, 'Gira Hustee', 'KingOhger');
INSERT INTO Ships (MODEL, PURPOSE, SIZE_LENGTH, SIZE_HEIGHT, SIZE_WIDTH, WEIGHT, PILOT, FRANCHISE) values ('Arwing', 'Combat', 28.0, 5.5, 14.0, 19.2, 'Fox McCloud', 'Star Fox');
INSERT INTO Ships (MODEL, PURPOSE, PILOT, FRANCHISE) values ('Halberd', 'Combat', 'Meta Knight', 'Hoshi No Kirby');

 