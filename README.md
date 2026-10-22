# MVC - Acceso a Datos

Trabajo en grupo de Acceso a Datos (2º DAM): aplicación de escritorio en Java (Swing) con patrón MVC y JDBC sobre MySQL. Trabaja con la base de datos `BANCOS` (formas de pago, préstamos y recibos).

## Requisitos

- **JDK 26** (el `pom.xml` compila con la versión 26).
- **NetBeans** (proyecto Maven).
- **MySQL 8.0.13 o superior**, en `localhost:3306`. El script de la base de datos usa `DEFAULT (CURRENT_DATE)`, que no existe en versiones anteriores.

## Puesta en marcha

1. Clonar el repositorio:
   ```
   git clone https://github.com/yerayy11/mvc-acceso-a-datos.git
   ```
2. Crear la base de datos en local. `DBController` se conecta a `jdbc:mysql://localhost:3306/BANCOS` con usuario `BANCOS` y contraseña `BANCOS`, así que cada uno monta lo mismo en su equipo:
   1. Abre una consola de MySQL como administrador (MySQL Workbench o `mysql -u root -p`) y ejecuta:
      ```sql
      CREATE DATABASE IF NOT EXISTS BANCOS;
      CREATE USER IF NOT EXISTS 'BANCOS'@'localhost' IDENTIFIED BY 'BANCOS';
      GRANT ALL PRIVILEGES ON BANCOS.* TO 'BANCOS'@'localhost';
      ```
   2. Ejecuta el script [`sql/BANCOS_mysql.sql`](sql/BANCOS_mysql.sql): crea las tablas `FPAGO`, `PRESTAMOS` y `RECIBOS`, inserta datos de ejemplo y crea `sp_GENERARRECIBOS`, `fn_calcular_deuda_prestamo` y `sp_obtener_recibos_pendientes`. Desde la consola: `mysql -u root -p BANCOS < sql/BANCOS_mysql.sql`. El script usa `DELIMITER`, que algunos clientes SQL no entienden; MySQL Workbench y la consola `mysql` sí.
   3. El script no se puede ejecutar dos veces seguidas (los `INSERT` fallarían por claves duplicadas). Para empezar de cero, borra la base de datos con `DROP DATABASE BANCOS;` (elimina todos sus datos locales) y repite los pasos 1 y 2.
3. Abrir la carpeta del repositorio en NetBeans (File → Open Project).
4. Clean and Build para comprobar que compila.
5. Ejecutar la clase `tema02_ejercicio1.Launcher` (clic derecho → Run File).

## Estructura del proyecto

```
src/main/java/tema02_ejercicio1/
├── Controllers/   acceso a datos: DBController (conexión) y un controlador por entidad
├── Models/        clases de modelo
├── Views/         ventanas Swing (Index es la ventana principal)
└── Launcher.java  punto de entrada
src/main/resources/iconos/   iconos de las ventanas
sql/                         script de la base de datos
```

## Cómo trabajamos con Git

Objetivo: que `main` esté siempre en un estado que compila y funciona, y que nadie pise el trabajo de otro.

### Reglas

1. **Nadie sube directamente a `main`.** Todo entra mediante Pull Request.
2. **Una rama por tarea, de vida corta.** Se crea al empezar la tarea y se borra al mergearla. No hay ramas personales permanentes.
3. **Nombres de rama:** en minúsculas y con guiones.
   - `feature/nombre-de-la-tarea` para funcionalidad nueva.
   - `fix/nombre-del-problema` para corregir errores.
   - `docs/nombre-del-cambio` para documentación y scripts.
4. **Commits pequeños y claros**, en español y en imperativo: "Añadir método findAll en el DAO", no "cambios".
5. **Todo Pull Request lo revisa al menos otra persona** (nunca quien lo ha escrito) antes de mergear.
6. **Merge con "Squash and merge"**, para que `main` quede con un commit por tarea.
7. **Antes de abrir el Pull Request, comprobar que compila** (Clean and Build en NetBeans).
8. **No subir** contraseñas personales, carpetas `target/` ni `nbproject/private/`.
9. **Archivos delicados:** `pom.xml` y la clase de conexión a la base de datos. Avisar al grupo antes de modificarlos.
10. **Ventanas del diseñador de NetBeans** (`.java` + `.form`): una sola persona por ventana a la vez; los conflictos en `.form` son muy difíciles de resolver.

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
