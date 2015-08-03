package io.boxtape

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.google.common.base.Splitter
import io.boxtape.yaml.PairWriter
import org.apache.commons.lang3.text.StrSubstitutor

fun String.toPairList(keyValueSeperator:String = ":", itemSeperator:String = ","):List<Pair<String,String>> {
    return Splitter.on(itemSeperator)
        .trimResults()
        .withKeyValueSeparator(keyValueSeperator)
        .split(this)
        .map { Pair(it.key, it.value) }
}

fun String.toKeyValueMap(keyValueSeperator:String = ":", itemSeperator:String = ","):Map<String,String> {
    return this.toPairList(keyValueSeperator,itemSeperator).toMap()
}

fun Any.asYaml():String {
    val yamlMapper = ObjectMapper(YAMLFactory())
    val module = SimpleModule()
    module.addSerializer(javaClass<Pair<Any, Any>>(), PairWriter())
    yamlMapper.registerModule(module)
    return yamlMapper.writeValueAsString(this);
}

fun String?.withPlaceholdersReplaced(replacements:Map<String,String>):String? {
    if (this == null) {
        return null;
    } else {
        val substitutor = StrSubstitutor(replacements)
        return substitutor.replace(this)
    }

}
