package com.takurou.android.killsf

import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_battle.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.jar.Attributes
import kotlin.concurrent.schedule

class BattleActivity : AppCompatActivity() {

    lateinit var timer :Timer
    var NameTitle :String? = ""
    var attackPower = 0
    var defencePower = 0
    var HP = 0
    var actionMSG = ""
    var randomNumber = 0
    var defenceFlag = 0

    // 効果音
    lateinit var soundPool : SoundPool

    //  バックミュージック
    lateinit var battleMusic : MediaPlayer

//    var intSoundId_BattleStart:Int = 0
    var intSoundId_Attack:Int = 0
    var intSoundId_Recommend:Int = 0
    var intSoundId_Mistake:Int = 0
    var intSoundId_Win:Int = 0
    var intSoundId_Lose:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)

        val bundle :Bundle? = intent.extras
        NameTitle = bundle?.getString("NameTitle")
        actionMSG = "ＢＡＴＴＬＥ　ＳＴＡＲＴ！"
        textViewBattleMsg.setText(actionMSG)

        //  バックミュージックの再生
        battleMusic = MediaPlayer.create(this,R.raw.killsf_battle_music)
        battleMusic.start()

        // 役割の設定
        when(roleNumber){
            1 -> {
                textViewRole.setText(getString(R.string.role1))
            }
            2 -> {
                textViewRole.setText(getString(R.string.role2))
            }
            3 -> {
                textViewRole.setText(getString(R.string.role3))
            }
            4 -> {
                textViewRole.setText(getString(R.string.role4))
            }
            5 -> {
                textViewRole.setText(getString(R.string.role5))
            }
        }
        //  自分のカードの設定
        roleOwnSetting()

        // あいてカードの設定
        EnemyHP.setText(enemyHP.toString())

        // 攻撃ボタン押下時
        buttonBattleAtack.setOnClickListener {
            buttonEnabledFalse()
            //  自分の攻撃
            //  攻撃メッセージの編集
            actionMSG = NameTitle + getString(R.string.actionMSG)
            textViewBattleMsg.setText(actionMSG)
            soundPool.play(intSoundId_Attack,1.0f,1.0f,0,0,1.0f)
            //  少し時間空けて攻撃結果を反映
            randomNumber = Random().nextInt(10)
            when(randomNumber){
                0,1 -> {    //  攻撃ミス
                    timer.schedule(2000, { runOnUiThread { buttleAttackMiss() } })
                }
                else -> {   //  攻撃成功
                    timer.schedule(2000, { runOnUiThread { buttleAttack() } })
                }
            }
//            if (randomNumber < 3){
//                //  攻撃ミス
//                timer.schedule(2000, { runOnUiThread { buttleAttackMiss() } })
//            }else{
//                //  攻撃成功
//                timer.schedule(2000, { runOnUiThread { buttleAttack() } })
//            }
        }

        //  防御ボタン押下時
        buttonBattleDefence.setOnClickListener {
            buttonEnabledFalse()
            defenceFlag = 1
            //  防御メッセージの編集
            actionMSG = NameTitle + "は殻にこもった"
            textViewBattleMsg.setText(actionMSG)
            timer.schedule(2000,{runOnUiThread { AttackEnemyMSG() }})
        }

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
//        intSoundId_BattleStart = soundPool.load(this,R.raw.gong_played1,1)
        intSoundId_Attack = soundPool.load(this,R.raw.sword_drawn1,1)
        intSoundId_Recommend = soundPool.load(this,R.raw.punch_high1,1)
        intSoundId_Mistake = soundPool.load(this,R.raw.boyon1,1)
        intSoundId_Win = soundPool.load(this,R.raw.gong_played2,1)
        intSoundId_Lose = soundPool.load(this,R.raw.down1,1)

    }

    override fun onPause() {
        super.onPause()
        // タイマークラスのリリース
        timer.cancel()
        // 効果音ファイルのリリース
        soundPool.release()
        //  バックミュージックのリリース
        battleMusic.release()
    }

    private fun buttleAttack() {
        //  HPから攻撃分を差し引く
        enemyHP = enemyHP - attackPower
        if (enemyHP <= 0){
            enemyHP = 0
        }
        EnemyHP.setText(enemyHP.toString())
        //  攻撃結果メッセージの編集
        actionMSG = "あいてに" + attackPower.toString() + "のダメージ"
        textViewBattleMsg.setText(actionMSG)
        soundPool.play(intSoundId_Recommend,1.0f,1.0f,0,0,1.0f)
        if (enemyHP <= 0){
            timer.schedule(2000,{runOnUiThread { GameWin() }})
        }else{
            timer.schedule(2000,{runOnUiThread { AttackEnemyMSG() }})
        }
    }

    private fun GameWin() {
        actionMSG = "あなたの勝ち"
        textViewBattleMsg.setText(actionMSG)
        soundPool.play(intSoundId_Win,1.0f,1.0f,0,0,1.0f)
        buttonEnabledFalse()
    }

    private fun AttackEnemyMSG(){
        actionMSG = "あいての攻撃"
        textViewBattleMsg.setText(actionMSG)
        soundPool.play(intSoundId_Attack,1.0f,1.0f,0,0,1.0f)
        timer.schedule(2000,{runOnUiThread { AttackEnemy() }})
    }

    private fun AttackEnemy() {
        randomNumber = Random().nextInt(10)
        when(randomNumber){
            0,1 -> {    //  攻撃ミス
                defenceFlag = 0
               buttleEnemyMiss()
            }
            2 -> {      //  会心の一撃
                //  HPから攻撃分を差し引く
                if (defenceFlag == 0){
                    HP = HP - enemySpecialPower
                    actionMSG = NameTitle + "はパ○ハラを受けた　　" + enemySpecialPower.toString() + "のダメージ"
                }else{
                    HP = HP + defencePower - enemySpecialPower
                    actionMSG = NameTitle + "はパ○ハラを受けた　　" + (enemySpecialPower - defencePower).toString() + "のダメージ"
                }
                if (HP <= 0){
                    HP = 0
                }
                OwnHP.setText(HP.toString())
                textViewBattleMsg.setText(actionMSG)
                soundPool.play(intSoundId_Recommend,1.0f,1.0f,0,0,1.0f)
                defenceFlag = 0
                if (HP == 0){
                    timer.schedule(2000,{runOnUiThread { actionLose() }})
                }else {
                    timer.schedule(2000, { runOnUiThread { actionClerar() } })
                }
            }
            else -> {
                //  HPから攻撃分を差し引く
                if (defenceFlag == 0){
                    HP = HP - enemyAttackPower
                    actionMSG = NameTitle + "は" + enemyAttackPower.toString() + "のダメージ"
                }else{
                    HP = HP + defencePower - enemyAttackPower
                    actionMSG = NameTitle + "は" + (enemyAttackPower - defencePower).toString() + "のダメージ"
                }
                if (HP <= 0){
                    HP = 0
                }
                OwnHP.setText(HP.toString())
                textViewBattleMsg.setText(actionMSG)
                soundPool.play(intSoundId_Recommend,1.0f,1.0f,0,0,1.0f)
                defenceFlag = 0
                if (HP == 0){
                    timer.schedule(2000,{runOnUiThread { actionLose() }})
                }else{
                    timer.schedule(2000,{runOnUiThread { actionClerar()}})
                }
            }
        }

//        if (randomNumber < 2){
//            buttleEnemyMiss()
//        }else{
//            //  HPから攻撃分を差し引く
//            HP = HP - enemyAttackPower
//            if (HP <= 0){
//                HP = 0
//            }
//            actionMSG = NameTitle + "は" + enemyAttackPower.toString() + "のダメージ"
//            OwnHP.setText(HP.toString())
//            textViewBattleMsg.setText(actionMSG)
//            soundPool.play(intSoundId_Recommend,1.0f,1.0f,0,0,1.0f)
//            if (HP == 0){
//                timer.schedule(2000,{runOnUiThread { actionLose() }})
//            }else{
//                timer.schedule(2000,{runOnUiThread { actionClerar()}})
//            }
//        }
    }

    private fun actionLose() {
        arriveMemberCount = arriveMemberCount - 1
        actionMSG = NameTitle + "は力尽きた"
        textViewBattleMsg.setText(actionMSG)
        soundPool.play(intSoundId_Lose,1.0f,1.0f,0,0,1.0f)

        if (arriveMemberCount == 0) {
            timer.schedule(2000,{runOnUiThread { GameLose() }})
        } else{
            timer.schedule(2000, { runOnUiThread { memberChange() } })
        }
    }

    private fun GameLose() {
        actionMSG = "あなたの負け"
        textViewBattleMsg.setText(actionMSG)
        buttonEnabledFalse()
    }

    private fun memberChange() {
        actionClerar()
        finish()
    }

    private fun actionClerar(){
        actionMSG = ""
        textViewBattleMsg.setText(actionMSG)
        buttonEnabledTrue()
    }

    private fun buttonEnabledTrue() {
        buttonBattleAtack.isEnabled = true
        buttonBattleDefence.isEnabled = true
        buttonBattleChange.isEnabled = true
        buttonBattleItem.isEnabled = true
        buttonBattleSpecial.isEnabled = true
    }

    private fun buttonEnabledFalse() {
        buttonBattleAtack.isEnabled = false
        buttonBattleDefence.isEnabled = false
        buttonBattleChange.isEnabled = false
        buttonBattleItem.isEnabled = false
        buttonBattleSpecial.isEnabled = false
    }

    private fun buttleAttackMiss() {
        actionMSG = NameTitle + "　痛恨のミス"
        textViewBattleMsg.setText(actionMSG)
        soundPool.play(intSoundId_Mistake,1.0f,1.0f,0,0,1.0f)
        timer.schedule(2000,{runOnUiThread { AttackEnemyMSG() }})
    }

    private fun buttleEnemyMiss() {
        actionMSG = "痛恨のミス"
        textViewBattleMsg.setText(actionMSG)
        soundPool.play(intSoundId_Mistake,1.0f,1.0f,0,0,1.0f)
        timer.schedule(2000,{runOnUiThread { actionClerar() }})
    }

    private fun roleOwnSetting() {
        when(NameTitle){
            getString(R.string.CardName1) -> {
                imageViewMyCard.setImageResource(R.drawable.matsuken)
                OwnHP.setText(getString(R.string.HpStatus1))
                attackPower = getString(R.string.AtackStatus1).toInt()
                defencePower = getString(R.string.DefenceStatus1).toInt()
                HP = getString(R.string.HpStatus1).toInt()
            }
            getString(R.string.CardName2) -> {
                imageViewMyCard.setImageResource(R.drawable.yuhei)
                OwnHP.setText(getString(R.string.HpStatus2))
                attackPower = getString(R.string.AtackStatus2).toInt()
                defencePower = getString(R.string.DefenceStatus2).toInt()
                HP = getString(R.string.HpStatus2).toInt()
            }
            getString(R.string.CardName3) -> {
                imageViewMyCard.setImageResource(R.drawable.yasu)
                OwnHP.setText(getString(R.string.HpStatus3))
                attackPower = getString(R.string.AtackStatus3).toInt()
                defencePower = getString(R.string.DefenceStatus3).toInt()
                HP = getString(R.string.HpStatus3).toInt()
            }
            getString(R.string.CardName4) -> {
                imageViewMyCard.setImageResource(R.drawable.zako1)
                OwnHP.setText(getString(R.string.HpStatus4))
                attackPower = getString(R.string.AtackStatus4).toInt()
                defencePower = getString(R.string.DefenceStatus4).toInt()
                HP = getString(R.string.HpStatus4).toInt()
            }
            getString(R.string.CardName5) -> {
                imageViewMyCard.setImageResource(R.drawable.zako2)
                OwnHP.setText(getString(R.string.HpStatus5))
                attackPower = getString(R.string.AtackStatus5).toInt()
                defencePower = getString(R.string.DefenceStatus5).toInt()
                HP = getString(R.string.HpStatus5).toInt()
            }
            getString(R.string.CardName6) -> {
                imageViewMyCard.setImageResource(R.drawable.zako3)
                OwnHP.setText(getString(R.string.HpStatus6))
                attackPower = getString(R.string.AtackStatus6).toInt()
                defencePower = getString(R.string.DefenceStatus6).toInt()
                HP = getString(R.string.HpStatus6).toInt()
            }
        }
    }
}