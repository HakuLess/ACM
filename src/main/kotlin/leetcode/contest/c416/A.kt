package leetcode.contest.c416

class SolutionA {
    fun reportSpam(message: Array<String>, bannedWords: Array<String>): Boolean {
        val bannedSet = bannedWords.toSet()
        return message.count { it in bannedSet } >= 2
    }
}