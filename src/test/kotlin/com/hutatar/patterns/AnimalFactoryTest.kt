package com.hutatar.patterns

import org.junit.Assert.*
import org.junit.Test

class AnimalFactoryTest {

    @Test
    fun createVertebrata(): Unit {
        val animalFactory = AnimalFactory.createFactory<VertebrataFactory>()
        assertTrue(Vertebrata::class == animalFactory.createAnimal()::class)
    }

    @Test
    fun createInvertebrata(): Unit {
        val animalfactory = AnimalFactory.createFactory<InvertebrataFactory>()
        assertTrue(Invertebrata::class == animalfactory.createAnimal()::class)
    }

    @Test
    fun createVertebrataNotTrue(): Unit {
        val animalFactory = AnimalFactory.createFactory<VertebrataFactory>()
        assertTrue(Invertebrata::class != animalFactory.createAnimal()::class)
    }

    @Test
    fun createInvertebrataNotTrue(): Unit {
        val animalfactory = AnimalFactory.createFactory<InvertebrataFactory>()
        assertTrue(Vertebrata::class != animalfactory.createAnimal()::class)
    }

    @Test(expected = IllegalArgumentException::class)
    fun factoryFail(): Unit {
        AnimalFactory.createFactory<FakeAnimalFactory>()
    }
}

class FakeAnimalFactory: AnimalFactory() {
    override fun createAnimal(): Animal {
        throw NotImplementedError("should not work")
    }
}