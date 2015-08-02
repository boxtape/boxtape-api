package io.boxtape.core.ansible

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude

public data class AnsibleRole(
    val name: String,
    val src: String,
    JsonInclude(JsonInclude.Include.NON_NULL) val version: String? = null,
    JsonIgnore val args: List<Pair<String, Any>>?
) {
    /**
     * Returns the role as a declaration for use within a playbook.
     * (excludes the role co-ordinates written to requirements.yml)
     */
    fun asPlaybookDeclaration(): Map<String, Any> {
        val map = linkedMapOf<String, Any>(Pair("role", name))
        args?.forEach { map.put(it.first, it.second) }
        return map
    }
}
