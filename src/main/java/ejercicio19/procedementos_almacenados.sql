

--Procedemento almacenado para o apartado i)
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizaDept`(cod INT(2), localidad VARCHAR(13))
BEGIN
UPDATE Dept SET loc=localidad WHERE deptno = cod;
END$$

--Procedemento almacenado para o apartado j)
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `consultaDepar`(in num int(2), out name varchar(14), out local varchar(13))
BEGIN
SELECT dname, loc INTO name, local
FROM dept
WHERE dept.deptno=num;
END$$

