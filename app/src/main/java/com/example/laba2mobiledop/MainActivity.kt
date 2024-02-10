package com.example.laba2mobiledop

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var x : Int = 8
    var y : Int = 1
    var score : Int = 0

    private lateinit var map : Array<Array<ImageView>>
    private lateinit var mas : Array<Array<Int>>
    private lateinit var mas2 : Array<Array<Int>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        map = Array(3) { Array(3) { findViewById(R.id.imageView1) } }
        map[0] = arrayOf(findViewById(R.id.imageView1), findViewById(R.id.imageView2), findViewById(R.id.imageView3))     // первая строка
        map[1] = arrayOf(findViewById(R.id.imageView4), findViewById(R.id.imageView5), findViewById(R.id.imageView6))     // вторая строка
        map[2] = arrayOf(findViewById(R.id.imageView7), findViewById(R.id.imageView8), findViewById(R.id.imageView9))     // третья строка
/*
        mas    = Array(10) { Array(10) { 0 } }
        mas[0] = arrayOf(1,1,1,1,1,1,1,1,0,0)
        mas[1] = arrayOf(1,1,1,1,1,1,1,0,0,0)
        mas[2] = arrayOf(1,1,1,1,1,1,0,0,1,1)
        mas[3] = arrayOf(1,1,1,1,1,0,0,1,1,1)
        mas[4] = arrayOf(1,1,1,1,1,0,1,1,1,1)
        mas[5] = arrayOf(1,0,0,0,0,0,1,1,1,1)
        mas[6] = arrayOf(1,0,1,1,0,1,1,1,1,1)
        mas[7] = arrayOf(1,0,1,1,0,1,1,1,1,1)
        mas[8] = arrayOf(1,0,0,0,0,0,0,0,1,1)
        mas[9] = arrayOf(1,1,1,1,1,1,1,1,1,1) */

        mas = Array(10){Array(10) { Random.nextInt(2)} }

        refresh()

    }
    @SuppressLint("SetTextI18n")
    fun refresh(){
        val coordsT : TextView = findViewById(R.id.coord)
        val scoreT : TextView = findViewById(R.id.scoreText)
        var text : String = ""
        for(row in 0..9){
            for(i in 0..9){
                if (row == x && i == y)
                    text += "x"
                else
                    text += mas[row][i]
                text += " "
            }
            text += "\n"
        }
        coordsT.text = text

        val xMap : Int = x-1
        val yMap : Int = y-1

        println("$xMap  $yMap")
        println("$x  $y")

        for(j in 0..2){
            for(i in 0..2){
                if(mas[xMap + i][yMap+j] == 0)
                    map[i][j].setImageResource(R.drawable.clean_way_background)
                else
                    map[i][j].setImageResource(R.drawable.wall_background)
            }
        }
        map[1][1].setImageResource(R.drawable.player_background)
        scoreT.text = "Счет\n" + score.toString()
    }

    fun pressOnButton(v: View){
        val bUp : Button = findViewById(R.id.buttonUp)
        val bDown : Button = findViewById(R.id.buttonDown)
        val bRight : Button = findViewById(R.id.buttonRight)
        val bLeft : Button = findViewById(R.id.buttonLeft)

        if (v.id == bUp.id && x > 1 && mas[x-1][y] !=1){
            x--
            score++
        }
        if (v.id == bDown.id && x <= 7 && mas[x+1][y] !=1){
            x++
            score++
        }
        if (v.id == bLeft.id && y > 1 && mas[x][y-1] !=1){
            y--
            score++
        }
        if (v.id == bRight.id && y <= 7 && mas[x][y+1] !=1){
            y++
            score++
        }

        refresh()
    }
}