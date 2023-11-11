package com.hpr.core.util

fun <T>MutableList<T>.addOrRemove(element : T){
    if (!contains(element)) add(element) else remove(element)
}
