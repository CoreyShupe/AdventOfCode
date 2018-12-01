package com.github.coreyshupe.adventofcode

fun String.asResource(applier: (String) -> Unit) =
    applier.invoke(this::class.java.getResource(this).readText().replace("\r", ""))