# Pruebas manuales

No hay tests automatizados, así que esta lista es la forma de comprobar que todo funciona. Se usa antes de entregar y al revisar PRs que cambian comportamiento.

**Preparación:** ejecutar `sql/bancos-mysql.sql` como root (deja la base de datos con los datos de ejemplo) y arrancar la aplicación con Run Project (F6). Cada bloque puede repetirse reiniciando los datos con el mismo script.

Marca cada prueba como ✅ (resultado esperado) o ❌ (y abre un Issue con los pasos).

## A. Arranque y cierre

| # | Prueba | Resultado esperado |
|---|---|---|
| 1 | Con MySQL parado, arrancar la aplicación | Aparece "Error en la conexión" y, al aceptar, la aplicación termina (no queda ninguna ventana ni proceso Java) |
| 2 | Con MySQL en marcha, arrancar | Ventana "App Bancos" con "Conexión establecida correctamente" |
| 3 | Cambiar el rol en el desplegable | La lista de miembros se actualiza |
| 4 | Cerrar con el botón de la puerta y con la X | La aplicación termina en ambos casos |

## B. Formas de pago

| # | Prueba | Resultado esperado |
|---|---|---|
| 5 | Abrir "Formas de Pago" | 3 filas: EF Efectivo 1, TR Transferencia Bancaria 12, TC Tarjeta de Crédito 6 |
| 6 | Alta: código `XX`, descripción `Prueba`, 3 recibos | "Insertado" y aparece en la tabla |
| 7 | Repetir el alta de `XX` | Solo "Error al insertar..."; no aparece "Insertado" |
| 8 | Alta con `abc` en número de recibos | "Error en el formato del número de recibos" |
| 9 | Seleccionar `XX`, cambiar la descripción y modificar | "Modificado" y la tabla muestra el cambio |
| 10 | Buscar `XX` y después `ZZ` | `XX` rellena los campos; `ZZ` da error de búsqueda |
| 11 | Baja de `TR` (tiene préstamos) | Solo "Error al borrar..."; no aparece "Borrado" y `TR` sigue en la tabla |
| 12 | Baja de `XX` | "Borrado" y desaparece de la tabla |

## C. Préstamos y recibos

| # | Prueba | Resultado esperado |
|---|---|---|
| 13 | Abrir "Prestamos" | 101, 102 y 103 con importes recibidos 1000, 500 y 600 y formas de pago TR, TC y EF |
| 14 | Seleccionar el 101 | Los campos se rellenan y la tabla de recibos muestra 3 filas: el 1 con fecha de pago 2026-02-16 y los 2 y 3 sin fecha de pago |
| 15 | Pulsar el botón de refrescar, con y sin préstamo seleccionado | Sin errores en la consola de NetBeans |
| 16 | Alta: número `104`, fecha `2026-10-22`, importe `1200`, forma `TC` | "Registro añadido". Al seleccionar el 104: 6 recibos de 200, con fechas mensuales desde 2026-10-22, sin fecha de pago, e importe recibido 0 |
| 17 | Alta con un número que ya existe (`101`) | "Registro no se ha podido añadir..." |
| 18 | Seleccionar el recibo 1 del 104 y pulsar "Pagar Recibo" | "Recibo pagado". Su fecha de pago es hoy y el importe recibido del 104 pasa a 200. Los recibos 1 de 101, 102 y 103 **no cambian** (el 101/1 sigue con 2026-02-16) |
| 19 | Pagar otra vez el mismo recibo | "No se ha podido pagar el recibo" |
| 20 | Pulsar "Pagar Recibo" sin seleccionar ningún recibo | "Selecciona un recibo" |
| 21 | Modificar el 104 (tiene un recibo pagado) | "No se ha podido modificar..." |
| 22 | Baja del 104 | "No se ha podido eliminar..." |
| 23 | Alta: `105`, `2026-10-22`, `500`, forma `EF` | 1 recibo de 500 |
| 24 | Modificar el 105: importe `600` y forma `TR` | "Registro modificado" y recibos regenerados: 12 recibos de 50 |
| 25 | Baja del 105 | "Prestamo borrado"; desaparecen el préstamo y sus recibos |
| 26 | Buscar el 101 y después el 999 | El 101 queda seleccionado con sus recibos; el 999 da "Codigo no encontrado" |

## D. Comprobaciones en la base de datos

Ejecutar en MySQL tras las pruebas anteriores:

```sql
-- No debe devolver filas (recibos sin préstamo)
SELECT * FROM RECIBOS WHERE nNumPrest NOT IN (SELECT nNumPrest FROM PRESTAMOS);

-- El importe recibido de cada préstamo debe coincidir con la suma de sus recibos pagados
-- (excepción conocida: el 103 viene con 600 recibidos en los datos de ejemplo aunque su recibo no está pagado)
SELECT p.nNumPrest, p.nImpReci,
       COALESCE(SUM(CASE WHEN r.dFecPago IS NOT NULL THEN r.nImpRec END), 0) AS suma_pagados
FROM PRESTAMOS p LEFT JOIN RECIBOS r ON r.nNumPrest = p.nNumPrest
GROUP BY p.nNumPrest, p.nImpReci;
```

## Problemas conocidos (no impiden el uso)

- Con `INT`, `sp_GENERARRECIBOS` redondea el importe de cada recibo: un préstamo de 1000 en 6 recibos genera 6 recibos de 167 (suma 1002).
- Alta o modificación de un préstamo con campos vacíos o con la fecha mal escrita: la excepción sale por consola y no se muestra mensaje al usuario.
- Si se borra el último préstamo de la tabla y después se pulsa refrescar, se produce una excepción en consola.
