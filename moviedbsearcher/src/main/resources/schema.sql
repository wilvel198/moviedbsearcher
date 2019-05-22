
DROP TABLE IF EXISTS MovieData;

CREATE TABLE MovieData (
  id          INTEGER PRIMARY KEY,
  fullJson VARCHAR(250) ,
  movieID VARCHAR(220),
  popularity VARCHAR(220),
  release_date VARCHAR(220),
  spoken_languages VARCHAR(220),
  title VARCHAR(220),

);
