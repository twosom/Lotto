package itstudy.icloud.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    //1~45까지 숫자 중 하나를 랜덤하게 리턴해주는 함수를 생성
    fun getRandomLottoNumber(): Int {
        return Random().nextInt(45) + 1
    }

    //1~45까지 숫자 6개를 리스트로 만들어서 리턴하는 메소드
    //숫자는 중복되지 않게 생성
    fun getRandomLottoNumbers(): MutableList<Int> {
        //정수를 저장할 List를 생성
        val lottoNumbers = mutableListOf<Int>()

        for (i in 1..6) {
            var number = 0
            do {
                //랜덤한 숫자를 가져와서
                number = getRandomLottoNumber()
                lottoNumbers.add(number)


                Log.e("number", lottoNumbers.toString())
            }//lottoNumbers에 있으면 다시 가져오기
            while(!lottoNumbers.contains(number))
        }

        return lottoNumbers
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        randomCard.setOnClickListener {
            val intent: Intent = Intent(this, ResultActivity::class.java)
            //데이터 저장
            intent.putIntegerArrayListExtra("result", ArrayList(getRandomLottoNumbers()))
            startActivity(intent)
        }

        constellationCard.setOnClickListener {
            val intent: Intent = Intent(this, ConstellationActivity::class.java)
            startActivity(intent)
        }

        nameCard.setOnClickListener {
            val intent: Intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
        }
    }
}