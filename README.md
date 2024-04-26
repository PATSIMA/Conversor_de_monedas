# Conversor de monedas


Participé como desarrollador back-end en el proyecto G6 de Alura Latam, donde contribuí al desarrollo de una aplicación Java que facilita la conversión de divisas a través de solicitudes a una API externa. Esta aplicación cuenta con una interfaz de consola que permite al usuario realizar todas las operaciones de conversión de manera intuitiva y sencilla


# **🔧 Funcionalidades 🔧**

**1.- Visualización de Monedas Disponibles:** Ofrece una funcionalidad para visualizar las monedas disponibles para realizar intercambios, permitiendo a los usuarios familiarizarse con las opciones disponibles en el sistema.

**2.- Conversión de Divisas:** Facilita la conversión eficiente y precisa entre diferentes monedas, brindando a los usuarios la capacidad de intercambiar valores con facilidad y precisión.

**3.- Registro de Conversiones:** Se ha implementado un sistema de registro que documenta cada conversión realizada. Esto brinda a los usuarios un historial detallado de sus transacciones pasadas para su referencia y seguimiento. La información se guarda tanto en un archivo conversion_log.txt como en la misma interfaz, asegurando un acceso conveniente y completo a los registros.

![image](https://github.com/PATSIMA/Conversor_de_monedas/assets/91814599/57012190-392f-42fa-85d0-8a46870672c3)


**4.-Integración Simplificada de API Key:** Ahora, la integración de la API key se ha simplificado aún más. Ya no es necesario incluirla en un archivo de texto (.txt). En su lugar, puedes ingresarla directamente en la interfaz, proporcionando una solución intuitiva y fácil de implementar para los usuarios. Esto asegura un proceso de configuración sin complicaciones.

![image](https://github.com/PATSIMA/Conversor_de_monedas/assets/91814599/f3c13b98-bdcd-4977-a52e-f67b84d5afdd)

![2024-04-26-16-20-18_Trim](https://github.com/PATSIMA/Conversor_de_monedas/assets/91814599/8d38860e-61e4-4bf5-b9ef-8ba9bfae7bb1)

# **🛠️ Caracteristicas del proyecto 🛠️**

**•	 Conversor de Moneda:** El proyecto consiste en un conversor de moneda que permite al usuario realizar conversiones entre diferentes tipos de moneda. Esta funcionalidad resulta útil para aquellos que necesitan conocer el valor equivalente de una moneda en relación con otra.

**•	 Interfaz de Usuario Amigable:** Se ha desarrollado una interfaz clara y fácil de entender que presenta al usuario las opciones disponibles para realizar conversiones de moneda. Esto mejora la experiencia del usuario al hacer que la interacción sea más intuitiva.

**•	Acceso a API de Tipo de Cambio:** Se ha utilizado una API externa (ExchangeRate-API) para obtener los tipos de cambio más recientes. Esto garantiza que las conversiones se realicen con datos actualizados, aumentando así la precisión de los resultados.

**• Manejo de Excepciones:** Se ha implementado un manejo robusto de excepciones para gestionar posibles errores durante la ejecución del programa, como errores de conexión, errores de entrada del usuario o problemas con archivos. Esto mejora la robustez y la confiabilidad del programa.

**• Registro de Conversiones:** El programa guarda un registro de las conversiones realizadas en un archivo de registro (conversion_log.txt). Esto proporciona una trazabilidad útil para el usuario, así como información de registro para cualquier auditoría o seguimiento necesario.

• **Flexibilidad en las Conversiones:** Se ofrece una amplia gama de opciones de conversión, lo que permite al usuario convertir entre diferentes pares de monedas, como dólar a peso argentino, peso argentino a dólar, etc. Esta flexibilidad hace que el programa sea versátil y adaptable a diversas necesidades de conversión.


# ✔️ Funciones del conversor  ✔️ 

###   MAIN 
` main(String[] args): El método principal que ejecuta el programa. Incluye la lógica principal para el menú de opciones, la entrada del usuario y la realización de conversiones de moneda.`

 `writeConversionLog(BufferedWriter writer, String conversion, double amount, double result): Método para escribir en un archivo de registro la información sobre las conversiones realizadas, incluyendo la moneda de origen, el monto convertido y el resultado de la conversión.`
 
`setupConversionMap(Map<Integer, String> map): Método para inicializar un mapa que asigna números de opción del menú a códigos de moneda utilizados en las solicitudes a la API de tasas de cambio.`

`createRequest(String url, String... args): Método para construir una URL de solicitud a la API de tasas de cambio, sustituyendo placeholders en la URL con los argumentos proporcionados.`

### DIVISA
`Divisa(Monedas monedas): Constructor que recibe un objeto Monedas para inicializar la instancia de Divisa.`

`setBase_code(String base_target, double cantidad): Método para establecer el código base y de destino junto con la cantidad a ser convertida. Llama al método calcular() para realizar la conversión.`

`calcular(): Método privado para calcular el resultado de la conversión utilizando las tasas de cambio proporcionadas por el objeto Monedas.`

`getResultado(): Método para obtener el resultado de la conversión.`

`toString(): Método para representar la instancia de Divisa como una cadena de texto, mostrando la cantidad convertida y las monedas base y de destino.`

### API
`Api(): Constructor que inicializa el cliente HTTP.`

`getResponse(String url): Método que toma una URL como entrada, crea una solicitud HTTP GET con esa URL utilizando el cliente HTTP y devuelve el cuerpo de la respuesta como una cadena de texto.`

### JSONPARSING 
`getMonedas(String json): Este método toma una cadena JSON como entrada y utiliza la biblioteca Gson para convertirla en un objeto de tipo Monedas, que parece ser una clase definida en otro lugar de tu código y que contiene información sobre las tasas de cambio de divisas.`

### KEY
`Key(String filePath) throws IOException: Constructor que toma la ruta del archivo como entrada y llama al método readApiKeyFromFile() para leer la clave de la API desde el archivo.`

`readApiKeyFromFile(String filePath) throws IOException: Método privado que toma la ruta del archivo como entrada, lee la clave de la API desde el archivo utilizando un BufferedReader y la almacena en la variable apiKey.`

`getApiKey(): Método público que devuelve la clave de la API almacenada en la variable apiKey.`

### MONEDAS
`base_code: Almacena el código de la moneda base para el conjunto de tasas de cambio.`

`conversion_rates: Almacena un objeto JsonObject que contiene las tasas de cambio entre la moneda base y otras monedas.
`
# Insignia del desafió
![Badge-Conversor-removebg-preview](https://github.com/PATSIMA/Conversor_de_monedas/assets/91814599/c2a2c6d9-0dc3-488b-889c-6dc662aa4bf4)
