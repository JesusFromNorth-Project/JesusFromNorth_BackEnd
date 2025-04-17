

## 📚 Documentación de Referencia

Antes de ejecutar el proyecto, asegúrate de seguir los siguientes pasos para una 
correcta configuración y despliegue.

---

## ✅ Configurar la Base de Datos

1. Abre tu gestor de base de datos MySQL y crea una nueva base de datos con el siguiente nombre:

   ```
   CREATE DATABASE system_clinic;
   
2. Dirígete a la ruta del proyecto:

    ```
   src/main/resources/

3. Abra el archivo `application.properties` y copie todo el codigo de abajo:
    ```
   #versionado de la api
    server.servlet.context-path=/system_clinic/api/v0.1
   
   #configuracion de la base de datos
    spring.datasource.url=jdbc:mysql://localhost:3306/system_clinic
    spring.datasource.username=TU USERNAME DE LA BD
    spring.datasource.password=TU CONTRASEÑA DE LA BD
   
   #configuracion del hibernate y jpa
    spring.jpa.show-sql=true
    spring.jpa.generate-ddl=true
    spring.jpa.hibernate.ddl-auto=update
   
   #puerto de la api
    server.port=5040

> ⚠️ **IMPORTANTE:** Asegúrate de que la base de datos `system_clinic` esté 
> creada en tu MySQL antes de ejecutar el proyecto.

> **NOTA:** Despues de un tiempo `hibernate.ddl-auto=update` pasara a ser  `none`
> para no tener problemas en la ejecucion, en caso de que haiga un error en la bd despues de ciertas modificacion
> borrela y vuelva a crearla y ejecute el programa, eso casi siempre lo arregla.
