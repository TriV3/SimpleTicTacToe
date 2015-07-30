package com.TriVe.Apps.SimpleTicTacToe;

public class Rules
{

    static int QuiGagne(int Tableau[])
    {
        // Première ligne.
        if ((Tableau[0] + Tableau[1] + Tableau[2]) == 3)
            return 1;
        else if ((Tableau[0] + Tableau[1] + Tableau[2]) == 30)
            return 10;
        // Deuxième ligne.
        if ((Tableau[3] + Tableau[4] + Tableau[5]) == 3)
            return 1;
        else if ((Tableau[3] + Tableau[4] + Tableau[5]) == 30)
            return 10;
        // Troisième ligne.
        if ((Tableau[6] + Tableau[7] + Tableau[8]) == 3)
            return 1;
        else if ((Tableau[6] + Tableau[7] + Tableau[8]) == 30)
            return 10;

        // Première colonne.
        if ((Tableau[0] + Tableau[3] + Tableau[6]) == 3)
            return 1;
        else if ((Tableau[0] + Tableau[3] + Tableau[6]) == 30)
            return 10;
        // Deuxième colonne.
        if ((Tableau[1] + Tableau[4] + Tableau[7]) == 3)
            return 1;
        else if ((Tableau[1] + Tableau[4] + Tableau[7]) == 30)
            return 10;
        // Troisième colonne.
        if ((Tableau[2] + Tableau[5] + Tableau[8]) == 3)
            return 1;
        else if ((Tableau[2] + Tableau[5] + Tableau[8]) == 30)
            return 10;

        // Diagonale 1.
        if ((Tableau[0] + Tableau[4] + Tableau[8]) == 3)
            return 1;
        else if ((Tableau[0] + Tableau[4] + Tableau[8]) == 30)
            return 10;
        // Diagonale 2.
        if ((Tableau[2] + Tableau[4] + Tableau[6]) == 3)
            return 1;
        else if ((Tableau[2] + Tableau[4] + Tableau[6]) == 30)
            return 10;

        return -1;
    }


    static  int CasesVides(int Tableau[])
    {
        int nbcasesvides = 0;
        for (int i = 0; i < 9; i++)
        {
            if ((Tableau[i] != 1) && (Tableau[i] != 10))
            {
                nbcasesvides++;
            }

        }
        return nbcasesvides;
    }

    static int Resultatcoup(int Tableau[])
    {
        if (CasesVides(Tableau) == 0)
            return 0;
        return QuiGagne(Tableau);
    }


    static void InitialiseTableau(int Tableau[])
    {
        for (int i = 0; i < 9; i++)
        {
            Tableau[i] = 0;
        }
    }

}
