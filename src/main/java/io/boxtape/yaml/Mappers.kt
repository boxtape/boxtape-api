package io.boxtape.yaml

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class Mappers {
    companion object Yaml {
        fun mapper(): ObjectMapper {
            val yamlMapper = ObjectMapper(YAMLFactory())
            val module = SimpleModule()

            module.addSerializer(javaClass<Pair<Any, Any>>(), PairWriter())
            yamlMapper.registerModule(module)
            yamlMapper.registerKotlinModule()
            return yamlMapper;
        }
    }
}
