package com.TriVe.Apps.SimpleTicTacToe;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;


public class MainActivity extends ActionBarActivity implements CanvasView.CanvasEvents
{

    private CanvasView customCanvas;

    private int[] GameBoard = new int[9];
//    private boolean isPlayerTurn = false;

    private boolean isStarted = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AI.Level = 0;

        String[] levels = getResources().getStringArray(R.array.cpu_levels);

        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);

        customCanvas.DrawText("Who Start?", Color.BLUE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setDisplayShowTitleEnabled(false);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_level, R.id.spinner_text, levels);

        ActionBar.OnNavigationListener navigationListener = new ActionBar.OnNavigationListener() {

            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {

                AI.Level = (int)itemId;
                return false;
            }
        };

        /** Setting dropdown items and item navigation listener for the actionbar */
        actionBar.setListNavigationCallbacks(adapter, navigationListener);
        actionBar.setSelectedNavigationItem(0);

    }

    public void clearCanvas()
    {
        Rules.InitialiseTableau(GameBoard);
        customCanvas.clearCanvas();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.cpu_start)
        {
            CPUStart();
            return true;
        }
        else if (id == R.id.human_start)
        {
            HumanStart();
            return true;
        }
        else if (id == R.id.clearCanvas) {
            OnbtnClearCanvas();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void OnbtnClearCanvas()
    {
        clearCanvas();
        isStarted = false;
        customCanvas.DrawText("Who Start?", Color.BLUE);
    }

    public void HumanStart()
    {
//        isPlayerTurn = true;
        clearCanvas();
        isStarted = true;

        customCanvas.DrawText("It's your turn", Color.BLUE);
    }

    public void CPUStart()
    {
        customCanvas.DrawText("", Color.DKGRAY);
//        isPlayerTurn = false;
        clearCanvas();
        isStarted = true;

        int choice;
        choice = AI.ArtificialChoice(GameBoard);
        GameBoard[choice] = 10;
        customCanvas.AddCircle(choice);



    }


    @Override
    public void OnCellTouched(int cell)
    {
        int result = -1;

        if (!isStarted)
            return;

        result = Rules.Resultatcoup(GameBoard);

        customCanvas.DrawText("", Color.DKGRAY);

        if (customCanvas.AddCross(cell))
        {
            GameBoard[cell] = 1;


            if (result != -1) {
                DisplayWinner(result);
                return;
            }
        }

        int choice;
        choice = AI.ArtificialChoice(GameBoard);
        GameBoard[choice] = 10;
        customCanvas.AddCircle(choice);

        if (result != -1)
            DisplayWinner(result);

    }


    private void DisplayWinner(int result)
    {
        isStarted = false;

        if (result == 10)
            customCanvas.DrawText("CPU Win", Color.rgb(255, 25, 17));
        else if (result == 1)
            customCanvas.DrawText("YOU Win", Color.rgb(0, 162, 16));
        else if (result == 0)
            customCanvas.DrawText("Drawn...", Color.GRAY);
    }
}
