-- ============================================================
-- Base de datos BANCOS (MySQL 8.0.13 o superior)
--
-- Ejecutar como administrador (root). Ver README, apartado "Puesta en marcha".
-- ATENCIÓN: si la base de datos BANCOS ya existe, SE BORRA y se recrea con los
-- datos de ejemplo. Sirve también para dejar los datos como al principio.
-- ============================================================

DROP DATABASE IF EXISTS BANCOS;
CREATE DATABASE BANCOS;
USE BANCOS;

-- Usuario de la aplicación (el mismo que usa DBController).
-- Se recrea para partir de permisos limpios y solo tiene acceso a la base de datos BANCOS.
DROP USER IF EXISTS 'bancos'@'localhost';
CREATE USER 'bancos'@'localhost' IDENTIFIED BY 'bancos';
GRANT ALL PRIVILEGES ON BANCOS.* TO 'bancos'@'localhost';

-- Creación de tablas

CREATE TABLE FPAGO (
  cCodFPago  VARCHAR(2) NOT NULL,
  cDesFPago  VARCHAR(100) NOT NULL,
  nRecibos   INT NOT NULL DEFAULT 1,
  CONSTRAINT PK_FPAGO PRIMARY KEY (cCodFPago)
);

CREATE TABLE PRESTAMOS (
  nNumPrest  INT NOT NULL,
  dFecPrest  DATE DEFAULT (CURRENT_DATE),
  nImpPrest  INT NOT NULL DEFAULT 0,
  nImpReci   INT NOT NULL DEFAULT 0,
  cCodFPago  VARCHAR(2) NOT NULL,
  CONSTRAINT PK_PRESTAMOS PRIMARY KEY (nNumPrest),
  CONSTRAINT FK_PRESTAMO_FPAGO FOREIGN KEY (cCodFPago) REFERENCES FPAGO (cCodFPago)
);

-- ON DELETE CASCADE: al borrar un préstamo se borran automáticamente todos sus recibos.
CREATE TABLE RECIBOS (
  nNumPrest  INT NOT NULL,
  nNumRecib  INT NOT NULL,
  dFecRec    DATE NOT NULL DEFAULT (CURRENT_DATE),
  nImpRec    INT NOT NULL DEFAULT 0,
  dFecPago   DATE DEFAULT NULL,
  CONSTRAINT PK_RECIBOS PRIMARY KEY (nNumPrest, nNumRecib),
  CONSTRAINT FK_RECIBO_PRESTAMO FOREIGN KEY (nNumPrest) REFERENCES PRESTAMOS (nNumPrest) ON DELETE CASCADE
);

-- Inserciones de datos de prueba

INSERT INTO FPAGO (cCodFPago, cDesFPago, nRecibos) VALUES ('EF', 'Efectivo', 1);
INSERT INTO FPAGO (cCodFPago, cDesFPago, nRecibos) VALUES ('TR', 'Transferencia Bancaria', 12);
INSERT INTO FPAGO (cCodFPago, cDesFPago, nRecibos) VALUES ('TC', 'Tarjeta de Crédito', 6);

INSERT INTO PRESTAMOS (nNumPrest, dFecPrest, nImpPrest, nImpReci, cCodFPago)
VALUES (101, '2026-01-15', 3000, 1000, 'TR');
INSERT INTO PRESTAMOS (nNumPrest, dFecPrest, nImpPrest, nImpReci, cCodFPago)
VALUES (102, '2026-02-01', 1000, 500, 'TC');
INSERT INTO PRESTAMOS (nNumPrest, dFecPrest, nImpPrest, nImpReci, cCodFPago)
VALUES (103, '2026-03-10', 600, 600, 'EF');

INSERT INTO RECIBOS (nNumPrest, nNumRecib, dFecRec, nImpRec, dFecPago)
VALUES (101, 1, '2026-02-15', 1000, '2026-02-16'); -- Pagado
INSERT INTO RECIBOS (nNumPrest, nNumRecib, dFecRec, nImpRec, dFecPago)
VALUES (101, 2, '2026-03-15', 1000, NULL); -- Pendiente
INSERT INTO RECIBOS (nNumPrest, nNumRecib, dFecRec, nImpRec, dFecPago)
VALUES (101, 3, '2026-04-15', 1000, NULL); -- Pendiente
INSERT INTO RECIBOS (nNumPrest, nNumRecib, dFecRec, nImpRec, dFecPago)
VALUES (102, 1, '2026-03-01', 500, '2026-03-02'); -- Pagado
INSERT INTO RECIBOS (nNumPrest, nNumRecib, dFecRec, nImpRec, dFecPago)
VALUES (102, 2, '2026-04-01', 500, NULL); -- Pendiente
INSERT INTO RECIBOS (nNumPrest, nNumRecib, dFecRec, nImpRec, dFecPago)
VALUES (103, 1, '2026-03-10', 600, NULL); -- Pendiente

-- Procedimiento almacenado: sp_GENERARRECIBOS

DELIMITER //

CREATE PROCEDURE sp_GENERARRECIBOS (IN xnNumPrest INT, OUT xnRec INT)
BEGIN
  DECLARE xNR INT DEFAULT 0;
  DECLARE xdFecPrest DATE;
  DECLARE xnImpPrest INT;
  DECLARE xcCodFPago VARCHAR(2);
  DECLARE xnRecibos INT;
  DECLARE xnImpRec INT;
  DECLARE i INT DEFAULT 1;

  SET xnRec = -1;
  SELECT COUNT(*) INTO xNR FROM PRESTAMOS WHERE nNumPrest = xnNumPrest;

  IF xNR > 0 THEN
      SELECT dFecPrest, nImpPrest, t1.cCodFPago, nRecibos
      INTO xdFecPrest, xnImpPrest, xcCodFPago, xnRecibos
      FROM PRESTAMOS t1
      INNER JOIN FPAGO t2 ON t1.cCodFPago = t2.cCodFPago
      WHERE nNumPrest = xnNumPrest;

      SET xnImpRec = xnImpPrest / xnRecibos;

      WHILE i <= xnRecibos DO
          INSERT INTO RECIBOS (nNumPrest, nNumRecib, dFecRec, nImpRec, dFecPago)
          VALUES (xnNumPrest, i, DATE_ADD(xdFecPrest, INTERVAL (i - 1) MONTH), xnImpRec, NULL);
          SET i = i + 1;
      END WHILE;

      SET xnRec = xnRecibos;
  END IF;
END //

-- Función: fn_calcular_deuda_prestamo

CREATE FUNCTION fn_calcular_deuda_prestamo (p_num_prest INT)
RETURNS DECIMAL(12, 2)
DETERMINISTIC
BEGIN
    DECLARE v_total_deuda DECIMAL(12, 2) DEFAULT 0;

    SELECT COALESCE(SUM(nImpRec), 0)
    INTO v_total_deuda
    FROM RECIBOS
    WHERE nNumPrest = p_num_prest
      AND dFecPago IS NULL;

    RETURN v_total_deuda;
END //

-- Procedimiento equivalente para obtener los recibos pendientes

CREATE PROCEDURE sp_obtener_recibos_pendientes (IN p_num_prest INT)
BEGIN
    SELECT nNumRecib, dFecRec, nImpRec
    FROM RECIBOS
    WHERE nNumPrest = p_num_prest
      AND dFecPago IS NULL
    ORDER BY dFecRec ASC;
END //

DELIMITER ;
