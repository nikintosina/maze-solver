import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MazeSolver {

    public static void main(String[] args) {
        String[][] maze = getMaze("maze.txt");
        List<int[]> path = new ArrayList<>();
        if (solveMaze(maze, 0, 0, path)) {
            for (int[] step : path) {
                System.out.print("(" + step[0] + ", " + step[1] + ") ---> ");
            }
            System.out.println("End");
        } else {
            System.out.println("No solution found.");
        }
    }

    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;
    }

    public static boolean solveMaze(String[][] maze, int row, int col, List<int[]> path) {
        if (row == maze.length - 1 && col == maze[0].length - 1) {
            path.add(new int[]{row, col});
            return true;
        }

        if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length || maze[row][col].equals("#")) {
            return false;
        }

        maze[row][col] = "#";
        path.add(new int[]{row, col});

        if (solveMaze(maze, row + 1, col, path) || solveMaze(maze, row, col + 1, path) ||
                solveMaze(maze, row - 1, col, path) || solveMaze(maze, row, col - 1, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        maze[row][col] = ".";
        return false;
    }
}
