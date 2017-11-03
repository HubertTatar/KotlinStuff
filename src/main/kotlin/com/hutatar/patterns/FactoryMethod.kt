package com.hutatar.patterns

interface Spider {
    val venomous: Boolean
}

class Tarantula(override val venomous: Boolean = false): Spider
class BlackWidow(override val venomous: Boolean = true): Spider

enum class Continent {
    SouthAmerica, Australia
}

class SpiderFactory {
    fun spiderForContinent(continent: Continent): Spider? {
        return when (continent) {
            Continent.Australia     -> BlackWidow()
            Continent.SouthAmerica  -> Tarantula()
            else                    -> throw IllegalArgumentException("")
        }
    }
}
