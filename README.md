# MVC - Acceso a Datos

Trabajo en grupo de Acceso a Datos (2º DAM): aplicación de escritorio en Java (Swing) con patrón MVC y JDBC sobre MySQL. Trabaja con la base de datos `BANCOS`: formas de pago, préstamos y recibos.

## Funcionalidad

- **Formas de pago:** alta, baja, modificación y búsqueda.
- **Préstamos:** alta (genera los recibos con el procedimiento `sp_GENERARRECIBOS`), baja y modificación (solo si todavía no se ha pagado ningún recibo; al modificar se regeneran los recibos) y búsqueda.
- **Recibos:** consulta de los recibos de cada préstamo y pago de recibos (al pagar se actualiza el importe recibido del préstamo).

## Requisitos

- **JDK 26** (el `pom.xml` compila con la versión 26).
- **NetBeans** (proyecto Maven).
- **MySQL 8.0.13 o superior**, en `localhost:3306`. El script de la base de datos usa `DEFAULT (CURRENT_DATE)`, que no existe en versiones anteriores.
- Un cliente SQL que entienda `DELIMITER`: **MySQL Workbench** o la consola `mysql`.

## Puesta en marcha

1. Clonar el repositorio:
   ```
   git clone https://github.com/yerayy11/mvc-acceso-a-datos.git
   ```
2. Crear la base de datos. El script [`sql/bancos-mysql.sql`](sql/bancos-mysql.sql) lo hace todo: crea la base de datos `BANCOS`, el usuario `bancos` (contraseña `bancos`, con permisos solo sobre `BANCOS`), las tablas, los datos de ejemplo y el procedimiento y las funciones almacenadas. Ejecútalo **como administrador (root)**:
   - Con la consola, desde la carpeta del repositorio:
     ```
     mysql -u root -p < sql/bancos-mysql.sql
     ```
   - Con MySQL Workbench: File → Open SQL Script, elige el archivo y ejecuta todo el script (icono del rayo).

   > **El script borra la base de datos `BANCOS` si ya existe** y la recrea con los datos de ejemplo. Sirve también para dejar los datos como al principio después de hacer pruebas.
3. Abrir la carpeta del repositorio en NetBeans (File → Open Project).
4. Clean and Build para comprobar que compila.
5. Ejecutar: Run Project (F6), o clic derecho sobre `tema02_ejercicio1.Launcher` → Run File.

Los datos de conexión están en `DBController`: `localhost:3306`, base de datos `BANCOS`, usuario `bancos`, contraseña `bancos`.

### Si algo falla

| Síntoma | Qué revisar |
|---|---|
| "Error en la conexión" al arrancar | Que el servicio MySQL esté en marcha, que escuche en el puerto 3306 y que hayas ejecutado el script. Prueba `mysql -u bancos -pbancos BANCOS`. |
| `Access denied for user` | Los nombres de usuario de MySQL distinguen mayúsculas: debe ser `bancos` en minúsculas. Vuelve a ejecutar el script como root. |
| Error al compilar | Que NetBeans use el JDK 26 (Tools → Java Platforms y propiedades del proyecto). |
| `PROCEDURE ... does not exist` | El script no se ejecutó entero. Usa Workbench o la consola `mysql` (otros clientes no entienden `DELIMITER`). |
| Instalaste una versión anterior del script | Vuelve a ejecutar el script actual: recrea el usuario y los permisos. |

## Estructura del proyecto

```
src/main/java/tema02_ejercicio1/
├── Controllers/   acceso a datos: DBController (conexión) y un controlador por entidad
├── Models/        clases de modelo
├── Views/         ventanas Swing (Index es la ventana principal)
└── Launcher.java  punto de entrada
src/main/resources/iconos/   iconos de las ventanas
sql/                         script de la base de datos
docs/                        documentación (pruebas manuales)
```

## Pruebas

No hay tests automatizados. Antes de entregar y al revisar cambios importantes se sigue la lista de [pruebas manuales](docs/pruebas-manuales.md).

## Cómo trabajamos con Git

Objetivo: que `main` esté siempre en un estado que compila y funciona, y que nadie pise el trabajo de otro.

### Reglas

1. **Nadie sube directamente a `main`.** Todo entra mediante Pull Request.
2. **Una rama por tarea, de vida corta.** Se crea al empezar la tarea y se borra al mergearla. No hay ramas personales permanentes.
3. **Nombres de rama:** en minúsculas y con guiones.
   - `feature/nombre-de-la-tarea` para funcionalidad nueva.
   - `fix/nombre-del-problema` para corregir errores.
   - `docs/nombre-del-cambio` para documentación y scripts.
4. **Commits pequeños y claros**, en español y en imperativo: "Añadir método findAll en el DAO", no "cambios". Los mensajes son públicos y permanentes: tono profesional.
5. **Todo Pull Request lo revisa al menos otra persona** (nunca quien lo ha escrito) antes de mergear. **Revisar es descargar la rama, ejecutar la aplicación y repetir las pruebas que describe el PR**; no basta con leer el código.
6. **Un Pull Request por tarea, directo a `main`.** No se apilan PRs sobre ramas de otros: se revisan peor y el historial pierde quién hizo qué.
7. **Merge con "Squash and merge"**, para que `main` quede con un commit por tarea.
8. **Antes de abrir el Pull Request, comprobar que compila** (Clean and Build en NetBeans).
9. **No subir** contraseñas personales, carpetas `target/` ni `nbproject/private/`.
10. **Archivos delicados:** `pom.xml`, `DBController` y el script SQL. Avisar al grupo antes de modificarlos.
11. **Ventanas del diseñador de NetBeans** (`.java` + `.form`): una sola persona por ventana a la vez; los conflictos en `.form` son muy difíciles de resolver.
12. **Cada bug o tarea tiene su Issue.** El Pull Request lo enlaza escribiendo `Closes #N` en la descripción, y el Issue se cierra solo al mergear. No se cierra un Issue a mano sin comprobar que está resuelto.

### Flujo paso a paso

```
# 1. Partir siempre de main actualizado
git switch main
git pull

# 2. Crear la rama de la tarea
git switch -c feature/nombre-de-la-tarea

# 3. Trabajar y guardar cambios
git add .
git commit -m "Mensaje claro en imperativo"

# 4. Antes de subir, traer lo último de main a tu rama
git pull origin main

# 5. Subir la rama
git push -u origin feature/nombre-de-la-tarea
```

Después, en GitHub: **Compare & pull request** → rellenar la plantilla → pedir revisión a un compañero → mergear con *Squash and merge* → *Delete branch*.

Si aparece un conflicto en el paso 4, no lo resuelvas a ciegas: avisa a quien haya tocado ese archivo.

### Revisar el Pull Request de un compañero

```
git fetch
git switch nombre-de-la-rama-del-compañero
```

Abre el proyecto en NetBeans, Clean and Build, ejecuta las pruebas del apartado "Pruebas realizadas" del PR y después aprueba o comenta. Para volver a lo tuyo: `git switch main`.
