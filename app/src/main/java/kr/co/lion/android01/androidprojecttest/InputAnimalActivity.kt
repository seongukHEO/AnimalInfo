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


class InputAnimalActivity : AppCompatActivity() {
    lateinit var activityInputAnimalBinding:ActivityInputAnimalBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputAnimalBinding = ActivityInputAnimalBinding.inflate(layoutInflater)
        setContentView(activityInputAnimalBinding.root)

        initData()
        setToolBar()
        activityInputAnimalBinding.toggleGroup1.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked){
                goMain(checkedId)
            }
        }



    }

    fun initData(){

    }

    fun setToolBar(){

        //툴바 설정
        activityInputAnimalBinding.apply {
            materialToolbar2.apply {
                //타이틀 설정
                title = "동물 등록"
                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 클릭했을 때
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                //메뉴 설정
                inflateMenu(R.menu.inputanimal_menu)
                //메뉴를 클릭했을 때?
                setOnMenuItemClickListener {
                    activityInputAnimalBinding.apply {
                        when(toggleGroup1.checkedButtonId){
                            R.id.lionButton -> {
                                var str1 = textFieldInputName.text.toString()
                                var str2tt = textFieldInputAge.text.toString()
                                var uri = R.drawable.lion
                                var str3tt = textFieldInputHairCount.text.toString()
                                var str4 = when(toggleGroup2.checkedButtonId){
                                    R.id.buttonGender1 -> "암컷입니다"
                                    R.id.buttonInputGender2 -> "수컷입니다"
                                    else -> ""
                                }
                                var str5 = R.drawable.lion
                                if (str1.isEmpty()){
                                    showDiaLog("이름 오류", "이름을 입력해주세요")
                                }else if (str2tt.isEmpty()){
                                    showDiaLog("나이 오류", "나이를 입력해주세요")
                                }else if (str3tt.isEmpty()){
                                    showDiaLog("털 갯수 오류", "털 갯수를 입력해주세요")
                                }else if (str4.isEmpty()){
                                    showDiaLog("성별 선택 오류", "성별을 선택해주세요")
                                }else {

                                    var str2 = str2tt.toInt()
                                    var str3 = str3tt.toInt()



                                    var lion = LionClass(str1, str2, uri, str3, str4)

                                    var newIntent = Intent()
                                    newIntent.putExtra("obj1", lion)
                                    setResult(RESULT_OK, newIntent)
                                    finish()

                                }


                            }
                            R.id.tigerButton -> {
                                var t1 = textFieldInputName.text.toString()
                                var t2str = textFieldInputAge.text.toString()
                                var uri = R.drawable.tiger
                                var t3str = textFieldInputLineCount.text.toString()
                                var t4str = sliderInputWeight.value.toString()

                                if (t1.isEmpty()){
                                    showDiaLog("이름 오류", "이름을 입력해주세요")
                                }else if (t2str .isEmpty()){
                                    showDiaLog("나이 오류", "나이를 입력해주세요")
                                }else if (t3str.isEmpty()){
                                    showDiaLog("줄무늬 갯수 오류", "줄무늬 갯수를 입력해주세요")
                                }else if (t4str.isEmpty()){
                                    showDiaLog("몸무게 오류", "몸무게를 입력해주세요")
                                }else {
                                    var t2 = t2str.toInt()
                                    var t3 = t3str.toInt()
                                    var t4 = t4str.toDouble()


                                    var tiger = TigerClass(t1, t2, uri, t3, t4)

                                    var newIntent2 = Intent()
                                    newIntent2.putExtra("obj2", tiger)
                                    setResult(RESULT_OK, newIntent2)
                                    finish()
                                }

                            }
                            R.id.giraffeButton -> {
                                var t5 = textFieldInputName.text.toString()
                                var t6str = textFieldInputAge.text.toString()
                                var uri = R.drawable.giraffe
                                var t7str = textFieldInputNeckLength.text.toString()
                                var t8str = textFieldInoutRunSpeed.text.toString()

                                if (t5.isEmpty()){
                                    showDiaLog("이름오류", "이름을 입력해주세요")

                                }else if (t6str.isEmpty()){
                                    showDiaLog("나이 오류", "나이를 입력해주세요")
                                }else if (t7str.isEmpty()){
                                    showDiaLog("목 길이 오류", "목 길이를 입력해주세요")
                                }else if (t8str.isEmpty()){
                                    showDiaLog("달리기 속도 오류", "달리기 속도를 입력해주세요")
                                }else {
                                    var t6 = t6str.toInt()
                                    var t7 = t7str.toInt()
                                    var t8 = t8str.toInt()

                                    var giraffe = GiraffeClass(t5, t6, uri, t7, t8)

                                    var newIntent3 = Intent()
                                    newIntent3.putExtra("obj3", giraffe)
                                    setResult(RESULT_OK, newIntent3)
                                    finish()
                                }
                            }
                        }
                    }


                    true
                }
            }
        }

    }

    fun goMain(checkId:Int){
        activityInputAnimalBinding.apply {
            when(checkId){
                R.id.lionButton -> {
                    containerInputType1.isVisible = true
                    containerInpuptType2.isVisible = false
                    containerInputType3.isVisible = false
                }
                R.id.tigerButton -> {
                    containerInpuptType2.isVisible = true
                    containerInputType1.isVisible = false
                    containerInputType3.isVisible = false
                }
                R.id.giraffeButton -> {
                    containerInputType1.isVisible = false
                    containerInpuptType2.isVisible = false
                    containerInputType3.isVisible = true
                }
            }

        }
    }
    fun showDiaLog(title:String, message:String){
        var viewDiaLog = MaterialAlertDialogBuilder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("확인"){diaLogInterface:DialogInterface, i:Int ->

            }
        }
    }





    }


















































