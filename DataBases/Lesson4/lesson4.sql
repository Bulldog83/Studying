use geodata;

CREATE OR REPLACE VIEW `Cities` AS
SELECT ct.id as `ID`, ct.title as `Title`, r.title as `Region`, cn.title as `Country`
FROM _cities as ct
LEFT JOIN _regions AS r ON ct.region_id = r.id
LEFT JOIN _countries AS cn ON ct.country_id = cn.id;

use employees;

DROP FUNCTION IF EXISTS `getTabNum`;
CREATE FUNCTION getTabNum(firstName VARCHAR(14), lastName VARCHAR(16))
RETURNS INT
RETURN (SELECT IF(emp_no IS NOT NULL, emp_no, 0) FROM employees AS emp
		WHERE emp.first_name = firstName AND emp.last_name = lastName
        LIMIT 1);
        
SELECT getTabNum('Parto', 'Bamford') AS Tabnum;

DROP TRIGGER IF EXISTS `payBonus`;
CREATE TRIGGER `payBonus` AFTER INSERT ON employees
FOR EACH ROW
	INSERT INTO salaries
	SET emp_no = NEW.emp_no,
		salary = 5000,
		from_date = curdate(),
		to_date = curdate();