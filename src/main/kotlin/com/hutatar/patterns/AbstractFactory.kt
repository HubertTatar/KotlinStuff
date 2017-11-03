package com.hutatar.patterns

interface Animal

class Vertebrata: Animal
class Invertebrata: Animal


abstract class AnimalFactory {
    abstract fun createAnimal(): Animal

    companion object {
        inline fun <reified T: AnimalFactory> createFactory(): AnimalFactory = when(T::class) {
            VertebrataFactory::class   -> VertebrataFactory()
            InvertebrataFactory::class -> InvertebrataFactory()
            else                       -> throw IllegalArgumentException("unknown argument")
        }
    }
}

class VertebrataFactory: AnimalFactory() {
    override fun createAnimal(): Animal = Vertebrata()
}

class InvertebrataFactory: AnimalFactory() {
    override fun createAnimal(): Animal = Invertebrata()
}


fun main(args: Array<String>) {
    val vertebrataFactory = AnimalFactory.createFactory<VertebrataFactory>()
    println(vertebrataFactory.createAnimal())
}