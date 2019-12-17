package com.example.piskvorky;

import android.util.Log;

public class Logic {

  private int ROWS;
  private int COLS;

  private int[][] board;
  private int nextPlayer;
  private int moves;

  public Logic(int Rows, int Cols) {
    this.ROWS = Rows;
    this.COLS = Cols;
    this.board = new int[this.ROWS][this.COLS];
    for(int i=0;i<this.ROWS;i++)
    {
      for (int j = 0; j < this.COLS; j++) {
        this.board[i][j] = 0;
      }
    }

    this.nextPlayer = 1;
    this.moves = 0;

  }

  public int nextPlayer() {
    return this.nextPlayer;
  }

  public int[][] getBoard()
  {
    return board;
  }

  public int getMoves() {
    return this.moves;
  }

  public int checkWinner()
  {
    int HEIGHT = ROWS;
    int WIDTH = COLS;

    for (int r = 0; r < HEIGHT; r++) {
      for (int c = 0; c < WIDTH; c++) {
        int player = board[r][c];
        if (player == 0)
          continue;

        if (c + 4 < WIDTH &&
                player == board[r][c+1] &&
                player == board[r][c+2] &&
                player == board[r][c+3] &&
                player == board[r][c+4])
          return player;
        if (r + 4 < HEIGHT) {
          if (player == board[r+1][c] &&
                  player == board[r+2][c] &&
                  player == board[r+3][c] &&
                  player == board[r+4][c] )
            return player;
          if (c + 4 < WIDTH &&
                  player == board[r+1][c+1] &&
                  player == board[r+2][c+2] &&
                  player == board[r+3][c+3]&&
                  player == board[r+4][c+4])
            return player;
          if (c - 4 >= 0 &&
                  player == board[r+1][c-1] &&
                  player == board[r+2][c-2] &&
                  player == board[r+3][c-3] &&
                  player == board[r+4][c-4]
          )
            return player;
        }
      }
    }
    if (this.moves == this.ROWS * this.COLS) return -1;
    return 0;
  }

  public int getCurrentState(int row, int col) {
    return this.board[row][col];
  }


  public void move(int row, int col) {
    assert this.board[row][col] == 0;
    this.board[row][col] = this.nextPlayer;
    this.nextPlayer = (this.nextPlayer() == 1 ? 2 : 1);
    this.moves += 1;
  }

}