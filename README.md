# FishCare

## Levantar el entorno de desarrollo

### PostgreSQL & PGAdmin

* docker-compose up

### Backend Spring Boot

* mvn spring-boot:run

## Utilización

Se utiliza Swagger y Swagger UI para probar los Endpoints. Accediendo a la siguiente dirección: localhost:8080/swagger-ui.html

# Información general

## Uso de MQTT

Los datos de configuración están en application.properties. 

Para utilizar la implementación de MQTT (para enviar un mensaje a un topic concreto):

public class YourClass {
  @Resource
  private MqttMessageClient mqttMessageClient;

  public void yourMethod() {
    mqttMessageClient.sendMessage("topic", "content");
  }
}