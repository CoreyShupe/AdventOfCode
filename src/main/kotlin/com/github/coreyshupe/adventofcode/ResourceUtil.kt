package com.github.coreyshupe.adventofcode

enum class ResourceType {
    FULL,
    LINED,
    COMMA_SPLIT
}

fun String.asResource(type: ResourceType, applier: (Any) -> Unit) {
    val text = this::class.java.getResource(this).readText().replace("\r", "")
    when (type) {
        ResourceType.FULL -> applier(text)
        ResourceType.LINED -> applier(text.split('\n'))
        ResourceType.COMMA_SPLIT -> applier(text.split(','))
    }
}