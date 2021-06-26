use employees;

#1
SELECT dep.dept_name AS `Department`, round(AVG(sal.salary), 2) AS `Salary` FROM departments AS dep
INNER JOIN dept_emp AS de ON dep.dept_no = de.dept_no
INNER JOIN employees AS emp ON emp.emp_no = de.emp_no
INNER JOIN salaries AS sal ON sal.emp_no = emp.emp_no
WHERE de.to_date = '9999-01-01'
GROUP BY `Department`;

#2
SELECT concat(emp.first_name, ' ', emp.last_name) AS `Employee`, max(sal.salary) as `Max Salary` FROM employees as emp
INNER JOIN dept_emp AS de ON emp.emp_no = de.emp_no
INNER JOIN salaries AS sal ON emp.emp_no = sal.emp_no
WHERE de.to_date = '9999-01-01'
GROUP BY `Employee`;

#3
DELETE FROM employees AS emp WHERE emp.emp_no = (
	SELECT emp_no FROM salaries AS sal WHERE salary = (
		SELECT max(sal.salary) FROM salaries AS sal
        INNER JOIN dept_emp AS de ON sal.emp_no = de.emp_no
        WHERE de.to_date = '9999-01-01'
	)
) LIMIT 1;

#4
SELECT dep.dept_name AS `Department`, count(*) FROM employees AS emp
INNER JOIN dept_emp AS de ON emp.emp_no = de.emp_no
INNER JOIN departments AS dep ON de.dept_no = dep.dept_no
WHERE de.to_date = '9999-01-01'
GROUP BY `Department`;

SELECT count(*) FROM employees AS emp
INNER JOIN dept_emp AS de ON emp.emp_no = de.emp_no
WHERE de.to_date = '9999-01-01';

#5
SELECT dep.dept_name AS `Department`, count(*) AS `Employees`, sum(sal.salary) AS `Salary Total` FROM departments AS dep
INNER JOIN dept_emp AS de ON dep.dept_no = de.dept_no
INNER JOIN employees AS emp ON emp.emp_no = de.emp_no
INNER JOIN salaries AS sal ON sal.emp_no = emp.emp_no
WHERE de.to_date = '9999-01-01' AND sal.to_date = '9999-01-01'
GROUP BY `Department`;