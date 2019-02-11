/*
  Cria função que implementa o método de Haversine para calculo de distancia entre pontos geográficos.
*/
CREATE OR REPLACE FUNCTION haversine(latitude1 double precision, longitude1 double precision,
                                     latitude2 double precision, longitude2 double precision)
  RETURNS double precision AS
$BODY$
declare
  dLat    double precision;
  dLng    double precision;
  lat1Rad double precision;
  lat2Rad double precision;
  a       double precision;
  c       double precision;
begin
  dLat = radians((latitude2 - latitude1));
  dLng = radians((longitude2 - longitude1));
  -- --
  lat1Rad = radians(latitude1);
  lat2Rad = radians(latitude2);
  -- --
  a = power(sin(dLat / 2), 2) + cos(lat1Rad) * cos(lat2Rad) * power(sin(dLng / 2), 2);
  c = 2 * atan2(sqrt(a), sqrt(1 - a));
  -- --
  return 6378.137 * c;
end
$BODY$
  language 'plpgsql';
