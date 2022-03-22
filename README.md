# users_register_app
App para registrar usuarios.

*Conexion con la Base de Datos.

Para configurar la conexion con la base de datos se debe configurar el archivo "application.properties" especificando el datasource.
En este caso con la BD levantada localmente en la direccion localhost:3306 mediante MySQL:
"spring.datasource.url=jdbc:mysql://localhost:3306/db_aleksei_prueba?useSSL=false&serverTimezone=UTC"

Recomendable levantar una instancia de BD con MySQL local en el puerto 3306 y ajustar los datos: nombre de la bd, usuario, contraseña en el application.properties.

*Ejecucion de la app por consola (dependencia: mvn install): 
cd "ruta del proyecto"
mvnw spring-boot:run

*Ejecutar mediante un cliente HTTP (ejemplo POSTMAN):
Method: POST
URL: http://localhost:8080/api/users
Ejemplo de request (BODY):

{
  "name": "nombre apellido",
  "email": "email@dominio.cl",
  "password": "Example1234",
  "phones": [
      {
          "number": "1234567",
          "citycode": "1",
          "contrycode": "57"
      }
   ]
}

*Observaciones:
- La Contraseña debe contener entre 8 y 20 caracteres. Debe contener al menos una letra mayuscula, una minuscula y un digito.
- La direccion de correo debe terminar en .cl y contener su respectivo @ .
- El mail no puede ser registrado 2 veces.
- Se puede registrar el usuario con una lista de telefonos en caso de contener mas de uno.
- La contraseña se almacenara en la BD encriptada mediante UUII.



