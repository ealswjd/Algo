// https://www.acmicpc.net/problem/1388

fun main() = with(System.`in`.bufferedReader()) {
    // 세로 크기N과 가로 크기 M
    val (n, m) = readLine()!!
        .split(' ').map { it.toInt() }
    // 기훈이 방
    val map: Array<CharArray> = Array(n) {
        readLine().trim().toCharArray()
    }
    val visited = Array(n) {
        BooleanArray(m)
    }

    val dr = intArrayOf(1, 0)
    val dc = intArrayOf(0, 1)
    val h = 0
    val w = 1
    var totalCnt = 0

    fun dfs(r: Int, c: Int, d: Int) {
        val nr = r + dr[d]
        val nc = c + dc[d]
        if(check(n, m, nr, nc)) return
        if(visited[nr][nc] || map[r][c] != map[nr][nc]) return

        visited[nr][nc] = true
        dfs(nr, nc, d)
    }

    var d = h
    for (r in 0 until n) {
        for(c in 0 until m) {
            if (visited[r][c]) continue

            d = if (map[r][c] == '-') w
            else h

            visited[r][c] = true
            dfs(r, c, d)
            totalCnt++
        }
    }

    print(totalCnt)
    close()
}

fun check(n: Int, m: Int, r: Int, c: Int): Boolean {
    return r >= n || c >= m
}
