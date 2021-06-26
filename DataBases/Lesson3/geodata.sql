use geodata;

SELECT ct.id as `ID`, ct.title as `Title`, r.title as `Region`, cn.title as `Country`
FROM _cities as ct
LEFT JOIN _regions AS r ON ct.region_id = r.id
LEFT JOIN _countries AS cn ON ct.country_id = cn.id;

SELECT ct.id as `ID`, ct.title as `Title`, r.title as `Region` FROM _cities as ct
INNER JOIN _regions as r ON ct.region_id = r.id
WHERE r.title LIKE 'Московская область';