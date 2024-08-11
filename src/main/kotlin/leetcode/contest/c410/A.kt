package leetcode.contest.c410

class SolutionA {
    fun finalPositionOfSnake(n: Int, commands: List<String>): Int {
        var x = 0
        var y = 0

        for (command in commands) {
            when (command) {
                "UP" -> x -= 1
                "DOWN" -> x += 1
                "LEFT" -> y -= 1
                "RIGHT" -> y += 1
            }
        }

        return x * n + y
    }
}