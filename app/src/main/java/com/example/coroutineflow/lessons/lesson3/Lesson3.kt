package com.example.coroutineflow.lessons.lesson3


import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

suspend fun main() {
    val res = getFlowByBuilderFlow().filter { it.isPrime() }
        .filter { it > 20 }
        .map {
            println("Map")
            "Number: $it"
        }
        .count()
    println(res)
//        .toList()
//        .collect { println(it) }
}

fun getFlowByFlowOfBuilder(): Flow<Int> {
    return flowOf(3, 4, 8, 16, 5, 7, 11, 32, 41, 28, 43, 47, 84, 116, 53, 59, 61)
}

fun getFlowByBuilderFlow(): Flow<Int> {
    val firstFlow = getFlowByFlowOfBuilder()


    return flow {
        var i = 0

        while (i < 30) {
            emit(i++)
        }

//        emitAll(firstFlow)

//        firstFlow.collect {
//            println("Emitted from firstFlow: $it")
//            emit(it)
//        }
    }
}

suspend fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    for (i in 2..this / 2) {
        delay(50)
        if (this % i == 0) return false
    }
    return true
}