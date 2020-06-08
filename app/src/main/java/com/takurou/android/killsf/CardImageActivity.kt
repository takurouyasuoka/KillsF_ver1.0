package com.takurou.android.killsf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_card_image.*

class CardImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_image)

        val bundle = intent.extras
        val longClickItemPosition = bundle?.getInt("longClickItemPosition")

        when(longClickItemPosition){
            0 -> {
                imageViewCardDetail.setImageResource(R.drawable.matsuken)
                textViewCardName.setText(getString(R.string.CardName1))
                textViewCardHP.setText(getString(R.string.HpStatus1))
                textViewCardAttack.setText(getText(R.string.AtackStatus1))
                textViewCardDefence.setText(getText(R.string.DefenceStatus1))
                textViewCharaDetail.setText(getText(R.string.CharaDetail1))
            }
            1 -> {
                imageViewCardDetail.setImageResource(R.drawable.yuhei)
                textViewCardName.setText(getString(R.string.CardName2))
                textViewCardHP.setText(getString(R.string.HpStatus2))
                textViewCardAttack.setText(getText(R.string.AtackStatus2))
                textViewCardDefence.setText(getText(R.string.DefenceStatus2))
                textViewCharaDetail.setText(getText(R.string.CharaDetail2))
            }
            2 -> {
                imageViewCardDetail.setImageResource(R.drawable.yasu)
                textViewCardName.setText(getString(R.string.CardName3))
                textViewCardHP.setText(getString(R.string.HpStatus3))
                textViewCardAttack.setText(getText(R.string.AtackStatus3))
                textViewCardDefence.setText(getText(R.string.DefenceStatus3))
                textViewCharaDetail.setText(getText(R.string.CharaDetail3))
            }
            3 -> {
                imageViewCardDetail.setImageResource(R.drawable.zako1)
                textViewCardName.setText(getString(R.string.CardName4))
                textViewCardHP.setText(getString(R.string.HpStatus4))
                textViewCardAttack.setText(getText(R.string.AtackStatus4))
                textViewCardDefence.setText(getText(R.string.DefenceStatus4))
                textViewCharaDetail.setText(getText(R.string.CharaDetail4))
            }
            4 -> {
                imageViewCardDetail.setImageResource(R.drawable.zako2)
                textViewCardName.setText(getString(R.string.CardName5))
                textViewCardHP.setText(getString(R.string.HpStatus5))
                textViewCardAttack.setText(getText(R.string.AtackStatus5))
                textViewCardDefence.setText(getText(R.string.DefenceStatus5))
                textViewCharaDetail.setText(getText(R.string.CharaDetail5))
            }
            5 -> {
                imageViewCardDetail.setImageResource(R.drawable.zako3)
                textViewCardName.setText(getString(R.string.CardName6))
                textViewCardHP.setText(getString(R.string.HpStatus6))
                textViewCardAttack.setText(getText(R.string.AtackStatus6))
                textViewCardDefence.setText(getText(R.string.DefenceStatus6))
                textViewCharaDetail.setText(getText(R.string.CharaDetail6))
            }
        }
    }
}