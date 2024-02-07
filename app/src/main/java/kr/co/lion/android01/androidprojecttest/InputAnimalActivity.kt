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
    //여기서 이벤트란 무언가를 클릭했을 때 보여지거나 그러는 것!
    fun setEvent(){
        //토글버튼을 눌렀을 때 해당하는 버튼에 따라 보여지는 것!
        activityInputAnimalBinding.apply {
            toggleGroup1.addOnButtonCheckedListener { group, checkedId, isChecked ->
                //모두 안보이게 한다
                containerInputType1.isVisible = false
                containerInpuptType2.isVisible = false
                containerInputType3.isVisible = false

                when(toggleGroup1.checkedButtonId){
                    R.id.lionButton -> {
                        //항목에 맞는 것들을 보이게 한다
                        containerInputType1.isVisible = true
                        //입력요소 초기화
                        toggleGroup2.check(R.id.buttonGender1)
                        textFieldInputHairCount.setText("")

                    }
                    R.id.tigerButton -> {
                        //항목에 맞는 것을 보이게 한다
                        containerInpuptType2.isVisible = true
                        //입력요소 초기화 ->
                    }
                }

            }
        }

    }


}











































