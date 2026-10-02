import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper
import groovy.json.JsonBuilder

def Message processData(Message message) {
    // 1. Get the payload body (Raw JSON string from OpenWeatherMap API)
    def body = message.getBody(java.lang.String)
    
    // 2. Parse the incoming JSON using JsonSlurper
    def jsonSlurper = new JsonSlurper()
    def weatherData = jsonSlurper.parseText(body)
    
    // 3. Map the required fields into the desired output structure
    def transformedPayload = [
        "City"       : weatherData.name,
        "Temperature": "${weatherData.main.temp} °C",
        "Weather"    : weatherData.weather[0]?.description ?: "",
        "Humidity"   : "${weatherData.main.humidity} %",
        "WindSpeed"  : "${weatherData.wind.speed} m/s"
    ]
    
    // 4. Convert the mapped object back into formatted JSON using JsonBuilder
    def jsonBuilder = new JsonBuilder(transformedPayload)
    
    // 5. Update the message body with the new JSON
    message.setBody(jsonBuilder.toPrettyString())
    
    return message
}