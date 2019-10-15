package br.com.mirabilis.kotdeserializer

import com.google.gson.*
import java.lang.reflect.Type
import java.util.HashMap

internal class DeserializerNya : JsonDeserializer<ModelNya> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ModelNya {
        val jsonObject = json.asJsonObject

        // Read simple String values.
        val uri = jsonObject.get(KEY_URI).asString
        val httpMethod = jsonObject.get(KEY_METHOD).asString

        // Read the dynamic parameters object.
        val parameters = readParametersMap(jsonObject)

        val result = ModelNya(uri,httpMethod, parameters)

        return result
    }

    private fun readParametersMap(jsonObject: JsonObject): Map<String, String>? {
        val paramsElement = jsonObject.get(KEY_PARAMETERS)
            ?: // value not present at all, just return null
            return null

        val parametersObject = paramsElement.asJsonObject
        val parameters = HashMap<String, String>()
        for ((key, value1) in parametersObject.entrySet()) {
            val value = value1.asString
            parameters[key] = value
        }
        return parameters
    }

    companion object {

        private val KEY_URI = "url"
        private val KEY_METHOD = "httpMethod"
        private val KEY_PARAMETERS = "parameters"
    }
}

private operator fun String?.invoke(uri: String?) {

}
