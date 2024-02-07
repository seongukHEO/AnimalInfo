package kr.co.lion.android01.androidprojecttest

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kr.co.lion.android01.androidprojecttest.databinding.ActivityInputAnimalBinding
import kr.co.lion.androidproject1test.AnimalType
import kr.co.lion.androidproject1test.LION_GENDER
import kr.co.lion.androidproject1test.Util


class InputAnimalActivity : AppCompatActivity() {
    lateinit var activityInputAnimalBinding: ActivityInputAnimalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputAnimalBinding = ActivityInputAnimalBinding.inflate(layoutInflater)
        setContentView(activityInputAnimalBinding.root)

        setView()
        setToolBar()
        setEvent()

    }

    //뷰 설정
    fun setView(){
        activityInputAnimalBinding.apply {
            toggleGroup1.check(R.id.lionButton)
                containerInputType1.isVisible = true
                containerInpuptType2.isVisible = false
                containerInputType3.isVisible = false

            //사자의 성별은 암컷으로 설정한다
            toggleGroup2.check(R.id.buttonGender1)
            //첫 번째 뷰에 포커스를 맞춰준다
            Util.showSoftInput(activityInputAnimalBinding.textFieldInputName, this@InputAnimalActivity)

        }
    }

    //툴바 설정
    fun setToolBar(){
        activityInputAnimalBinding.apply {
            materialToolbar2.apply {
                //타이틀 설정
                title = "동물 등록"

                //아이콘설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 클릭했을 때
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                //메뉴 설정
                inflateMenu(R.menu.inputanimal_menu)
                //메뉴를 클릭했을 때? 함수로 받자!
                setOnMenuItemClickListener {
                    //유효성 검사를 완료한 애들만
                    checkOK()


                    true
                }
            }
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
                    //사자
                    R.id.lionButton -> {
                        //항목에 맞는 것들을 보이게 한다
                        containerInputType1.isVisible = true
                        //입력요소 초기화
                        toggleGroup2.check(R.id.buttonGender1)
                        textFieldInputHairCount.setText("")

                    }
                    //호랑이
                    R.id.tigerButton -> {
                        //항목에 맞는 것을 보이게 한다
                        containerInpuptType2.isVisible = true
                        //입력요소 초기화 -> 다른 버튼을 눌렀다가 돌아왔을때 내부의 값을 없앤다
                        textFieldInputLineCount.setText("")
                        sliderInputWeight.value = sliderInputWeight.valueFrom
                    }
                    //기린
                    R.id.giraffeButton -> {
                        //항목에 맞는 것을 보이게한다
                        containerInputType3.isVisible = true
                        //입력요소 초기화
                        textFieldInputNeckLength.setText("")
                        textFieldInoutRunSpeed.setText("")
                    }
                }
                //이름 입력칸에 포커스를 준다
                Util.showSoftInput(textFieldInputName, this@InputAnimalActivity)
            }
            sliderInputWeight.addOnChangeListener { slider, value, fromUser ->
                textView.text = "몸무게 : ${value.toInt()}kg"
            }
        }

    }
    //유효성 검사
    fun checkOK(){
        activityInputAnimalBinding.apply {
            var str1 = textFieldInputName.text.toString()
            if (str1.trim().isEmpty()){
                Util.showDiaLog(this@InputAnimalActivity, "이름 입력 오류", "이름을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    Util.showSoftInput(textFieldInputName, this@InputAnimalActivity)
                }
                //return을 하지 않으면 모든 오류 메시지가 뜬다
                return
            }
            var str2 = textFieldInputAge.text.toString()
            if (str2.trim().isEmpty()){
                Util.showDiaLog(this@InputAnimalActivity, "나이 입력 오류", "나이를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    Util.showSoftInput(textFieldInputAge, this@InputAnimalActivity)
                }
                return
            }
            when(toggleGroup1.checkedButtonId){
                //사자
                R.id.lionButton -> {
                    var str3 = textFieldInputHairCount.text.toString()
                    if (str3.trim().isEmpty()){
                        Util.showDiaLog(this@InputAnimalActivity, "털의 갯수 입력 오류", "털의 갯수를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(textFieldInputHairCount, this@InputAnimalActivity)
                        }
                        return
                    }
                }
                R.id.tigerButton -> {
                    var str4 = textFieldInputLineCount.text.toString()
                    if (str4.trim().isEmpty()){
                        Util.showDiaLog(this@InputAnimalActivity, "줄무늬 갯수 입력 오류", "줄무늬 갯수를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            //이게 뭐냐하면 DiaLog가 뜬 뒤 포커스 맞추기!
                            Util.showSoftInput(textFieldInputLineCount, this@InputAnimalActivity)
                        }
                        return
                    }
                }
                R.id.giraffeButton -> {
                    var str5 = textFieldInputNeckLength.text.toString()
                    if (str5.trim().isEmpty()){
                        Util.showDiaLog(this@InputAnimalActivity, "목의 길이 입력 오류", "목의 길이를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(textFieldInputNeckLength,this@InputAnimalActivity)
                        }
                        return
                    }
                    var str6 = textFieldInoutRunSpeed.text.toString()
                    if (str6.trim().isEmpty()){
                        Util.showDiaLog(this@InputAnimalActivity, "달리기 속도 입력 오류", "달리기 속도를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(textFieldInoutRunSpeed, this@InputAnimalActivity)
                        }
                        return
                    }
                }
            }
        }
        recordInfo()
        finish()

    }

    //값을 저장하기
    fun recordInfo(){
        //분기해서 저장한다
        activityInputAnimalBinding.apply {
            when(toggleGroup1.checkedButtonId){
                //사자
                R.id.lionButton -> {
                    var lion = LionClass()
                    lion.type = AnimalType.ANIMAL_TYPE_LION
                    lion.name = textFieldInputName.text.toString()
                    lion.age = textFieldInputAge.text.toString().toInt()
                    //성별을 분기한다
                    lion.gender = when(toggleGroup2.checkedButtonId){
                        R.id.buttonGender1 -> LION_GENDER.LION_GENDER1
                        R.id.buttonInputGender2 -> LION_GENDER.LION_GENDER2
                        else -> LION_GENDER.LION_GENDER1
                    }
                    lion.fairCount = textFieldInputHairCount.text.toString().toInt()
                    Util.animalList.add(lion)
                }
                R.id.tigerButton -> {
                    var tiger = TigerClass()
                    tiger.type = AnimalType.ANIMAL_TYPE_TIGER
                    tiger.name = textFieldInputName.text.toString()
                    tiger.age = textFieldInputAge.text.toString().toInt()
                    tiger.lineCount = textFieldInputLineCount.text.toString().toInt()
                    tiger.weight = sliderInputWeight.value.toInt()
                    //모든 값을 animalList에 담아준다
                    Util.animalList.add(tiger)
                }
                R.id.giraffeButton -> {
                    var giraffe = GiraffeClass()
                    giraffe.type = AnimalType.ANIMAL_TYPE_GIRAFFE
                    giraffe.name = textFieldInputName.text.toString()
                    giraffe.age = textFieldInputAge.toString().toInt()
                    giraffe.neck = textFieldInputNeckLength.text.toString().toInt()
                    giraffe.runSpeed = textFieldInoutRunSpeed.text.toString().toInt()
                    Util.animalList.add(giraffe)
                }

            }
        }

    }


}











































