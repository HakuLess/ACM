package leetcode.lccup.round2021.spring.team

import utils.dir4
import utils.print

fun main() {
    val s = SolutionD()
    s.composeCube(
        arrayOf(
            arrayOf("000", "110", "000"),
            arrayOf("110", "011", "000"),
            arrayOf("110", "011", "110"),
            arrayOf("000", "010", "111"),
            arrayOf("011", "111", "011"),
            arrayOf("011", "010", "000")
        )
    ).print()

//    s.composeCube(
//        arrayOf(
//            arrayOf("101", "111", "000"),
//            arrayOf("000", "010", "111"),
//            arrayOf("010", "011", "000"),
//            arrayOf("010", "111", "010"),
//            arrayOf("101", "111", "010"),
//            arrayOf("000", "010", "011")
//        )
//    ).print()

//    val cube = Cube(3)
//    for (i in 0 until 6) {
//        cube.getFace(i).print()
//    }
}

class SolutionD {
    fun composeCube(shapes: Array<Array<String>>): Boolean {
        val n = shapes[0].size
        val cube = Cube(n)
        var ans = false

        var c = 0

        // 当前状态
        fun dfs(cube: Cube, i: Int) {
            if (ans) {
                return
            }

            c++
            println("$c $i ${cube.count()}")

            if (i !in shapes.indices) {
                if (cube.isAll()) {
                    println("meet $i")
                    ans = true
                }
                return
            }
            val item = shapes[i]

            for (j in 0 until 6) {
                for (t in 0 until 4) {
                    val next = cube.insert(j, item, t)
                    if (next != null) {
                        dfs(next, i + 1)
                    }
                }
            }
        }

        dfs(cube, 0)
        return ans
    }
}

// 正方体，边长为n
class Cube(val n: Int) {
    val cube = Array<Array<IntArray>>(n) { Array(n) { IntArray(n) { 0 } } }

    //    init {
//        var c = 0
//        for (i in 0 until n) {
//            for (j in 0 until n) {
//                for (k in 0 until n) {
//                    cube[i][j][k] = c++
//                }
//            }
//        }
//    }
    fun count(): Int {
        var c = 0
        for (i in 0 until n) {
            for (j in 0 until n) {
                for (k in 0 until n) {
                    if (cube[i][j][k] == 1) c++
                }
            }
        }
        return c
    }

    fun clone(): Cube {
        val newCube = Cube(n)
        for (i in 0 until n) {
            for (j in 0 until n) {
                for (k in 0 until n) {
                    newCube.cube[i][j][k] = this.cube[i][j][k]
                }
            }
        }
        return newCube
    }

    fun isAll(): Boolean {
        for (i in 0 until n) {
            for (j in 0 until n) {
                for (k in 0 until n) {
                    if (cube[i][j][k] == 0) {
                        return false
                    }
                }
            }
        }
        return true
    }

    fun insert(type: Int, item: Array<String>, t: Int): Cube? {
        val l = ArrayList<IntArray>()
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (item[i][j] == '1') {
                    when (t) {
                        0 -> l.add(intArrayOf(i, j))
                        1 -> l.add(intArrayOf(n - i, j))
                        2 -> l.add(intArrayOf(i, n - j))
                        3 -> l.add(intArrayOf(n - i, n - j))
                    }
                }
            }
        }
        if (l.all { it[0] in 0 until n && it[1] in 0 until n }) {
            val face = getFace(type)
            if (l.all { face[it[0]][it[1]] == 0 }) {
                val newCube = clone()
                l.forEach {
                    newCube.setFace(type, it[0], it[1], 1)
                }
                return newCube
            } else {
                return null
            }
        }
        return null
    }

    fun setFace(type: Int, i: Int, j: Int, v: Int) {
        when (type) {
            0 -> {
                cube[0][i][j] = v
            }
            1 -> {
                cube[i][0][j] = v
            }
            2 -> {
                cube[i][j][0] = v
            }
            3 -> {
                cube[i][n - 1][j] = v
            }
            4 -> {
                cube[i][j][n - 1] = v
            }
            5 -> {
                cube[n - 1][i][j] = v
            }
        }
    }

    // 面
    // 0 底部
    // 1 2 3 4 前后左右
    // 5 顶部
    fun getFace(type: Int): Array<IntArray> {
        val ans = Array<IntArray>(n) { IntArray(n) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                when (type) {
                    0 -> {
                        ans[i][j] = cube[0][i][j]
                    }
                    1 -> {
                        ans[i][j] = cube[i][0][j]
                    }
                    2 -> {
                        ans[i][j] = cube[i][j][0]
                    }
                    3 -> {
                        ans[i][j] = cube[i][n - 1][j]
                    }
                    4 -> {
                        ans[i][j] = cube[i][j][n - 1]
                    }
                    5 -> {
                        ans[i][j] = cube[n - 1][i][j]
                    }
                }
            }
        }
        return ans
    }

    fun print() {
        println("Start----")
        for (i in 0 until 6) {
            getFace(i).print()
        }
        println("End----")
    }
}