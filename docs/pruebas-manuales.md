# Pruebas manuales

No hay tests automatizados, así que esta lista es la forma de comprobar que todo funciona. Se usa antes de entregar y al revisar PRs que cambian comportamiento.

**Preparación:** ejecutar `sql/bancos-mysql.sql` como root (deja la base de datos con los datos de ejemplo) y arrancar la aplicación con Run Project (F6). Las pruebas del bloque C se hacen en orden, porque unas dependen de los datos que dejan las anteriores; para repetirlas, reiniciar los datos con el mismo script.

Marca cada prueba como ✅ (resultado esperado) o ❌ (y abre un Issue con los pasos).

## A. Arranque y cierre

| # | Prueba | Resultado esperado |
|---|---|---|
| 1 | Con MySQL parado, arrancar la aplicación | Aparece "Error en la conexión" y, al aceptar, la aplicación termina con `System.exit(1)` (no queda ninguna ventana ni proceso Java) |
| 2 | Con MySQL en marcha, arrancar | Ventana "App Bancos" con "Conexión establecida correctamente" |
| 3 | Cambiar el rol en el desplegable | La lista de miembros se actualiza |
| 4 | Cerrar con el botón de la puerta y, en otra ejecución, con la X | La aplicación termina en ambos casos (el botón cierra antes la conexión y llama a `System.exit(0)`) |

## B. Formas de pago

| # | Prueba | Resultado esperado |
|---|---|---|
| 5 | Abrir "Formas de Pago" | 3 filas: EF Efectivo 1, TR Transferencia Bancaria 12, TC Tarjeta de Crédito 6 |
| 6 | Alta: código `XX`, descripción `Prueba`, 3 recibos | "Insertado" y aparece en la tabla |
| 7 | Repetir el alta de `XX` | Solo "Error al insertar..."; no aparece "Insertado" |
| 8 | Alta con `abc` en número de recibos | "Error en el formato del número de recibos" |
| 9 | Seleccionar `XX`, cambiar la descripción y modificar | "Modificado" y la tabla muestra el cambio |
| 10 | Buscar `XX` y después `ZZ` | `XX` rellena los campos; `ZZ` da error de búsqueda |
| 11 | Baja de `TR` (tiene préstamos) | Solo "Error al borrar..."; no aparece "Borrado" y `TR` sigue en la tabla. Los préstamos **no** se borran en cascada: el `ON DELETE CASCADE` es solo entre préstamos y recibos |
| 12 | Baja de `XX` | "Borrado" y desaparece de la tabla |

## C. Préstamos y recibos

| # | Prueba | Resultado esperado |
|---|---|---|
| 13 | Abrir "Prestamos" | 101, 102 y 103 ordenados, con fechas 15/01/2026, 01/02/2026 y 10/03/2026, importes 3000, 1000 y 600, importes recibidos 1000, 500 y 600 y formas de pago TR, TC y EF |
| 14 | Seleccionar el 101 | Los campos se rellenan y la tabla de recibos muestra 3 filas: el 1 (15/02/2026, 1000) con fecha de pago 16/02/2026 y los 2 y 3 sin fecha de pago. Esta tabla se rellena recorriendo `getListaRecibos()` del préstamo cargado con `findAllEager` |
| 15 | Alta: número `104`, importe `1200`, forma `TC` (la fecha es la de hoy, no se escribe) | "Registro añadido". Al seleccionar el 104: 6 recibos de 200, con fechas mensuales desde hoy, sin fecha de pago, e importe recibido 0 |
| 16 | Alta con un número que ya existe (`101`) | "Registro no se ha podido añadir..." |
| 17 | Alta con letras o con un número negativo en el número de préstamo o en el importe | Mensaje de "solo acepta números" o "tiene que ser mayor o igual que 0" |
| 18 | Seleccionar el recibo 3 del 101 (el 2 sigue sin pagar) y pulsar "Pagar Recibo" | "Primero hay que pagar los recibos anteriores de este préstamo". No cambia nada |
| 19 | Pagar el recibo 2 del 101 y después el 3 | "Recibo pagado" las dos veces. Su fecha de pago es hoy y el importe recibido del 101 pasa a 2000 y después a 3000 |
| 20 | Pagar el recibo 1 del 104 | "Recibo pagado". El importe recibido del 104 pasa a 200. Los recibos 1 de 102 y 103 **no cambian** (el 101/1 sigue con 16/02/2026) |
| 21 | Pagar otra vez el mismo recibo | "No se ha podido pagar el recibo" |
| 22 | Pulsar "Pagar Recibo" sin seleccionar ningún recibo | "Selecciona un recibo" |
| 23 | Modificar el 104 (tiene un recibo pagado) | "No se ha podido modificar..." |
| 24 | Baja del 104 | "No se ha podido eliminar..." |
| 25 | Alta: `105`, importe `500`, forma `EF` | 1 recibo de 500 |
| 26 | Modificar el 105: importe `600` y forma `TR` | "Registro modificado". La fecha del préstamo no cambia y los recibos se regeneran: 12 recibos de 50 |
| 27 | Modificar el 105 con letras en el importe | "El Importe del préstamo solo acepta números." y no cambia nada |
| 28 | Baja del 105 | "Prestamo borrado"; desaparecen el préstamo y sus recibos (los borra la base de datos con `ON DELETE CASCADE`) |
| 29 | Buscar el 101 y después el 999 | El 101 queda seleccionado con sus recibos; el 999 da "Codigo no encontrado" |

## D. Comprobaciones en la base de datos

Ejecutar en MySQL tras las pruebas anteriores:

```sql
-- Debe aparecer ON DELETE CASCADE en FK_RECIBO_PRESTAMO
SHOW CREATE TABLE RECIBOS;

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
- Los préstamos de los datos de ejemplo (101, 102 y 103) tienen importe recibido mayor que 0, así que no se pueden modificar ni borrar; solo los préstamos nuevos sin pagos.
