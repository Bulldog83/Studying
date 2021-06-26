USE employees;

DROP PROCEDURE IF EXISTS `fireEmployee`;

DELIMITER $$
CREATE PROCEDURE fireEmployee(tabNum INT)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
		DECLARE errno INT;
		GET CURRENT DIAGNOSTICS CONDITION 1 errno = MYSQL_ERRNO;
		SELECT errno AS `MYSQL_ERROR`;
        ROLLBACK;
	END;
    DECLARE td DATE;
    DECLARE cd DATE;
    SET @td = '9999-01-01';
    SET @cd = curdate();
	START TRANSACTION;
		UPDATE dept_emp SET to_date = @cd WHERE emp_no = tabNum AND to_date = @td;
		UPDATE dept_manager SET to_date = @cd WHERE emp_no = tabNum AND to_date = @td;
		UPDATE titles SET to_date = @cd WHERE emp_no = tabNum AND to_date = @td;
		UPDATE salaries SET to_date = @cd WHERE emp_no = tabNum AND to_date = @td;
	COMMIT;
END$$
DELIMITER ;