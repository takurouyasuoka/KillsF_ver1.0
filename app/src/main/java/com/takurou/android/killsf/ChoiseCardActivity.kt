package com.takurou.android.killsf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_choise_card.*
import kotlinx.android.synthetic.main.activity_select_card.*

class ChoiseCardActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var selectedItemPosition :MutableList<Int>
    var selectedItemCount :Int = 0
    var NameTitle = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choise_card)

        // todo 1.intentの受領
        val bundle = intent.extras

        selectedItemPosition = bundle?.getIntArray("selectedItemPosition")!!.toMutableList()
        selectedItemCount = selectedItemPosition.count()

        // todo 2.画像表示
        statusSet()

        // todo 5.Buttonクリック時の処理
        imageButton1.setOnClickListener(this)
        imageButton2.setOnClickListener(this)
        imageButton3.setOnClickListener(this)
        imageButton4.setOnClickListener(this)
        imageButton5.setOnClickListener(this)

    }

    private fun statusSet() {
        // リストから選択したデータの設定
        var setcard1SW = 0
        var setcard2SW = 0
        var setcard3SW = 0
        var setcard4SW = 0
        var setcard5SW = 0
        var setcard6SW = 0

        // １枚目のデータ設定
        var setcardSW = 0
        for (i in 0 .. selectedItemCount - 1){
            if (setcardSW == 1){
                break
            }
            when(selectedItemPosition[i]){
                0 -> {
                    if (setcard1SW == 0){
                        // カード「マツケン」
                        imageButton1.setImageResource(R.drawable.matsuken)
                        textViewCardTitle1.setText(getString(R.string.CardName1))
                        setcard1SW = 1
                        setcardSW = 1
                    }
                }
                1 -> {
                    if (setcard2SW == 0){
                        // カード「ゆーへい」
                        imageButton1.setImageResource(R.drawable.yuhei)
                        textViewCardTitle1.setText(getString(R.string.CardName2))
                        setcard2SW = 1
                        setcardSW = 1
                    }
                }
                2 -> {
                    if (setcard3SW == 0){
                        // カード「やす」
                        imageButton1.setImageResource(R.drawable.yasu)
                        textViewCardTitle1.setText(getString(R.string.CardName3))
                        setcard3SW = 1
                        setcardSW = 1
                    }
                }
                3 -> {
                    if (setcard4SW == 0){
                        // カード「ざこ１」
                        imageButton1.setImageResource(R.drawable.zako1)
                        textViewCardTitle1.setText(getString(R.string.CardName4))
                        setcard4SW = 1
                        setcardSW = 1
                    }
                }
                4 -> {
                    if (setcard5SW == 0){
                        // カード「ざこ２」
                        imageButton1.setImageResource(R.drawable.zako2)
                        textViewCardTitle1.setText(getString(R.string.CardName5))
                        setcard5SW = 1
                        setcardSW = 1
                    }

                }
                5 -> {
                    if (setcard6SW == 0){
                        // カード「ざこ３」
                        imageButton1.setImageResource(R.drawable.zako3)
                        textViewCardTitle1.setText(getString(R.string.CardName6))
                        setcard6SW = 1
                        setcardSW = 1
                    }
                }
            }
        }
        // 2枚目のデータ設定
        setcardSW = 0
        for (i in 0 .. selectedItemCount - 1){
            if (setcardSW == 1){
                break
            }
            when(selectedItemPosition[i]){
                0 -> {
                    if (setcard1SW == 0){
                        // カード「マツケン」
                        imageButton2.setImageResource(R.drawable.matsuken)
                        textViewCardTitle2.setText(getString(R.string.CardName1))
                        setcard1SW = 1
                        setcardSW = 1
                    }
                }
                1 -> {
                    if (setcard2SW == 0){
                        // カード「ゆーへい」
                        imageButton2.setImageResource(R.drawable.yuhei)
                        textViewCardTitle2.setText(getString(R.string.CardName2))
                        setcard2SW = 1
                        setcardSW = 1
                    }
                }
                2 -> {
                    if (setcard3SW == 0){
                        // カード「やす」
                        imageButton2.setImageResource(R.drawable.yasu)
                        textViewCardTitle2.setText(getString(R.string.CardName3))
                        setcard3SW = 1
                        setcardSW = 1
                    }
                }
                3 -> {
                    if (setcard4SW == 0){
                        // カード「ざこ１」
                        imageButton2.setImageResource(R.drawable.zako1)
                        textViewCardTitle2.setText(getString(R.string.CardName4))
                        setcard4SW = 1
                        setcardSW = 1
                    }
                }
                4 -> {
                    if (setcard5SW == 0){
                        // カード「ざこ２」
                        imageButton2.setImageResource(R.drawable.zako2)
                        textViewCardTitle2.setText(getString(R.string.CardName5))
                        setcard5SW = 1
                        setcardSW = 1
                    }

                }
                5 -> {
                    if (setcard6SW == 0){
                        // カード「ざこ３」
                        imageButton2.setImageResource(R.drawable.zako3)
                        textViewCardTitle2.setText(getString(R.string.CardName6))
                        setcard6SW = 1
                        setcardSW = 1
                    }
                }
            }
        }
        // 3枚目のデータ設定
        setcardSW = 0
        for (i in 0 .. selectedItemCount - 1){
            if (setcardSW == 1){
                break
            }
            when(selectedItemPosition[i]){
                0 -> {
                    if (setcard1SW == 0){
                        // カード「マツケン」
                        imageButton3.setImageResource(R.drawable.matsuken)
                        textViewCardTitle3.setText(getString(R.string.CardName1))
                        setcard1SW = 1
                        setcardSW = 1
                    }
                }
                1 -> {
                    if (setcard2SW == 0){
                        // カード「ゆーへい」
                        imageButton3.setImageResource(R.drawable.yuhei)
                        textViewCardTitle3.setText(getString(R.string.CardName2))
                        setcard2SW = 1
                        setcardSW = 1
                    }
                }
                2 -> {
                    if (setcard3SW == 0){
                        // カード「やす」
                        imageButton3.setImageResource(R.drawable.yasu)
                        textViewCardTitle3.setText(getString(R.string.CardName3))
                        setcard3SW = 1
                        setcardSW = 1
                    }
                }
                3 -> {
                    if (setcard4SW == 0){
                        // カード「ざこ１」
                        imageButton3.setImageResource(R.drawable.zako1)
                        textViewCardTitle3.setText(getString(R.string.CardName4))
                        setcard4SW = 1
                        setcardSW = 1
                    }
                }
                4 -> {
                    if (setcard5SW == 0){
                        // カード「ざこ２」
                        imageButton3.setImageResource(R.drawable.zako2)
                        textViewCardTitle3.setText(getString(R.string.CardName5))
                        setcard5SW = 1
                        setcardSW = 1
                    }

                }
                5 -> {
                    if (setcard6SW == 0){
                        // カード「ざこ３」
                        imageButton3.setImageResource(R.drawable.zako3)
                        textViewCardTitle3.setText(getString(R.string.CardName6))
                        setcard6SW = 1
                        setcardSW = 1
                    }
                }
            }
        }
        // 4枚目のデータ設定
        setcardSW = 0
        for (i in 0 .. selectedItemCount - 1){
            if (setcardSW == 1){
                break
            }
            when(selectedItemPosition[i]){
                0 -> {
                    if (setcard1SW == 0){
                        // カード「マツケン」
                        imageButton4.setImageResource(R.drawable.matsuken)
                        textViewCardTitle4.setText(getString(R.string.CardName1))
                        setcard1SW = 1
                        setcardSW = 1
                    }
                }
                1 -> {
                    if (setcard2SW == 0){
                        // カード「ゆーへい」
                        imageButton4.setImageResource(R.drawable.yuhei)
                        textViewCardTitle4.setText(getString(R.string.CardName2))
                        setcard2SW = 1
                        setcardSW = 1
                    }
                }
                2 -> {
                    if (setcard3SW == 0){
                        // カード「やす」
                        imageButton4.setImageResource(R.drawable.yasu)
                        textViewCardTitle4.setText(getString(R.string.CardName3))
                        setcard3SW = 1
                        setcardSW = 1
                    }
                }
                3 -> {
                    if (setcard4SW == 0){
                        // カード「ざこ１」
                        imageButton4.setImageResource(R.drawable.zako1)
                        textViewCardTitle4.setText(getString(R.string.CardName4))
                        setcard4SW = 1
                        setcardSW = 1
                    }
                }
                4 -> {
                    if (setcard5SW == 0){
                        // カード「ざこ２」
                        imageButton4.setImageResource(R.drawable.zako2)
                        textViewCardTitle4.setText(getString(R.string.CardName5))
                        setcard5SW = 1
                        setcardSW = 1
                    }

                }
                5 -> {
                    if (setcard6SW == 0){
                        // カード「ざこ３」
                        imageButton4.setImageResource(R.drawable.zako3)
                        textViewCardTitle4.setText(getString(R.string.CardName6))
                        setcard6SW = 1
                        setcardSW = 1
                    }
                }
            }
        }
        // 5枚目のデータ設定
        setcardSW = 0
        for (i in 0 .. selectedItemCount - 1){
            if (setcardSW == 1){
                break
            }
            when(selectedItemPosition[i]){
                0 -> {
                    if (setcard1SW == 0){
                        // カード「マツケン」
                        imageButton5.setImageResource(R.drawable.matsuken)
                        textViewCardTitle5.setText(getString(R.string.CardName1))
                        setcard1SW = 1
                        setcardSW = 1
                    }
                }
                1 -> {
                    if (setcard2SW == 0){
                        // カード「ゆーへい」
                        imageButton5.setImageResource(R.drawable.yuhei)
                        textViewCardTitle5.setText(getString(R.string.CardName2))
                        setcard2SW = 1
                        setcardSW = 1
                    }
                }
                2 -> {
                    if (setcard3SW == 0){
                        // カード「やす」
                        imageButton5.setImageResource(R.drawable.yasu)
                        textViewCardTitle5.setText(getString(R.string.CardName3))
                        setcard3SW = 1
                        setcardSW = 1
                    }
                }
                3 -> {
                    if (setcard4SW == 0){
                        // カード「ざこ１」
                        imageButton5.setImageResource(R.drawable.zako1)
                        textViewCardTitle5.setText(getString(R.string.CardName4))
                        setcard4SW = 1
                        setcardSW = 1
                    }
                }
                4 -> {
                    if (setcard5SW == 0){
                        // カード「ざこ２」
                        imageButton5.setImageResource(R.drawable.zako2)
                        textViewCardTitle5.setText(getString(R.string.CardName5))
                        setcard5SW = 1
                        setcardSW = 1
                    }

                }
                5 -> {
                    if (setcard6SW == 0){
                        // カード「ざこ３」
                        imageButton5.setImageResource(R.drawable.zako3)
                        textViewCardTitle5.setText(getString(R.string.CardName6))
                        setcard6SW = 1
                        setcardSW = 1
                    }
                }
            }
        }
    }

    override fun onClick(v: View?) {

        val intent = Intent(this@ChoiseCardActivity,BattleActivity::class.java)

        when(v?.id){
            R.id.imageButton1 ->{
                if (passedimagebutton1 == 1){
                    Toast.makeText(this,"力尽きている",Toast.LENGTH_SHORT).show()
                }else {
                    intent.putExtra("NameTitle", textViewCardTitle1.text.toString())
                    passedimagebutton1 = 1
                    roleNumber = roleNumber + 1
                    startActivity(intent)
//                    finish()
                }
            }
            R.id.imageButton2 ->{
                if (passedimagebutton2 == 1){
                    Toast.makeText(this,"力尽きている",Toast.LENGTH_SHORT).show()
                }else {
                    intent.putExtra("NameTitle", textViewCardTitle2.text.toString())
                    passedimagebutton2 = 1
                    roleNumber = roleNumber + 1
                    startActivity(intent)
//                finish()
                }
            }
            R.id.imageButton3 ->{
                if (passedimagebutton3 == 1){
                    Toast.makeText(this,"力尽きている",Toast.LENGTH_SHORT).show()
                }else {
                    intent.putExtra("NameTitle", textViewCardTitle3.text.toString())
                    passedimagebutton3 = 1
                    roleNumber = roleNumber + 1
                    startActivity(intent)
//                finish()
                }
            }
            R.id.imageButton4 ->{
                if (passedimagebutton4 == 1){
                    Toast.makeText(this,"力尽きている",Toast.LENGTH_SHORT).show()
                }else {
                    intent.putExtra("NameTitle", textViewCardTitle4.text.toString())
                    passedimagebutton4 = 1
                    roleNumber = roleNumber + 1
                    startActivity(intent)
//                finish()
                }
            }
            R.id.imageButton5 ->{
                if (passedimagebutton5 == 1){
                    Toast.makeText(this,"力尽きている",Toast.LENGTH_SHORT).show()
                }else {
                    intent.putExtra("NameTitle", textViewCardTitle5.text.toString())
                    passedimagebutton5 = 1
                    roleNumber = roleNumber + 1
                    startActivity(intent)
//                finish()
                }
            }
        }
    }
}
