package leetcode.contest.c452

import utils.dir4
import utils.print
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
//    s.minMoves(arrayOf("S.", "XL"), 2).print()
//    s.minMoves(arrayOf("LS", "RL"), 4).print()
//    s.minMoves(arrayOf("L.S", "RXL"), 3).print()
    s.minMoves(arrayOf("SRXL", "LLR.", "XRRL"), 3).print()
}

class SolutionC {
    fun minMoves(classroom: Array<String>, energy: Int): Int {

        val m = classroom.size
        val n = classroom[0].length

        data class State(val row: Int, val col: Int, val energy: Int, val mask: Int, val steps: Int)
        data class Key(val row: Int, val col: Int, val mask: Int)

        val garbageMap = mutableMapOf<Pair<Int, Int>, Int>()
        var startRow = -1
        var startCol = -1
        var id = 0

        for (i in 0 until m) {
            for (j in 0 until n) {
                when (classroom[i][j]) {
                    'S' -> {
                        startRow = i
                        startCol = j
                    }
                    'L' -> {
                        garbageMap[Pair(i, j)] = id++
                    }
                }
            }
        }

        val totalMask = (1 shl garbageMap.size) - 1

        val queue: Queue<State> = LinkedList()
        val visited = HashSet<Key>()

        val start = State(startRow, startCol, energy, 0, 0)
        val startKey = Key(startRow, startCol, 0)
        queue.offer(start)
        visited.add(startKey)

        val energyMap = HashMap<Key, Int>()

        while (queue.isNotEmpty()) {
//            println("state: ${queue.peek()}")
            val (row, col, curEnergy, mask, steps) = queue.poll()

            if (mask == totalMask) return steps

            for ((dr, dc) in dir4) {
                val nextRow = row + dr
                val nextCol = col + dc
                var nextEnergy = curEnergy - 1
                var nextMask = mask

                if (nextRow !in 0 until m || nextCol !in 0 until n) continue
                val cell = classroom[nextRow][nextCol]
                if (cell == 'X') continue

                if (nextEnergy < 0 && classroom[row][col] != 'R') continue
                if (cell == 'R') nextEnergy = energy
                if (cell == 'L') {
                    garbageMap[Pair(nextRow, nextCol)]?.let {
                        nextMask = mask or (1 shl it)
                    }
                }

                val key = Key(nextRow, nextCol, nextMask)
                if (key !in visited || (nextEnergy > energyMap.getOrDefault(key, -1))) {
//                if (key !in visited) {
                    energyMap[key] = nextEnergy
                    visited.add(key)
                    queue.offer(State(nextRow, nextCol, nextEnergy, nextMask, steps + 1))
                }
            }
        }

        return -1
    }
}