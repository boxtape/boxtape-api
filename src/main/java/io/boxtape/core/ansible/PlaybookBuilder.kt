package io.boxtape.core.ansible

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.google.common.collect.ArrayListMultimap
import io.boxtape.asYaml
import io.boxtape.core.configuration.Configuration
import io.boxtape.core.Dependency
import io.boxtape.yaml.PairWriter

public class PlaybookBuilder(
    val providers:ArrayListMultimap<Dependency,PlayProvider>,
    val config: Configuration
) {
    fun asYaml(): String {
        return listOf(build()).asYaml()
    }
    fun build():Playbook {
        val playbook = Playbook();
        providers.keySet().forEach { dependency ->
            providers.get(dependency).forEach { provider ->
                playbook.addRoles(provider.provideRoles(dependency,config))
                playbook.addPlays(provider.provide(dependency,config))
            }
        }
        return playbook
    }
}
