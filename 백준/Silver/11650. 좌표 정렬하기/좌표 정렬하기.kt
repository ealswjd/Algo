
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt() // 좌표 개수
    val points = Array(n) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        x to y
    }

    // x 오름차순, x 같으면 y 오름차순 정렬
    points.sortWith(compareBy({ it.first }, { it.second }))

    val result = StringBuilder()
    for ((x, y) in points) {
        result.append("$x $y\n")
    }

    print(result)
}
