-- Database: azienda

-- DROP DATABASE azienda;

CREATE DATABASE azienda
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'it_IT.UTF-8'
       LC_CTYPE = 'it_IT.UTF-8'
       CONNECTION LIMIT = -1;
       
-- Sequence: sequenza_id_ordine

-- DROP SEQUENCE sequenza_id_ordine;

CREATE SEQUENCE sequenza_id_ordine
  START WITH 1 INCREMENT  BY 1
  MINVALUE 0
  MAXVALUE 9999999
ALTER TABLE sequenza_id_ordine
  OWNER TO postgres;
 
-- Sequence: sequenza_id_prodotto

-- DROP SEQUENCE sequenza_id_prodotto;

CREATE SEQUENCE sequenza_id_prodotto
  START WITH 1 INCREMENT  BY 1
  MINVALUE 0
  MAXVALUE 9999999
ALTER TABLE sequenza_id_prodotto
  OWNER TO postgres;
  
-- Sequence: sequenza_id_utente

-- DROP SEQUENCE sequenza_id_utente;

CREATE SEQUENCE sequenza_id_utente
  START WITH 1 INCREMENT  BY 1
  MINVALUE 0
  MAXVALUE 9999999
ALTER TABLE sequenza_id_utente
  OWNER TO postgres;
  
-- Table: utente

-- DROP TABLE utente;

CREATE TABLE utente
(
  nome character varying,
  cognome character varying,
  indirizzo character varying,
  email character varying,
  username character varying,
  password character varying,
  role character varying,
  idutente bigint NOT NULL,
  CONSTRAINT idutente PRIMARY KEY (idutente ),
  CONSTRAINT username UNIQUE (username )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE utente
  OWNER TO postgres;

INSERT INTO utente (nome, cognome, username, password, role, idutente) 
					VALUES ('Mario', 'Rossi', 'admin', 'admin', 'admin', '00' );

  
-- Table: prodotto

-- DROP TABLE prodotto;

CREATE TABLE prodotto
(
  codiceprodotto character varying,
  nome character varying,
  descrizione character varying,
  prezzo character varying,
  idprodotto bigint NOT NULL,
  CONSTRAINT idprodotto PRIMARY KEY (idprodotto ),
  CONSTRAINT codiceprodotto UNIQUE (codiceprodotto )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE prodotto
  OWNER TO postgres;

  INSERT INTO prodotto (codiceprodotto, nome, descrizione, prezzo, idprodotto) VALUES ('iPho00', 'iPhone','smartphone','699','00');
INSERT INTO prodotto (codiceprodotto, nome, descrizione, prezzo, idprodotto) VALUES ('com00', 'Laptop','informatica','499','01');
INSERT INTO prodotto (codiceprodotto, nome, descrizione, prezzo, idprodotto) VALUES ('mus00', 'iPod','musica','199','02');
  
-- Table: ordine

-- DROP TABLE ordine;

CREATE TABLE ordine
(
  stato character varying,
  idordine bigint NOT NULL,
  idcliente bigint,
  data date,
  CONSTRAINT idordine PRIMARY KEY (idordine )
  CONSTRAINT cliente FOREIGN KEY (idcliente)
      REFERENCES utente (idutente) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ordine
  OWNER TO postgres;


-- Table: rigaordine

-- DROP TABLE rigaordine;

CREATE TABLE rigaordine
(
  quantita integer,
  idordine bigint NOT NULL,
  codprodotto character varying NOT NULL,
  prezzo numeric,
  CONSTRAINT chiave PRIMARY KEY (idordine , codprodotto ),
  CONSTRAINT ordine FOREIGN KEY (idordine)
      REFERENCES ordine (idordine) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT prodotto FOREIGN KEY (codprodotto)
      REFERENCES prodotto (codiceprodotto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE rigaordine
  OWNER TO postgres;

