package leetcode.contest.c420

class SolutionA {
    fun stringSequence(target: String): List<String> {
        val result = mutableListOf<String>()
        var current = 'a'
        val screen = StringBuilder()

        for (char in target) {
            screen.append('a')
            result.add(screen.toString())

            while (screen.last() != char) {
                current = if (current == 'z') 'a' else current + 1
                screen.setCharAt(screen.length - 1, current)
                result.add(screen.toString())
            }

            current = 'a'
        }

        return result
    }
}