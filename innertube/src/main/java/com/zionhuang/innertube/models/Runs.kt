package com.zionhuang.innertube.models

import kotlinx.serialization.Serializable

@Serializable
data class Runs(
    val runs: List<Run>?,
)

@Serializable
data class Run(
    val text: String,
    val navigationEndpoint: NavigationEndpoint?,
)

fun List<Run>.splitBySeparator(): List<List<Run>> {
    val res = mutableListOf<List<Run>>()
    var tmp = mutableListOf<Run>()
    forEach { run ->
        if (run.text == " • ") {
            res.add(tmp)
            tmp = mutableListOf()
        } else {
            tmp.add(run)
        }
    }
    res.add(tmp)
    return res
}

// Removes identification item such as 'song' 'episode' if it's present in list
fun List<List<Run>>.removeIdentificationItem(): List<List<Run>> {
    return this.firstOrNull()
        ?.firstOrNull()?.navigationEndpoint?.let {
            this
        } ?: this.drop(1)
}

fun List<Run>.oddElements() = filterIndexed { index, _ ->
    index % 2 == 0
}
