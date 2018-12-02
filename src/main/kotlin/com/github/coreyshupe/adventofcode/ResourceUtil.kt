package com.github.coreyshupe.adventofcode

fun String.asResource(applier: (String) -> Unit) =
    applier.invoke(this::class.java.getResource(this).readText().replace("\r", ""))

fun String.asLinedResource(applier: (List<String>) -> Unit) = this.asResource {
    applier.invoke(it.split('\n'))
}