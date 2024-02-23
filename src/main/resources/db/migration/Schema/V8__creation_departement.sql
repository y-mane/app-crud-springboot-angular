CREATE TABLE Departement(
     id BIGINT PRIMARY KEY,
     code VARCHAR(255) NOT NULL,
     designation VARCHAR(255) NOT NULL
     );

CREATE SEQUENCE auto_incrementation_departement;

ALTER TABLE Departement
    ALTER COLUMN id SET DEFAULT nextval('auto_incrementation_departement');

ALTER TABLE Departement
    ALTER COLUMN id SET NOT NULL ;

