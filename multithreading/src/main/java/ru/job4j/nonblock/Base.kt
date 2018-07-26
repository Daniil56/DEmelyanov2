package ru.job4j.nonblock

data class Base(var version : Int, val id : Int , @Volatile  var value: Int)  {

    fun incrementVersion() {
        synchronized(lock = this) {
            this.version++
        }
    }
}