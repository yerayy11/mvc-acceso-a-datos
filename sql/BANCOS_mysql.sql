-- Base de datos BANCOS (convertida de Oracle a MySQL). Requiere MySQL 8.0.13 o superior.
-- Antes de ejecutar este script hay que crear la base de datos y el usuario (ver README).

USE BANCOS;

CREATE TABLE FPAGO (
    cCodFPago VARCHAR(2) PRIMARY KEY,
    cDesFPago VARCHAR(100) NOT NULL,
    nRecibos INT NOT NULL DEFAULT 1
);

CREATE TABLE PRESTAMOS (
    nNumPrest INT PRIMARY KEY,
    dFecPrest DATE DEFAULT (CURRENT_DATE),
    nImpPrest DECIMAL(10,2) NOT NULL DEFAULT 0,
    nImpReci DECIMAL(10,2) NOT NULL DEFAULT 0,
    cCodFPago VARCHAR(2) NOT NULL,

    CONSTRAINT FK_PRESTAMO_FPAGO
        FOREIGN KEY (cCodFPago)
        REFERENCES FPAGO(cCodFPago)
);

CREATE TABLE RECIBOS (
    nNumPrest INT NOT NULL,
    nNumRecib INT NOT NULL,
    dFecRec DATE NOT NULL DEFAULT (CURRENT_DATE),
    nImpRec DECIMAL(10,2) NOT NULL DEFAULT 0,
    dFecPago DATE NULL,

    PRIMARY KEY (nNumPrest, nNumRecib),

    CONSTRAINT FK_RECIBO_PRESTAMO
        FOREIGN KEY (nNumPrest)
        REFERENCES PRESTAMOS(nNumPrest)
);

INSERT INTO FPAGO VALUES ('EF', 'Efectivo', 1);
INSERT INTO FPAGO VALUES ('TR', 'Transferencia Bancaria', 12);
INSERT INTO FPAGO VALUES ('TC', 'Tarjeta de Crédito', 6);

INSERT INTO PRESTAMOS
VALUES (101, '2026-01-15', 12000, 1000, 'TR');

INSERT INTO PRESTAMOS
VALUES (102, '2026-02-01', 3000, 500, 'TC');

INSERT INTO PRESTAMOS
VALUES (103, '2026-03-10', 600, 600, 'EF');

INSERT INTO RECIBOS
VALUES (101,1,'2026-02-15',1000,'2026-02-16');

INSERT INTO RECIBOS
VALUES (101,2,'2026-03-15',1000,NULL);

INSERT INTO RECIBOS
VALUES (101,3,'2026-04-15',1000,NULL);

INSERT INTO RECIBOS
VALUES (102,1,'2026-03-01',500,'2026-03-02');

INSERT INTO RECIBOS
VALUES (102,2,'2026-04-01',500,NULL);

INSERT INTO RECIBOS
VALUES (103,1,'2026-03-10',600,NULL);

DELIMITER $$

CREATE PROCEDURE sp_GENERARRECIBOS(
    IN xnNumPrest INT,
    OUT xnRec INT
)
BEGIN
    DECLARE xNR INT DEFAULT 0;
    DECLARE xdFecPrest DATE;
    DECLARE xnImpPrest DECIMAL(10,2);
    DECLARE xcCodFPago VARCHAR(2);
    DECLARE xnRecibos INT;
    DECLARE xnImpRec DECIMAL(10,2);
    DECLARE i INT DEFAULT 1;
    SET xnRec = -1;
    SELECT COUNT(*)
    INTO xNR
    FROM PRESTAMOS
    WHERE nNumPrest = xnNumPrest;
    IF xNR > 0 THEN
        SELECT p.dFecPrest,
               p.nImpPrest,
               p.cCodFPago,
               f.nRecibos
        INTO xdFecPrest,
             xnImpPrest,
             xcCodFPago,
             xnRecibos
        FROM PRESTAMOS p
        INNER JOIN FPAGO f
            ON p.cCodFPago = f.cCodFPago
        WHERE p.nNumPrest = xnNumPrest;
        SET xnImpRec = xnImpPrest / xnRecibos;
        WHILE i <= xnRecibos DO
            INSERT INTO RECIBOS(
                nNumPrest,
                nNumRecib,
                dFecRec,
                nImpRec,
                dFecPago
            )
            VALUES(
                xnNumPrest,
                i,
                DATE_ADD(xdFecPrest, INTERVAL (i-1) MONTH),
                xnImpRec,
                NULL
            );
            SET i = i + 1;
        END WHILE;
        SET xnRec = xnRecibos;
    END IF;
-- Corregido al subirlo al repo: faltaba cerrar el procedimiento (END$$) y restaurar el delimitador.
END$$

DELIMITER ;

DELIMITER $$

CREATE FUNCTION fn_calcular_deuda_prestamo(
    p_num_prest INT
)
RETURNS DECIMAL(12,2)
DETERMINISTIC
BEGIN
    DECLARE v_total_deuda DECIMAL(12,2);
    SELECT IFNULL(SUM(nImpRec),0)
    INTO v_total_deuda
    FROM RECIBOS
    WHERE nNumPrest = p_num_prest
      AND dFecPago IS NULL;
    RETURN v_total_deuda;
END$$
DELIMITER ;
DELIMITER $$

CREATE PROCEDURE sp_obtener_recibos_pendientes(
    IN p_num_prest INT
)
BEGIN

    SELECT nNumRecib,
           dFecRec,
           nImpRec
    FROM RECIBOS
    WHERE nNumPrest = p_num_prest
      AND dFecPago IS NULL
    ORDER BY dFecRec ASC;

END$$

DELIMITER ;
