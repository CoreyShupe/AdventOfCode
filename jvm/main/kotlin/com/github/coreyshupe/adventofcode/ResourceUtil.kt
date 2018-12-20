package com.github.coreyshupe.adventofcode

sealed class ResourceType<T> {
    object Full : ResourceType<String>()
    object Lined : ResourceType<List<String>>()
    object CommaSplit : ResourceType<List<String>>()
    object SpaceSplit : ResourceType<List<String>>()
}

fun <T> input(year: Int, day: Int, type: ResourceType<T>, applier: (T) -> Any) =
    "/20$year/day${day}_input".asResource(type, applier)

@Suppress("UNCHECKED_CAST")
fun <T> String.asResource(type: ResourceType<T>, applier: (T) -> Any) {
    val text = this::class.java.getResource(this).readText().replace("\r", "")
    when (type) {
        ResourceType.Full -> applier(text as T)
        ResourceType.Lined -> applier(text.split('\n') as T)
        ResourceType.CommaSplit -> applier(text.split(',') as T)
        ResourceType.SpaceSplit -> applier(text.split(' ') as T)
    }
}