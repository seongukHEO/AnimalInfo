package kr.co.lion.android01.androidprojecttest

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kr.co.lion.android01.androidprojecttest.databinding.ActivityModifyInfoBinding
import kr.co.lion.androidproject1test.AnimalType
import kr.co.lion.androidproject1test.LION_GENDER
import kr.co.lion.androidproject1test.Util

class ModifyInfoActivity : AppCompatActivity() {

    lateinit var activityModifyInfoBinding: ActivityModifyInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityModifyInfoBinding = ActivityModifyInfoBinding.inflate(layoutInflater)
        setContentView(activityModifyInfoBinding.root)
        setToolBar()
        showResult()
        setView()
    }


    //툴바 설정
    fun setToolBar(){
        activityModifyInfoBinding.apply {
            toolbarModify.apply {
                //타이틀 설정
                title = "동물 정보 수정"
                //아이콘 배치
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 클릭했을 때
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                //메뉴 배치
                inflateMenu(R.menu.modifyinfo_menu)
                //메뉴를 클릭했을 때
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.end_menu -> {
                            checkOK()
                            finish()

                        }
                    }
                    true
                }
            }
        }

    }

    //뷰 설정
    fun setView(){
        activityModifyInfoBinding.apply {
            sliderModifyWeight.addOnChangeListener { slider, value, fromUser ->
                textModifyView.text = "몸무게 : ${value.toInt()}kg"
            }

        }

    }

    //값을 출력
    fun showResult(){
        activityModifyInfoBinding.apply {
            var obj5 = intent.getIntExtra("position", 0)
            var animal = Util.animalList[obj5]
            //공통
            textFieldModifyName.setText("${animal.name}")
            textFieldModifyAge.setText("${animal.age}살")

            //타입별로 분기
            when(animal.type){
                AnimalType.ANIMAL_TYPE_LION -> {
                    //타입에 맞는 화면을 보이게한다
                    containerModifyType1.isVisible = true
                    //animal을 형 변환 한다
                    var lion = animal as LionClass
                    textFieldModifyHairCount.setText("${lion.fairCount}개")
                    when(lion.gender){
                        LION_GENDER.LION_GENDER1 -> {
                            toggleModifyGroup2.check(R.id.buttonModifyGender2)
                        }
                        LION_GENDER.LION_GENDER2 -> {
                            toggleModifyGroup2.check(R.id.buttonModifyGender1)
                        }
                    }
                }
                AnimalType.ANIMAL_TYPE_TIGER -> {
                    //타입에 맞는 화면을 보이게한다
                    containerModifyType2.isVisible = true
                    var tiger = animal as TigerClass
                    textFieldModifyLineCount.setText("${tiger.lineCount}개")
                    sliderModifyWeight.value = tiger.weight.toFloat()

                }
                AnimalType.ANIMAL_TYPE_GIRAFFE -> {
                    //타입에 맞는 화면을 보이게한다
                    containerModifyType3.isVisible = true
                    //형변환
                    var giraffe = animal as GiraffeClass
                    textFieldModifyNeckLength.setText("${giraffe.neck}cm")
                    textFieldModifyRunSpeed.setText("${giraffe.runSpeed}km")

                }
            }
        }
    }
    //수정처리
    fun modifyResult(){
        activityModifyInfoBinding.apply {
            //스마트 캐스팅을 활용하여 해보자
            var position = intent.getIntExtra("position", 0)
            var animal = Util.animalList[position]
            //공통
            animal.name = textFieldModifyName.text.toString()
            animal.age = textFieldModifyAge.text.toString().toInt()

            //타입별로 분기한다
            when(animal.type){
                AnimalType.ANIMAL_TYPE_LION -> {
                    //형변환
                    if (animal is LionClass){
                        animal.fairCount = textFieldModifyHairCount.text.toString().toInt()
                        when(animal.gender){
                            LION_GENDER.LION_GENDER1 -> {
                                toggleModifyGroup2.check(R.id.buttonModifyGender2)
                            }
                            LION_GENDER.LION_GENDER2 -> {
                                toggleModifyGroup2.check(R.id.buttonModifyGender1)
                            }
                        }
                    }
                }
                AnimalType.ANIMAL_TYPE_TIGER -> {
                    //형변환
                    if (animal is TigerClass){
                        animal.lineCount = textFieldModifyLineCount.text.toString().toInt()
                        animal.weight = sliderModifyWeight.value.toInt()
                    }

                }
                AnimalType.ANIMAL_TYPE_GIRAFFE -> {
                    if (animal is GiraffeClass){
                        animal.neck = textFieldModifyNeckLength.text.toString().toInt()
                        animal.runSpeed = textFieldModifyRunSpeed.text.toString().toInt()
                    }

                }
            }

        }
    }
    //유효성 검사
    fun checkOK(){
        activityModifyInfoBinding.apply {
            var obj = intent.getIntExtra("position", 0)
            var animal = Util.animalList[obj]
            //이름
            var name = textFieldModifyName.text.toString()
            if (name.trim().isEmpty()){
                Util.showDiaLog(this@ModifyInfoActivity, "이름 입력 오류", "이름을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    Util.showSoftInput(textFieldModifyName, this@ModifyInfoActivity)
                }
                return
            }
            //나이
            var age = textFieldModifyAge.text.toString()
            if (age.trim().isEmpty()){
                Util.showDiaLog(this@ModifyInfoActivity, "나이 입력 오류", "나이를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    Util.showSoftInput(textFieldModifyAge, this@ModifyInfoActivity)
                }
                return
            }
            //타입별로 분기한다
            when(animal.type){
                AnimalType.ANIMAL_TYPE_LION -> {
                    var fair = textFieldModifyHairCount.text.toString()
                    if (fair.trim().isEmpty()){
                        Util.showDiaLog(this@ModifyInfoActivity, "털 갯수 입력 오류", "털 갯수를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(textFieldModifyHairCount, this@ModifyInfoActivity)
                        }

                    }
                    toggleModifyGroup2.check(R.id.buttonModifyGender1)
                    return
                }
                AnimalType.ANIMAL_TYPE_TIGER -> {
                    var line = textFieldModifyLineCount.text.toString()
                    if (line.trim().isEmpty()){
                        Util.showDiaLog(this@ModifyInfoActivity, "줄무늬 갯수 오류", "줄무늬 갯수를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(textFieldModifyLineCount, this@ModifyInfoActivity)
                        }
                    }
                    return
                }
                AnimalType.ANIMAL_TYPE_GIRAFFE -> {
                    var neck = textFieldModifyNeckLength.text.toString()
                    if (neck.trim().isEmpty()){
                        Util.showDiaLog(this@ModifyInfoActivity, "목의 길이 오류", "목의 길이를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(textFieldModifyNeckLength, this@ModifyInfoActivity)
                        }
                    }
                    var run = textFieldModifyRunSpeed.text.toString()
                    if (run.trim().isEmpty()){
                        Util.showDiaLog(this@ModifyInfoActivity, "달리는 속도 입력 오류", "달리는 속도를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                            Util.showSoftInput(textFieldModifyRunSpeed, this@ModifyInfoActivity)
                        }
                    }

                }
            }
        }
        modifyResult()
        finish()
    }
}























































