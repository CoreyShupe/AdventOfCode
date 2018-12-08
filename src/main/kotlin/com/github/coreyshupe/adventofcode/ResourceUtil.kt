package com.github.coreyshupe.adventofcode

sealed class ResourceType<T> {
    object Full : ResourceType<String>()
    object Lined : ResourceType<List<String>>()
    object CommaSplit : ResourceType<List<String>>()
    object SpaceSplit : ResourceType<List<String>>()
}

@Suppress("UNCHECKED_CAST")
fun <T> String.asResource(type: ResourceType<T>, applier: (T) -> Unit) {
    val text = this::class.java.getResource(this).readText().replace("\r", "")
    when (type) {
        ResourceType.Full -> applier(text as T)
        ResourceType.Lined -> applier(text.split('\n') as T)
        ResourceType.CommaSplit -> applier(text.split(',') as T)
        ResourceType.SpaceSplit -> applier(text.split(' ') as T)
    }
}