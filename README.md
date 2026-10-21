# MVC - Acceso a Datos

Trabajo en grupo de Acceso a Datos: aplicación Java con patrón MVC y JDBC (MySQL).

## Puesta en marcha

> Esta sección se completa cuando se suba el proyecto base.

- **JDK:** (completar)
- **IDE:** NetBeans (proyecto Maven)
- **Base de datos:** MySQL (completar: nombre del esquema, puerto, usuario y script SQL)

1. Clonar el repositorio:
   ```
   git clone https://github.com/yerayy11/mvc-acceso-a-datos.git
   ```
2. Abrir la carpeta en NetBeans (File → Open Project).
3. Crear la base de datos en local (ver apartado anterior).
4. Clean and Build para comprobar que compila.

## Cómo trabajamos con Git

Objetivo: que `main` esté siempre en un estado que compila y funciona, y que nadie pise el trabajo de otro.

### Reglas

1. **Nadie sube directamente a `main`.** Todo entra mediante Pull Request.
2. **Una rama por tarea, de vida corta.** Se crea al empezar la tarea y se borra al mergearla. No hay ramas personales permanentes.
3. **Nombres de rama:** en minúsculas y con guiones.
   - `feature/nombre-de-la-tarea` para funcionalidad nueva.
   - `fix/nombre-del-problema` para corregir errores.
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
