package com.pokedex.utils.text

fun String.addPaths(vararg paths: String): String {
    //Do not remove escape from pattern, even if AS recommend it
    val pattern = "\\{\\w+\\}"
    val url = this.replace(pattern.toRegex(), "%s")
    return url.format(*paths)
}