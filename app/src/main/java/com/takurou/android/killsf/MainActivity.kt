package com.takurou.android.killsf

import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timer

var arriveMemberCount = 5

var passedimagebutton1 = 0
var passedimagebutton2 = 0
var passedimagebutton3 = 0
var passedimagebutton4 = 0
var passedimagebutton5 = 0

var enemyAttackPower = 0
var enemySpecialPower = 0
var enemyHP = 0

var roleNumber = 0

class MainActivity : AppCompatActivity() {

    lateinit var timer : Timer
    //  サウンドＩＤ
    var intSoundId_GameStart:Int = 0
    // 効果音
    lateinit var soundPool :SoundPool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rotate = RotateAnimation(
            0.0f, 360.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )

//        //  フェードイン
//        val alphaFadeIn = AlphaAnimation(0.0f,1.0f)
//        alphaFadeIn.duration = 3000
//        alphaFadeIn.fillAfter = true
//
//        imageView.startAnimation(alphaFadeIn)
//        textView.startAnimation(alphaFadeIn)

        // 回転
        // animation時間 msec
        rotate.duration = 3000
        // 繰り返し回数
        rotate.repeatCount = 0
        // animationが終わったそのまま表示にする
        rotate.fillAfter = true
        //アニメーションの開始
        imageView.startAnimation(rotate)

        //  相手のステータス設定
        enemyAttackPower = getString(R.string.enemyAttackPower).toInt()
        enemySpecialPower = R.string.enemySpecialPower.toInt()
        enemyHP = getString(R.string.enemyHP).toInt()

        buttonStart.setOnClickListener {
            soundPool.play(intSoundId_GameStart,1.0f,1.0f,0,0,1.0f)
            timer.schedule(1000, { runOnUiThread { nextStart() } })
        }
    }

    private fun nextStart() {
        val intent = Intent(this@MainActivity,SelectCardActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        // タイマークラスのインスタンス化
        timer = Timer()

        // 効果音を出すためのクラス：SoundPoolの準備
        soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            SoundPool.Builder().setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build())
                .setMaxStreams(1)
                .build()
        }else{
            SoundPool(1, AudioManager.STREAM_MUSIC,0)
        }

        // 効果音ファイルをメモリのロード
        intSoundId_GameStart = soundPool.load(this,R.raw.mens_ou1,1)

    }

    override fun onPause() {
        super.onPause()
        // 効果音ファイルのリリース
        soundPool.release()
        // タイマークラスのリリース
        timer.cancel()
    }

}

