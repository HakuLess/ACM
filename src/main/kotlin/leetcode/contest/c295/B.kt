package leetcode.contest.c295

import utils.print

fun main() {
    val s = SolutionB()
    s.discountPrices("there are $1 $2 and 5$ candies in the shop", 50).print()
}

class SolutionB {
    fun discountPrices(sentence: String, discount: Int): String {
        return sentence.split(" ").map {
            if (it.length > 1 && it[0] == '$' && it.substring(1, it.length).all { it in '0'..'9' }) {
                "$" + String.format("%.2f", it.substring(1, it.length).toDouble() * (100 - discount) / 100)
            } else {
                it
            }
        }.joinToString(" ")
    }
}