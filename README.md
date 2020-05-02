# Hoot Tickets

## Índice
- [Introducción](#introduccion)
- [Vídeo de demostración](#video)
- [Despliegue de la aplicación](#despliegue)
- [Descripción de la página](#descripcion)
  - [Tipos de usuario](#usuarios)
  - [Funcionalidad](#funcionalidad)
  - [Entidades principales](#entidades)
  - [Servicio interno](#servicio)
- [Equipo](#equipo)
- [Diagrama de navegación](#navegacion)
- [Diagrama de clases](#clases)
- [Diagrama Entidad-Relación](#diagramaER)
- [Capturas de pantalla](#capturas)
  - [Página principal](#capturasPrincipal)
  - [Proceso de compra](#capturasCompra)
  - [Proceso de creación de eventos](#capturasCreacion)
  - [Devolución de entradas](#capturasDevolucion)
  - [Registro de usuario](#capturasRegistro)
  - [Inicio de sesión](#capturasLogin)

## Introducción<a name="introduccion"></a>
Hoot Tickets es una página para la compra y venta de entradas de eventos musicales.

## Vídeo de demostración<a name="video"></a>
**TODO**

## Despliegue de la aplicación<a name="despliegue"></a>
Para el despliegue se han utilizado Docker y Docker Compose. En la [última release](https://github.com/Jormii/Hoot-Tickets/releases) se puede encontrar un .zip llamado "deployment" que incluye los ficheros necesarios para arrancar la aplicación. Entre todos los ficheros se puede encontrar uno llamado "run_docker.sh". Basta con ejecutar este fichero para poner en marcha la aplicación. Este fichero pone en marcha:
- Dos contenedores con la imagen de la aplicación.
- Dos contenedores con la imagen del servicio interno.
- Un contenedor con un balanceador de carga Haproxy.
- Un contenedor con una base de datos MySQL.
- Un contenedor con una instancia de RabbitMQ.

## Descripción de la página<a name="descripcion"></a>
### Tipos de usuario<a name="usuarios"></a>
- Anónimo: Con este nombre se identifican a aquellos usuarios que acceden a la página sin haber realizado inicio de sesión.
- Registrado: Usuarios con cuenta en el sitio y que han realizado inicio de sesión.
- Vendedor: Un tipo especial de usuario registrado con la capacidad de poner en venta sus propias entradas.

### Funcionalidad<a name="funcionalidad"></a>
Se puede encontrar la funcionalidad y descripción de la página en más detalle en este [enlace](https://docs.google.com/document/d/1NrD6JB6T7d2Fr4xy9gx2P4ysY2c4kxBIPfvAas6bO0w/edit?usp=sharing).

#### Pública
Todo usuario podrá acceder a la página y realizar la compra de entradas sin necesidad de registrarse o iniciar sesión. Se proporcionará un servicio de búsqueda que permitirá al comprador filtrar entre los distintos eventos ofertados. Cuando el usuario encuentre el concierto al que desea asistir, eligirá qué día y tipo de entrada desea adquirir antes de realizar el pago para, posteriormente, adquirir su entrada.

#### Privada
- Usuarios registrados: Tendrán a su disposición un historial de compras en el que podrán revisar y descargar sus entradas en todo momento. Por otro lado, se les ofrecerá un servicio de devolución de entradas.
- Usuarios vendedor: Podrán crear y modificar sus propios eventos y especificar qué días y tipos de entradas ofertan para dichos eventos.

### Entidades principales<a name="entidades"></a>
- Usuario: Tiene un nombre y contraseña, puede ver sus datos y las entradas que ha comprado.

- Vendedor: Hereda de la clase usuario, posee funciones para añadir eventos, modificar los eventos que ha creado y ver la lista de estos.

- Evento: Está compuesto por el nombre, lugar, fecha, datos extras y la compañía a la que pertenece, junto con las entradas disponibles.

- Entrada: En esta clase se almacena la posición (butaca) del evento, junto con su precio. También se relaciona con el evento al que pertenece.


### Servicio interno<a name="servicio"></a>
El servicio interno consiste en una aplicación que se encarga de enviar un correo electrónico al comprador con un PDF adjunto en el que figurará la información de su entrada junto a un código QR. El correo electrónico destino será aquel con el que se haya registrado el usuario o el que este haya aportado en caso de que no tenga cuenta.

## Equipo<a name="equipo"></a>
[Trello](https://trello.com/b/EhPCgI2B/dad)

Nombre y apellidos | Correo | Cuenta de GitHub
-------------------|----------------------|-----------------
Jorge López Natal | j.lopezn.2016@alumnos.urjc.es | https://github.com/Jormii
José Luis Rodríguez Corcho| jl.rodriguezc.2016@alumnos.urjc.es | https://github.com/huros35
Yolanda Gómez Henche | y.gomezh@alumnos.urjc.es | https://github.com/yolandagomezh

## Diagrama de navegación<a name="navegacion"></a>
![alt text](https://i.imgur.com/5cC7tfw.png "Diagrama de navegación")

## Diagrama de clases<a name="clases"></a>
![alt text](https://i.imgur.com/ayNorJh.png "Diagrama de clases")

## Diagrama Entidad-Relación<a name="diagramaER"></a>
![alt text](https://i.imgur.com/BNdHln5.jpg "Diagrama ER")

## Capturas de pantalla<a name="capturas"></a>
### Página principal<a name="capturasPrincipal"></a>
![alt text](https://i.imgur.com/9LXmk4e.png "Página principal sin haber iniciado sesión")
![alt text](https://i.imgur.com/HxRBtXp.png "Página principal habiendo iniciado sesión")
Desde aquí se puede acceder a todos los eventos ofertados y a las opciones de usuario.

### Compra de entradas<a name="capturasCompra"></a>
##### Información del evento
![alt text](https://i.imgur.com/30ayMr6.png "Información del evento")
Aquí se muestra toda la información de un evento junto con las sesiones que se celebran.

##### Información de la sesión y selección de tickets
![alt text](https://i.imgur.com/7ZRKLMR.png "Sesión y sus tickets")
Muestra información de la sesión y lista los tickets a la venta para proceder a la compra.

##### Pantalla de pago
![alt text](https://i.imgur.com/Kd7LZcH.png "Pantalla de pago")
Lista los tickets seleccionados y ofrece un formulario para realizar el pago. Si el usuario no ha iniciado sesión aparecerá un campo adicional en el formulario pidiendo una dirección de correo electrónico.

##### Pantalla de pago realizado
![alt text](https://i.imgur.com/jmurM6x.png "Pago realizado")
Avisa al usuario de que el pago se ha realizado correctamente.

### Creación de eventos<a name="capturasCreacion"></a>
##### Formulario inicial
![alt text](https://i.imgur.com/kJ4xQ1r.png "Formulario inicial")
Un formulario inicial que pide los detalles básicos del nuevo evento.

##### Creación del evento en detalle
![alt text](https://i.imgur.com/DvBqSUM.png "Creación de evento")
Permite crear nuevas sesiones y entradas.

##### Creación de sesión
![alt text](https://i.imgur.com/GHshcXD.png "Creación de sesión")
Pregunta por los campos necesarios para crear una sesión.

##### Creación de entrada
![alt text](https://i.imgur.com/H8VCZFO.png "Creación de entrada")
Pregunta por los campos necesarios para crear una entrada.

##### Creación de evento exitosa
![alt text](https://i.imgur.com/RZ1twlg.png "Creación exitosa")
Avisa al usuario de que el evento ha sido creado exitosamente.

### Pantalla de devolución de entradas<a name="capturasDevolucion"></a>
![alt text](https://i.imgur.com/mGhDuCx.png "Devolución de entradas")
Permite a un usuario registrado reembolsar una compra realizada.

### Pantalla de registro de usuario<a name="capturasRegistro"></a>
![alt text](https://i.imgur.com/HouHAwS.png "Registro de usuario")
Permite a un usuario registrarse en el sitio.

### Pantalla de inicio de sesión<a name="capturasLogin"></a>
![alt text](https://i.imgur.com/MROubKZ.png "Inicio de usuario")
