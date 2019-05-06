# MercadoLibreGalaxyTest
Examen de Ingreso a Mercado Libre

### Valores iniciales de los planetas

Planeta | Vel. Rotación | Sentido Rotación | Distancia al Sol
--------|---------------|------------------|-----------------
Ferengi|1 Grado/Dia|Horario|500 km
Vulcano|5 Grados/Dia|Anti-horario|1000 km
Betasoide|3 Grados/Dia|Horario|2000 km

### Condiciones de Clima

Clima|Condición
-----|---------
Sequía|Cuando los 3 planetas se encuentren alineados entre sí y también alineados respecto al sol.
Lluvia|Cuando los planetas formen un triángulo y el Sol se encuentre dentro de éste.
Lluvia Intensa|Cuando el perímetro del triángulo sea el mayor posible y el Sol se encuentre dentro de éste.
Condiciones óptimas|Cuando los 3 planetas se encuentren alineados entre sí pero no con el Sol.

### Requisitos
1 - Determinar cuántos períodos de sequía habrá.
2 - Determinar cuántos períodos de lluvia habrá y que día será el pico máximo de lluvia.
3 - Determinar cuántos períodos de condiciones óptimas habrá.

### Extra
Realizar y alojar un servicio REST el cuál permite invocar el endpoint api/clima?dia=123 el cuál debe devolver el pronóstico del clima para el día indicado.

### Extra ++
Fuera de lo solicitado, creé un endpoint en api/clima/summary el cual devuelve el resultado de los 3 primeros puntos.
