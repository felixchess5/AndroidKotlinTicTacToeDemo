package com.olivo.tictactoedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buClick(view: View) {
        val buSelected = view as Button

        var cellId =0
        when(buSelected.id) {
            R.id.bu11 -> cellId = 1
            R.id.bu12 -> cellId = 2
            R.id.bu13 -> cellId = 3
            R.id.bu21 -> cellId = 4
            R.id.bu22 -> cellId = 5
            R.id.bu23 -> cellId = 6
            R.id.bu31 -> cellId = 7
            R.id.bu32 -> cellId = 8
            R.id.bu33 -> cellId = 9
        }

        Log.d("buClick:",buSelected.id.toString())
        Log.d("buClick cellId:",cellId.toString())

        playGame(cellId, buSelected)

    }

    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()


    fun playGame(cellId:Int, buSelected:Button) {

        if(activePlayer == 1) {
            buSelected.text = "X"
            buSelected.setBackgroundResource(R.color.player1color)
            player1.add(cellId)
            activePlayer = 2

            autoPlay()

        } else {
            buSelected.text = "O"
            buSelected.setBackgroundResource(R.color.player2color)
            player2.add(cellId)
            activePlayer = 1
        }

        buSelected.isEnabled = false

        checkWinner()
    }

    fun checkWinner() {
        var winner = -1

        // First Row
        if(player1.contains(1) && player1.contains(2) && player1.contains(3))
            winner = 1

        if(player2.contains(1) && player2.contains(2) && player2.contains(3))
            winner = 2

        // Second Row
        if(player1.contains(4) && player1.contains(5) && player1.contains(6))
            winner = 1

        if(player2.contains(4) && player2.contains(5) && player2.contains(6))
            winner = 2

        // Third Row
        if(player1.contains(7) && player1.contains(8) && player1.contains(9))
            winner = 1

        if(player2.contains(7) && player2.contains(8) && player2.contains(9))
            winner = 2

        // First Column
        if(player1.contains(1) && player1.contains(4) && player1.contains(7))
            winner = 1

        if(player2.contains(1) && player2.contains(4) && player2.contains(7))
            winner = 2

        // Second Column
        if(player1.contains(2) && player1.contains(5) && player1.contains(8))
            winner = 1

        if(player2.contains(2) && player2.contains(5) && player2.contains(8))
            winner = 2

        // Third Column
        if(player1.contains(3) && player1.contains(6) && player1.contains(9))
            winner = 1

        if(player2.contains(3) && player2.contains(6) && player2.contains(9))
            winner = 2

        // First Diagonal
        if(player1.contains(1) && player1.contains(5) && player1.contains(9))
            winner = 1

        if(player2.contains(1) && player2.contains(5) && player2.contains(9))
            winner = 2

        // Second Diagonal
        if(player1.contains(3) && player1.contains(5) && player1.contains(7))
            winner = 1

        if(player2.contains(3) && player2.contains(5) && player2.contains(7))
            winner = 2

        if(winner > 0) {
            Toast.makeText(this, "Player $winner wins the game", Toast.LENGTH_SHORT).show()
        }
    }

    fun autoPlay() {

        var emptyCells = ArrayList<Int>()
        for( cellId in 1..9) {
            if(!(player1.contains(cellId) || player2.contains(cellId))) {
                emptyCells.add(cellId)
            }
        }

        val r = Random()
        val randIndex = r.nextInt(emptyCells.size)
        val cellId = emptyCells[randIndex]
        var buSelected:Button?
        buSelected = when(cellId) {
            1 -> bu11
            2 -> bu12
            3 -> bu13
            4 -> bu21
            5 -> bu22
            6 -> bu23
            7 -> bu31
            8 -> bu32
            9 -> bu33
            else -> {bu11}
        }

        playGame(cellId, buSelected)
    }
}


