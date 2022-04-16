package leetcode.lccup.round2021.spring.solo

class SolutionA {
    fun giveGem(gem: IntArray, operations: Array<IntArray>): Int {
        operations.forEach {
            val x = gem[it[0]] / 2
            gem[it[0]] -= x
            gem[it[1]] += x
        }
//        return gem.max()!! - gem.min()!!
        return gem.maxOrNull()!!- gem.minOrNull()!!
    }
}