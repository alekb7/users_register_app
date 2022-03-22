# users_register_app
App para registrar usuarios.

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

Observaciones:
- La Contraseña debe contener entre 8 y 20 caracteres. Debe contener al menos una letra mayuscula, una minuscula y un digito.
- La direccion de correo debe terminar en .cl y contener su respectivo @ .
- El mail no puede ser registrado 2 veces.
- Se puede registrar el usuario con una lista de telefonos en caso de contener mas de uno.
- La contraseña se almacenara en la BD encriptada mediante UUII.
