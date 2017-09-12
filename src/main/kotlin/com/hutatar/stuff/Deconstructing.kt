package com.hutatar.stuff

fun main(args: Array<String>) {
    val a = Deconstructing()
//    a.simple()
//    a.deconstractInList()
//    a.returnDecTest()
//    a.deconstructionOfAMap()
    a.deconstructionInLambdas()
}

class Deconstructing {
    //http://kotlinlang.org/docs/reference/multi-declarations.html
    fun simple() {
        val alice = Pet("alice", 6)
        val (name, age) = alice
        println(name)
        println(age)
        //val (name, age, loot) = alice//destructuring declaration initlizer must have component3
        //val (name) = alice//conflicting delcarations
        val (name2) = alice
        println(name2)
        val (name3, _) = alice
        //println(_)//unresolved reference
        println(name3)
        //println((name3, _)) boom
    }

    fun deconstractInList() {
        val ll: List<Pet> = listOf(Pet("raz", 1), Pet("dwa", 2), Pet("trzy", 3))

        for ((a, b) in ll) { println("a: ${a}, b: ${b}")}

    }

    fun returnDecTest() {
        val (a, b) = returnDec()
        println("a: ${a}, b: ${b}")
    }

    fun returnDec(): Pet {
        return Pet("test", 2)
    }

    //type expected
//    fun returnDec2(): (String, Int) {
//        return ("ras", 2)
//    }

    //requeired Pet found String
    //makes sense it is return Pet.component1
//    fun returnDec2(): Pet {
//        return ("ras", 2)
//    }

    fun deconstructionOfAMap() {
        val map = hashMapOf<String, Pet>("a" to Pet("az", 2), "b" to Pet("dwa", 3))
        for ((a, b) in map) { println("a: $a, b: $b")}
        //for ((c, (d,e)) in map) { println("a: ${a}, b: ${b}")}//taki sprytny nie jest :(
    }

    fun deconstructionInLambdas() {
        val map = hashMapOf<String, Pet>("a" to Pet("az", 2), "b" to Pet("dwa", 3))
        map.mapValues { m -> print(m) }
        map.map { (a, b) -> println("a: $a, b: $b") }
        map.map { (a: String, b: Pet) -> println("a: $a, b: $b") }
        map.map { (a, b): Map.Entry<String, Pet> -> println("a: $a, b: $b") }
    }
}



data class Pet(val name: String, val age: Int)