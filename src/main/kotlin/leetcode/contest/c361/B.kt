package leetcode.contest.c361

class SolutionB {
    fun minimumOperations(num: String): Int {
        var meet0 = false
        var meet5 = false
        // 00 25 50 75
        var index = 0
        var offset = 0
        for (i in num.indices.reversed()) {
            when (num[i]) {
                '0' -> {
                    offset = 1
                    if (meet0) {
                        index = i
                        offset = 2
                        break
                    }
                    meet0 = true
                }
                '5' -> {
                    if (meet0) {
                        index = i
                        offset = 2
                        break
                    }
                    meet5 = true
                }
                '2' -> {
                    if (meet5) {
                        index = i
                        offset = 2
                        break
                    }
                }
                '7' -> {
                    if (meet5) {
                        index = i
                        offset = 2
                        break
                    }
                }
                else -> {

                }
            }
        }
        return num.length - index - offset
    }
}