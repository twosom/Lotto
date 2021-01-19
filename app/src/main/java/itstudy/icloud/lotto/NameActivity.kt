package itstudy.icloud.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_name.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class NameActivity : AppCompatActivity() {
    fun getLottoNumbersFromHash(str: String): MutableList<Int> {
        //정수를 저장할 List
        var list = mutableListOf<Int>()

        //정수 1~45를 list에 저장
        for (number in 1..45) {
            list.add(number)
            Log.e("list", list.toString())
        }

        //오늘 날짜와 문자열을 합쳐서 새로운 문자열 만들기
        val targetString = SimpleDateFormat("yyyy-MM-dd").format(Date()) + str
        //list의 숫자 섞기
        //문자열의 해시코드를 Long으로 변환해서 Random의 seed를 생성해서 list의 숫자를
        //섞기
        list.shuffle(Random(targetString.hashCode()))
        Log.e("shuffle", list.subList(0, 6).toString())
        return list.subList(0, 6)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)
        goButton.setOnClickListener{
            val intent: Intent = Intent(this, ResultActivity::class.java)

            //입력된 이름이 없으면 Toast출력
            if (TextUtils.isEmpty(editText.text.toString())) {
                Toast.makeText(this@NameActivity, "이름을 입력해주세요.", Toast.LENGTH_LONG).show()
            }

            //데이터 만들기
            var list = getLottoNumbersFromHash(editText.text.toString())
            //데이터 저장
            intent.putIntegerArrayListExtra("result", ArrayList(list))
            intent.putExtra("name", editText.text.toString())



            startActivity(intent)
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}

