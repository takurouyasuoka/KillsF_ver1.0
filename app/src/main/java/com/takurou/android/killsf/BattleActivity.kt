package com.takurou.android.killsf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_battle.*

class BattleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)

        val bundle :Bundle? = intent.extras
        val NameTitle = bundle!!.getString("NameTitle")

        when(NameTitle){
            getString(R.string.CardName1) -> {
                imageViewMyCard.setImageResource(R.drawable.matsuken)
                editTextMyHP.setText(getString(R.string.HpStatus1))
            }
            getString(R.string.CardName2) -> {
                imageViewMyCard.setImageResource(R.drawable.yuhei)
                editTextMyHP.setText(getString(R.string.HpStatus2))
            }
            getString(R.string.CardName3) -> {
                imageViewMyCard.setImageResource(R.drawable.yasu)
                editTextMyHP.setText(getString(R.string.HpStatus3))
            }
            getString(R.string.CardName4) -> {
                imageViewMyCard.setImageResource(R.drawable.zako1)
                editTextMyHP.setText(getString(R.string.HpStatus4))
            }
            getString(R.string.CardName5) -> {
                imageViewMyCard.setImageResource(R.drawable.zako2)
                editTextMyHP.setText(getString(R.string.HpStatus5))
            }
            getString(R.string.CardName6) -> {
                imageViewMyCard.setImageResource(R.drawable.zako3)
                editTextMyHP.setText(getString(R.string.HpStatus6))
            }
        }
    }
}
