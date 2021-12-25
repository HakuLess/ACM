package leetcode.contest.b68

class SolutionB {
    fun findAllRecipes(recipes: Array<String>, ingredients: List<List<String>>, supplies: Array<String>): List<String> {

        val sup = HashSet<String>()
        sup.addAll(supplies)

        val ans = HashSet<String>()
        var changed = false
        val add = BooleanArray(recipes.size) { false }
        do {
            changed = false
            for (i in ingredients.indices) {
                if (add[i]) continue
                if (ingredients[i].all { it in sup }) {
                    ans.add(recipes[i])
                    sup.add(recipes[i])
                    changed = true
                    add[i] = true
                }
            }
        } while (changed)

        return ans.toList()
    }
}