ALTER TABLE Personne ADD COLUMN departement_id BIGINT ;

ALTER TABLE Personne
ADD CONSTRAINT  fk_departement_id
FOREIGN KEY (departement_id) REFERENCES Departement(id) ;
