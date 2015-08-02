package io.boxtape.core.ansible

import io.boxtape.core.configuration.Configuration
import io.boxtape.core.Dependency

public interface PlayProvider {
    fun canProvideFor(dependency: Dependency): Boolean

    fun isNeeded(playbook: String): Boolean {
        return true;
    }

    fun provideVagrantConfiguration(): List<String> {
        return emptyList()
    }

    fun provideApplicationConfiguration() : List<String> {
        return emptyList()
    }
    fun provideRoles(dependency: Dependency, config: Configuration) : List<AnsibleRole> {
        return emptyList()
    }
    fun provide(dependency: Dependency, config: Configuration): List<AnsiblePlay> {
        return emptyList()
    }
}
