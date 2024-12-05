//En los Test de FRONT ejecutar primero testRegister y luego testNewAccount, para luego poder utilizar el resto de los test usadno los datos ingresados en esos test y que quedan almacenados en la BD, ya que después de cierto tiempo la BD se borra, en el caso de que cuenten con datos propios funcionando ingresarlos a los test y correrlos en el orden que prefieran.
//En los Test de back cree variables para ingresar de forma más rápida los datos de USERNAME, PASSWORD, ACCOUNTID y no tener que buscarlo y modificarlo en el código, por el mismo problema que despues de un tiempo se borran los datos de la BD.
Proyecto de Pruebas Automatizadas - Para Bank
Este proyecto contiene un conjunto completo de pruebas automatizadas para el sistema Para Bank, utilizando Java y Selenium para la automatización de pruebas de interfaz de usuario (front-end) y RestAssured para las pruebas de la API (back-end).

Objetivo
El objetivo de este proyecto es automatizar las pruebas de las funcionalidades clave de un sistema bancario, como el registro de usuarios, apertura de cuentas, visualización de la cuenta, transferencia de fondos y revisión de la actividad de la cuenta. El proyecto también incluye pruebas de la API para verificar que los servicios backend respondan correctamente.

Pruebas Front-end
1. Registro de Usuario
Pasos:
Hacer clic en el botón <Registro>.
Rellenar el formulario de registro con los datos requeridos.
Hacer clic nuevamente en el botón <Registro>.
Verificar que el texto "Su cuenta se ha creado correctamente. En la pantalla se puede ver 'You are now logged in'." esté visible.
2. Apertura de Nueva Cuenta
Pasos:
Hacer clic en el botón <Abrir nueva cuenta>.
En el apartado "¿Qué tipo de cuenta desea abrir?", seleccionar la opción <SAVINGS>.
Hacer clic en el botón <Abrir nueva cuenta>.
Verificar que el texto "Congratulations, your account is now open." esté visible.
3. Resumen de Cuentas
Pasos:
Hacer clic en el botón <Resumen de cuentas>.
Verificar que el texto "*El saldo incluye depósitos que pueden estar sujetos a retenciones" esté visible.
4. Transferir Fondos
Pasos:
Hacer clic en el botón <Transferencia de fondos>.
Verificar que el texto "Transferir fondos" esté visible.
En el campo <Importe: $>, introducir el monto a transferir.
En el campo <De la cuenta #>, seleccionar una cuenta.
En el campo <A la cuenta #>, seleccionar una cuenta diferente.
Hacer clic en el botón <Transferencia>.
Verificar que el texto "¡Transferencia completa!" esté visible.
5. Actividad de la Cuenta (Mensual)
Pasos:
Hacer clic en el botón <Resumen de cuentas>.
Verificar que el texto "*El saldo incluye depósitos que pueden estar sujetos a retenciones" esté visible.
Hacer clic en una cuenta en la columna <Cuenta>.
Verificar que el texto "Detalles de la cuenta" esté visible.
En "Actividad de la cuenta", hacer clic en <Periodo de actividad:> y seleccionar "Todo".
En "Actividad de la cuenta", hacer clic en <Tipo:> y seleccionar "Todo".
Hacer clic en el botón <Ir>.

Pruebas Back-end de la API
1. Validación de código de estado 200
A continuación se presentan las URLs de las pruebas de la API que se validarán para asegurar que las respuestas son correctas (código de estado 200):

Registro de usuario:
https://parabank.parasoft.com/parabank/register.htm

Apertura de nueva cuenta:
https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=12545&newAccountType=1&fromAccountId=xxxxx

Resumen de cuentas:
https://parabank.parasoft.com/parabank/overview.htm

Transferencia de fondos:
https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=13566&toAccountId=13677&amount=xxxxx

Actividad de la cuenta (mensual):
https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/13566/transactions/month/All/type/All

Requerimientos Técnicos
Java 17 o superior.
Maven para la gestión de dependencias y construcción del proyecto.
Selenium para la automatización de pruebas de interfaz de usuario (front-end).
RestAssured para las pruebas de API (back-end).

Estructura del Proyecto
El proyecto está organizado en las siguientes carpetas:
src/main/java: Contiene el código fuente del proyecto.
tests: Aquí se encuentran las clases de prueba automatizadas, tanto para el front-end (Selenium) como para el back-end (RestAssured).
utils: Utilidades comunes utilizadas en las pruebas.
src/test/resources: Archivos de configuración y otros recursos necesarios para las pruebas, como los datos de entrada.

Informes de Pruebas
Una vez que las pruebas se hayan ejecutado, se generarán informes detallados en formato HTML que podrás encontrar en la siguiente ubicación dentro del proyecto: