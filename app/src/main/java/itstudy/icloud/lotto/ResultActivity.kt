package itstudy.icloud.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_result.backButton
import java.util.ArrayList

class ResultActivity : AppCompatActivity() {
    //안드로이드는 리소스를 정수 상수로 관리
    //이름 순으로 정렬해서 순서대로 번호를 부여
    //같은 용도로 사용되는 이미지는 일련번호를 마지막에
    //추가해서 만드는 것이 좋음.
    //볼 시작 이미지의 아이디를 저장
    val LottoImageStartId = R.drawable.ball_01

    //1~45 사이의 숫자 리스트를 받아서 이미지 뷰에 출력하는 메소드
    fun updateLottoBall(result:List<Int>) {
        imageView01.setImageResource(LottoImageStartId + (result[0] - 1))
        imageView02.setImageResource(LottoImageStartId + (result[1] - 1))
        imageView03.setImageResource(LottoImageStartId + (result[2] - 1))
        imageView04.setImageResource(LottoImageStartId + (result[3] - 1))
        imageView05.setImageResource(LottoImageStartId + (result[4] - 1))
        imageView06.setImageResource(LottoImageStartId + (result[5] - 1))

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        backButton.setOnClickListener {
            finish()
        }

        resultLabel.text="랜덤하게 생성한\n로또 번호"
        //result에 저장된 데이터를 IntegerArray로 읽어오기
        val result = intent.getIntegerArrayListExtra("result")
        //이미지 뷰에 출력
        updateLottoBall(result!!.sortedBy{it})

        //constellation 값을 가져오기
        val constellation = intent.getStringExtra("constellation")


        //name값 가져오기
        val name = intent.getStringExtra("name")

        //데이터가 있다면 resultLabel에 출력
        if (!TextUtils.isEmpty(name)) {
            Log.e("이름", name.toString())
            resultLabel.text="${name.toString()}의\n로또번호"
        }


        //데이터가 있다면 resultLabel 에 출력
        if (!TextUtils.isEmpty(constellation)) {
            Log.e("별자리", constellation.toString())
            resultLabel.text = "${constellation.toString()}의\n로또 번호"
        }

    }
}