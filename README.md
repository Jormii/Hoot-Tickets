# Hoot Tickets

## Índice
- [Introducción](#introduccion)
- [Descripción de la página](#descripcion)
  - [Tipos de usuario](#usuarios)
  - [Funcionalidad](#funcionalidad)
  - [Entidades principales](#entidades)
- [Equipo](#equipo)
- [Diagrama de navegación](#navegacion)
- [Capturas de pantalla](#capturas)
  - [Página principal](#capturasPrincipal)
  - [Proceso de compra](#capturasCompra)
  - [Proceso de creación de eventos](#capturasCreacion)
  - [Devolución de entradas](#capturasDevolucion)
  - [Registro de usuario](#capturasRegistro)

## Introducción<a name="introduccion"></a>
Hoot Tickets es una página para la compra y venta de entradas de eventos musicales.

## Descripción de la página<a name="descripcion"></a>
### Tipos de usuario<a name="usuarios"></a>
- Anónimo: Con este nombre se identifican a aquellos usuarios que acceden a la página sin haber realizado inicio de sesión.
- Registrado: Usuarios con cuenta en el sitio y que han realizado inicio de sesión.
- Manager: Un tipo especial de usuario registrado con la capacidad de poner en venta sus propias entradas.

### Funcionalidad<a name="funcionalidad"></a>
Se puede encontrar la funcionalidad y descripción de la página en más detalle en este [enlace](https://docs.google.com/document/d/1NrD6JB6T7d2Fr4xy9gx2P4ysY2c4kxBIPfvAas6bO0w/edit?usp=sharing).

#### Pública
Todo usuario podrá acceder a la página y realizar la compra de entradas sin necesidad de registrarse o iniciar sesión. Se proporcionará un servicio de búsqueda que permitirá al comprador filtrar entre los distintos eventos ofertados. Cuando el usuario encuentre el concierto al que desea asistir, eligirá qué día y tipo de entrada desea adquirir antes de realizar el pago para, posteriormente, adquirir su entrada.

#### Privada
- Usuarios registrados: Tendrán a su disposición un historial de compras en el que podrán revisar y descargar sus entradas en todo momento. Por otro lado, se les ofrecerá un servicio de devolución de entradas.
- Usuarios manager: Podrán crear y modificar sus propios eventos y especificar qué días y tipos de entradas ofertan para dichos eventos.

### Entidades principales<a name="entidades"></a>
- Usuario: Tiene un nombre y contraseña, puede ver sus datos y las entradas que ha comprado.

- Vendedor: Hereda de la clase usuario, posee funciones para añadir eventos, modificar los eventos que ha creado y ver la lista de estos.

- Evento: Está compuesto por el nombre, lugar, fecha, datos extras y la compañía a la que pertenece, junto con las entradas disponibles.

- Entrada: En esta clase se almacena la posición (butaca) del evento, junto con su precio. También se relaciona con el evento al que pertenece.

## Equipo<a name="equipo"></a>
[Trello](https://trello.com/b/EhPCgI2B/dad)

Nombre y apellidos | Correo | Cuenta de GitHub
-------------------|----------------------|-----------------
Jorge López Natal | j.lopezn.2016@alumnos.urjc.es | https://github.com/Jormii
José Luis Rodríguez Corcho| jl.rodriguezc.2016@alumnos.urjc.es | https://github.com/huros35
Yolanda Gómez Henche | y.gomezh@alumnos.urjc.es | https://github.com/yolandagomezh

## Diagrama de navegación<a name="navegacion"></a>
![alt text](https://i.imgur.com/bUqDjKc.png "Diagrama de navegación")

A 18 de febrero, no se han implementado:
- Inicio de sesión.
- Búsqueda/filtrado.
- Edición de eventos.

## Capturas de pantalla<a name="capturas"></a>
### Página principal<a name="capturasPrincipal"></a>
![alt text](https://i.imgur.com/sJ3RMr4.png "Página principal")
Desde aquí se puede acceder a todos los eventos ofertados y a las opciones de usuario.

### Compra de entradas<a name="capturasCompra"></a>
##### Información del evento
![alt text](https://i.imgur.com/bfc6URr.png "Información del evento")
Aquí se muestra toda la información de un evento junto con las sesiones que se celebran.

##### Información de la sesión y selección de tickets
![alt text](https://i.imgur.com/wUhXcp5.png "Sesión y sus tickets")
Muestra información de la sesión y lista los tickets a la venta para proceder a la compra.

##### Pantalla de pago
![alt text](https://i.imgur.com/lbbOKdX.png "Pantalla de pago")
Lista los tickets seleccionados y ofrece un formulario para realizar el pago.

##### Pantalla de pago realizado
![alt text](https://i.imgur.com/gWhjVYW.png "Pago realizado")
Avisa al usuario de que el pago se ha realizado correctamente.

### Creación de eventos<a name="capturasCreacion"></a>
##### Formulario inicial
![alt text](https://i.imgur.com/7Usgtgt.png "Formulario inicial")
Un formulario inicial que pide los detalles básicos del nuevo evento.

##### Creación del evento en detalle
![alt text](https://i.imgur.com/JVk0Y5f.png "Creación de evento")
Permite crear nuevas sesiones y entradas.

##### Creación de sesión
![alt text](https://i.imgur.com/J5ZyLtr.png "Creación de sesión")
Pregunta por los campos necesarios para crear una sesión.

##### Creación de entrada
![ælt text](https://i.imgur.com/3nGnVtO.png "Creación de entrada")
Pregunta por los campos necesarios para crear una entrada.

##### Creación de evento exitosa
![alt text](https://i.imgur.com/ZPsFkdY.png "Creación exitosa")
Avisa al usuario de que el evento ha sido creado exitosamente.

### Pantalla de devolución de entradas<a name="capturasDevolucion"></a>
![alt text](https://i.imgur.com/zBVmvHr.png "Devolución de entradas")
Permite a un usuario registrado reembolsar una compra realizada.

### Pantalla de registro de usuario<a name="capturasRegistro"></a>
![alt text](https://i.imgur.com/dASGcQf.png "Registro de usuario")
Permite a un usuario registrarse en el sitio.
