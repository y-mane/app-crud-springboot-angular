CREATE SEQUENCE auto_incrementation;

ALTER TABLE Personne
ALTER COLUMN id SET DEFAULT nextval('auto_incrementation');

ALTER TABLE Personne
ALTER COLUMN id SET NOT NULL ;
