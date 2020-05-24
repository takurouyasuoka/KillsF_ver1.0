package com.takurou.android.killsf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

var arriveMemberCount = 5

var passedimagebutton1 = 0
var passedimagebutton2 = 0
var passedimagebutton3 = 0
var passedimagebutton4 = 0
var passedimagebutton5 = 0

var enemyAttackPower = 0
var enemyHP = 0

var roleNumber = 0

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  相手のステータス設定
        enemyAttackPower = getString(R.string.enemyAttackPower).toInt()
        enemyHP = getString(R.string.enemyHP).toInt()

        buttonStart.setOnClickListener {
            val intent = Intent(this@MainActivity,SelectCardActivity::class.java)
            startActivity(intent)
        }
    }
}
