package io.boxtape.core.configuration

import org.apache.commons.lang3.text.StrSubstitutor

/**
 * Project configuration -- allows components
 * to register properties that will be ultimately written out
 * and configure the running application
 *
 * Not the same as BoxtapeSettings -- which focusses on Boxtape itself
 */
class Configuration {

    private val properties : MutableMap<String,String> = hashMapOf()
    val vagrantSettings = VagrantSettings()
    fun registerPropertyWithDefault(propertyName: String, defaultValue: String) {
        properties.put(propertyName,defaultValue)
    }

    fun asStrings(): List<String> {
        val substitutor = StrSubstitutor(properties)
        val strings = properties.map { "${it.key}=${substitutor.replace(it.value)}" }
        return strings
    }

    fun addForwardedPort(hostPort: String, guestPort: String) {
        vagrantSettings.addForwardedPort(hostPort,guestPort)
    }


}
