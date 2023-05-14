package leetcode.contest.b104

class SolutionA {
    fun countSeniors(details: Array<String>): Int {
        return details.map {
            it.takeLast(4).dropLast(2).toInt()
        }.count {
            it > 60
        }
    }
}