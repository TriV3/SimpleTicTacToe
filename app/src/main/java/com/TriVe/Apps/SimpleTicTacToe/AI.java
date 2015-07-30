package com.TriVe.Apps.SimpleTicTacToe;

import android.util.Log;

import java.util.Random;

public class AI
{
    // Variables globales
    static int SumRow1, SumRow2, SumRow3, SumColumn1, SumColumn2, SumColumn3;
    static int SumDia1, SumDia2, SumGame, MyMove, Level;

    static void SpotInRow (int x, int Game[])
    {
        // find an empty spot in this row
        if ((x == 1) && (Game[0] == 0))MyMove = 0;
        if ((x == 1) && (Game[1] == 0))MyMove = 1;
        if ((x == 1) && (Game[2] == 0))MyMove = 2;
        if ((x == 2) && (Game[3] == 0))MyMove = 3;
        if ((x == 2) && (Game[4] == 0))MyMove = 4;
        if ((x == 2) && (Game[5] == 0))MyMove = 5;
        if ((x == 3) && (Game[6] == 0))MyMove = 6;
        if ((x == 3) && (Game[7] == 0))MyMove = 7;
        if ((x == 3) && (Game[8] == 0))MyMove = 8;
    }

    static void SpotInColumn (int x, int Game[])
    {
        // find an exmpty spot in this column
        if ((x == 1) && (Game[0] == 0)) MyMove = 0;
        if ((x == 1) && (Game[3] == 0)) MyMove = 3;
        if ((x == 1) && (Game[6] == 0)) MyMove = 6;
        if ((x == 2) && (Game[1] == 0)) MyMove = 1;
        if ((x == 2) && (Game[4] == 0)) MyMove = 4;
        if ((x == 2) && (Game[7] == 0)) MyMove = 7;
        if ((x == 3) && (Game[2] == 0)) MyMove = 2;
        if ((x == 3) && (Game[5] == 0)) MyMove = 5;
        if ((x == 3) && (Game[8] == 0)) MyMove = 8;
    }

    static void SpotInDia (int x, int Game[])
    {
        // find an empty spot in this diagonal
        if ((x == 1) && (Game[0] == 0)) MyMove = 0;
        if ((x == 1) && (Game[4] == 0)) MyMove = 4;
        if ((x == 1) && (Game[8] == 0)) MyMove = 8;
        if ((x == 2) && (Game[2] == 0)) MyMove = 2;
        if ((x == 2) && (Game[4] == 0)) MyMove = 4;
        if ((x == 2) && (Game[6] == 0)) MyMove = 6;
    }

    static int ArtificialChoice(int Game[])
    {
        // Levels:
        // INSANE = 0, Master = 1, Casual = 2, Novice = 3

        // assign values to the variables the rules will uses
        SumRow1 = Game[0] + Game[1] + Game[2];
        SumRow2 = Game[3] + Game[4] + Game[5];
        SumRow3 = Game[6] + Game[7] + Game[8];
        SumColumn1 = Game[0] + Game[3] + Game[6];
        SumColumn2 = Game[1] + Game[4] + Game[7];
        SumColumn3 = Game[2] + Game[5] + Game[8];
        SumDia1 = Game[0] + Game[4] + Game[8];
        SumDia2 = Game[2] + Game[4] + Game[6];
        SumGame = SumRow1 + SumRow2 + SumRow3;

        MyMove = 100;

        // INSANE = 0, Master = 1, Casual = 2
        // step one: Can I win
        if (SumRow1 == 20) SpotInRow(1, Game);
        if (SumRow2 == 20) SpotInRow(2, Game);
        if (SumRow3 == 20) SpotInRow(3, Game);
        if (SumColumn1 == 20) SpotInColumn(1, Game);
        if (SumColumn2 == 20) SpotInColumn(2, Game);
        if (SumColumn3 == 20) SpotInColumn(3, Game);
        if (SumDia1 == 20) SpotInDia(1, Game);
        if (SumDia2 == 20) SpotInDia(2, Game);

        if (MyMove != 100) Log.v("AI : ", "1) I Can Win");

        // INSANE = 0, Master = 1, Casual = 2
        // step two: Can you win
        if (MyMove == 100 && Level <= 2)
        {
            if (SumRow1 == 2) SpotInRow(1, Game);
            if (SumRow2 == 2) SpotInRow(2, Game);
            if (SumRow3 == 2) SpotInRow(3, Game);
            if (SumColumn1 == 2) SpotInColumn(1, Game);
            if (SumColumn2 == 2) SpotInColumn(2, Game);
            if (SumColumn3 == 2) SpotInColumn(3, Game);
            if (SumDia1 == 2) SpotInDia(1, Game);
            if (SumDia2 == 2) SpotInDia(2, Game);

            if (MyMove != 100) Log.v("AI : ", "2) You Can Win");
        }

        // INSANE = 0
        // step three: Can I force a double chance to win
        if (MyMove == 100 && Level <= 0)
        {
            if ((SumRow1 + SumColumn1 == 20) && (Game[0] == 0)) MyMove = 0;
            if ((SumRow1 + SumColumn2 == 20) && (Game[1] == 0)) MyMove = 1;
            if ((SumRow1 + SumColumn3 == 20) && (Game[2] == 0)) MyMove = 2;
            if ((SumRow2 + SumColumn1 == 20) && (Game[3] == 0)) MyMove = 3;
            if ((SumRow2 + SumColumn2 == 20) && (Game[4] == 0)) MyMove = 4;
            if ((SumRow2 + SumColumn3 == 20) && (Game[5] == 0)) MyMove = 5;
            if ((SumRow3 + SumColumn1 == 20) && (Game[6] == 0)) MyMove = 6;
            if ((SumRow3 + SumColumn2 == 20) && (Game[7] == 0)) MyMove = 7;
            if ((SumRow3 + SumColumn3 == 20) && (Game[8] == 0)) MyMove = 8;
            if ((SumColumn1 + SumDia1 == 20) && (Game[0] == 0)) MyMove = 0;
            if ((SumColumn2 + SumDia1 == 20) && (Game[4] == 0)) MyMove = 4;
            if ((SumColumn3 + SumDia1 == 20) && (Game[8] == 0)) MyMove = 8;
            if ((SumColumn1 + SumDia2 == 20) && (Game[6] == 0)) MyMove = 6;
            if ((SumColumn2 + SumDia2 == 20) && (Game[4] == 0)) MyMove = 4;
            if ((SumColumn3 + SumDia2 == 20) && (Game[2] == 0)) MyMove = 2;
            if ((SumRow1 + SumDia1 == 20) && (Game[0] == 0)) MyMove = 0;
            if ((SumRow2 + SumDia1 == 20) && (Game[4] == 0)) MyMove = 4;
            if ((SumRow3 + SumDia1 == 20) && (Game[8] == 0)) MyMove = 8;
            if ((SumRow1 + SumDia2 == 20) && (Game[2] == 0)) MyMove = 2;
            if ((SumRow2 + SumDia2 == 20) && (Game[4] == 0)) MyMove = 4;
            if ((SumRow3 + SumDia2 == 20) && (Game[6] == 0)) MyMove = 6;

            if (MyMove != 100) Log.v("AI : ", "3) Force double chance.");
        }

        // INSANE = 0
        // step four: if I have a piece in the center & the opponent has two on a single
        // diagonal chose once of the four mid square in the rows & columns on the outside
        if (MyMove == 100 && Level <= 0)
        {
            if ((SumGame == 12) && (SumDia1 == 12) && (Game[4] == 10))
                MyMove = 3;
            if ((SumGame == 12) && (SumDia2 == 12) && (Game[4] == 10))
                MyMove = 3;

            if (MyMove != 100) Log.v("AI : ", "4) ...");
        }

        // INSANE = 0, Master = 1
        // step 5: if the middle is empty then take it
        if (MyMove == 100 && Level <= 1)
            if (Game[4] == 0)
            {
                MyMove = 4;
                Log.v("AI : ", "5) In the middle");
            }


        // INSANE = 0
        // step 6: if one of the corners is empty then take it
        if (MyMove == 100 && Level <= 0)
        {
            if (Game[0] == 0) MyMove = 0;
            if (Game[2] == 0) MyMove = 2;
            if (Game[6] == 0) MyMove = 6;
            if (Game[8] == 0) MyMove = 8;

            if (MyMove != 100) Log.v("AI : ", "6) In one of the corners.");
        }

        // INSANE = 0, Master = 1, Casual = 2, Novice = 3
        // step 7: Just pick any free spot
        if (MyMove == 100)
        {
            do
            {
                int min = 0;
                int max = 8;
                /* generate random number from 0 to 8: */
                Random r = new Random();
                MyMove = r.nextInt(max - min + 1) + min;

            }while (Game[MyMove] != 0);

            if (MyMove != 100) Log.v("AI : ", "7) Random.");
        }

        return MyMove;
    }

}
