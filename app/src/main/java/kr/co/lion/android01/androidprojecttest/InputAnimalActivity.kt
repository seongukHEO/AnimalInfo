package kr.co.lion.android01.androidprojecttest

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.android01.androidprojecttest.databinding.ActivityInputAnimalBinding
import kr.co.lion.androidproject1test.Util


class InputAnimalActivity : AppCompatActivity() {
    lateinit var activityInputAnimalBinding: ActivityInputAnimalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputAnimalBinding = ActivityInputAnimalBinding.inflate(layoutInflater)
        setContentView(activityInputAnimalBinding.root)
        setView()

    }

    //뷰 설정
    fun setView(){
        activityInputAnimalBinding.apply {
            //사자의 성별은 암컷으로 설정한다
            toggleGroup2.check(R.id.buttonGender1)
            //첫 번째 뷰에 포커스를 맞춰준다
            Util.showSoftInput(activityInputAnimalBinding.textFieldInputName, this@InputAnimalActivity)

        }
    }

    //뷰들의 이벤트 설정
    fun setEvent(){

    }


}











































