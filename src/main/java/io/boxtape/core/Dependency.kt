package io.boxtape.core

import com.github.zafarkhaja.semver.Version

data class Dependency(val groupId: String, val artifactId: String, val version: String) {

    /**
     * Indicates if this dependency matches other.
     *
     * It is expected that this method is called on a fully
     * qualified, resolved Dependency.
     *
     * The value of other may contain version ranges.
     */
    fun matches(other: Dependency): Boolean {
        return this.groupId == other.groupId &&
            this.artifactId == other.artifactId &&
            Version.valueOf(version).satisfies(other.version)
    }

    fun name(): String = "${groupId}:${artifactId}:${version}"
}
