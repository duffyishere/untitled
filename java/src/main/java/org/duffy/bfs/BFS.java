package org.duffy.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    private int length, width;
    private int[][] visited;
    private int[][] grid;
    public int[] dx = new int[] {0, -1, 0, 1};
    public int[] dy = new int[] {1, 0, -1, 0};
    public Queue<Integer[]> queue = new LinkedList<>();


    public void dfs(int x, int y) {
        for (int i=0; i<4; i++) {
            int mx = dx[i] + x;
            int my = dy[i] + y;

            if (mx < 0 || my < 0 || length <= mx || width <= my) continue;
            if (visited[mx][my] > 0) continue;
            if (grid[mx][my] == 0) continue;

            visited[mx][my] = visited[x][y] + 1;
            dfs(mx, my);
        }
    }


    public void pr2178() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = br.readLine().split(" ");
        length = Integer.parseInt(buffer[0]);
        width = Integer.parseInt(buffer[1]);
        grid = new int[length][width];
        visited = new int[length][width];

        for (int i = 0; i < length; i++) {
            String[] line = br.readLine().trim().split("");
            for (int j = 0; j < width; j++) {
                grid[i][j] = Integer.parseInt(line[j]);
            }
        }

        visited[0][0] = 1;
        queue.add(new Integer[]{0, 0});
        while (queue.size() > 0) {
            Integer[] bfr = queue.poll();
            int x = bfr[0];
            int y = bfr[1];

            for (int i=0; i<4; i++) {
                int mx = x + dx[i];
                int my = y + dy[i];

                if (mx < 0 || my < 0 || width-1 < mx || length-1 < my) continue;
                if (0 < visited[my][mx]) continue;
                if (grid[my][mx] == 0) continue;

                visited[my][mx] = visited[y][x] + 1;
                queue.add(new Integer[]{mx, my});
            }
        }

        System.out.println(visited[length-1][width-1]);
    }
    public int height;
    public List<Integer[]> viruses = new ArrayList<>();

    public void pr14502() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = br.readLine().split(" ");
        height = Integer.parseInt(buffer[0]);
        width = Integer.parseInt(buffer[1]);
        grid = new int[height][width];

        List<Integer[]> walls = new ArrayList<>();
        for (int i = 0; i <height; i++) {
            buffer = br.readLine().split(" ");
            for (int j = 0; j < width; j++) {
                int num = Integer.parseInt(buffer[j]);
                grid[i][j] = num;
                if (num == 0) walls.add(new Integer[]{i, j});
                else if (num == 2) viruses.add(new Integer[]{i, j});
            }
        }

        // todo: 임의의 벽 3개를 세우는 코드
        int ret = 0;
        for (int i = 0; i < walls.size(); i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    grid[walls.get(i)[0]][walls.get(i)[1]] = 1;
                    grid[walls.get(j)[0]][walls.get(j)[1]] = 1;
                    grid[walls.get(k)[0]][walls.get(k)[1]] = 1;
                    ret = Math.max(ret, solve());
                    grid[walls.get(i)[0]][walls.get(i)[1]] = 0;
                    grid[walls.get(j)[0]][walls.get(j)[1]] = 0;
                    grid[walls.get(k)[0]][walls.get(k)[1]] = 0;
                }
            }
        }
        System.out.println(ret);
    }

    public int solve() {
        visited = new int[height][width];
        for (Integer[] virus: viruses) {
            visited[virus[0]][virus[1]] = 1;
            dfs(virus[0], virus[1]);
        }
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == 0 && visited[i][j] == 0)
                    count++;
            }
        }
        return count;
    }
}
