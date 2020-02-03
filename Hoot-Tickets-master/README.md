# Hoot Tickets

## Índice
- [Introducción](#introduccion)
- [Descripción de la página](#descripcion)
  - [Tipos de usuario](#usuarios)
  - [Funcionalidad](#funcionalidad)
  - [Entidades principales](#entidades)
- [Equipo](#equipo)

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
