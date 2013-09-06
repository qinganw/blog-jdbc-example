CREATE TABLE `dept` (
	`deptno` INT(10) NOT NULL DEFAULT '0',
	`dname` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`deptno`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `emp` (
	`empno` INT(10) NOT NULL DEFAULT '0',
	`ename` VARCHAR(50) NULL DEFAULT NULL,
	`deptno` INT(10) NULL DEFAULT NULL,
	PRIMARY KEY (`empno`),
	INDEX `FK_emp_dept` (`deptno`),
	CONSTRAINT `FK_emp_dept` FOREIGN KEY (`deptno`) REFERENCES `dept` (`deptno`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

DELIMITER //
create PROCEDURE sp_add_emp2(
    in v_empno INT,
    in v_ename varchar(50),
    in v_deptno INT,
    in v_dname varchar(50),
        
    out num INT
)
BEGIN
declare num1 INT;
declare num2 INT;

SELECT COUNT(*) INTO num1 FROM dept WHERE deptno=v_deptno;
IF(num1=0) THEN
   INSERT INTO dept(deptno,dname) VALUES(v_deptno,v_dname);
END IF;
SELECT COUNT(*) INTO num2 FROM emp WHERE empno=v_empno;
IF(num2=0)THEN
    INSERT INTO emp(empno,ename,deptno) VALUES(v_empno,v_ename,v_deptno);
END IF;
set num=num1+num2;
END// 