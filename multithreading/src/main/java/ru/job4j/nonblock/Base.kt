package ru.job4j.nonblock

data class Base( val id : Int , @field:Volatile private var value: Int)  {
    @Volatile
    var version : Int
    private set
    init {
        this.version = 0
    }

@Synchronized
    fun incrementVersion() {
            this.version++
        }

    fun setValue(value : Int) {
        this.value = value
    }
    fun getValue() : Int {
        return this.value
    }
}