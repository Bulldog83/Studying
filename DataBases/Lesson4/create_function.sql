## пРОСТОЙ скрипт создания функции - DEFINER(владельца) СУБД сама создаст
## чтобы пересоздать/обновить с таким же именем функцию в режиме скрипта надо сделать DROP этой функции
## Но в воркбенче как и с таблицами можно работать в режиме ALTER - попробуйте
CREATE FUNCTION `myCountDept`(dept char(4)) RETURNS int
RETURN (SELECT 
				COUNT(*) 
			FROM employees.dept_emp
			WHERE to_date = '9999-01-01' and dept_no=dept);

## создание функции чуток посложнее            
delimiter $$
create function emp_name (employee_id int)
returns varchar(32)
reads SQL data ## для команды show creata function
begin
        declare full_name char(50);
       return  (select 
            concat(first_name, ' ', last_name) as name  ##into @full_name
        from 
            employees
        where 
            emp_no = employee_id);

end $$         
select emp_name(10003);
show create function emp_name;