package com.yourstar.serializable

import java.io.Serializable

data class Student(var name: String, var age: Int, var score: Score) : Serializable

data class Score(var math: Int, var english: Int, var chinese: Int) : Serializable {
    //lazy 只在第一将你访问属性时计算，以后直接读取计算出来的值，下面的有可能不太好，如果成绩改变，会导致grade错误
    val grade: String by lazy {
        if (math >= 90 && english >= 90 && chinese >= 90) {
            "A"
        } else if (math >= 80 && english >= 80 && chinese >= 80) {
            "B"
        } else {
            "C"
        }
    }

//    init {
//        if (math >= 90 && english >= 90 && chinese >= 90) {
//            grade = "A"
//        } else if (math >= 80 && english >= 80 && chinese >= 80) {
//            grade = "B"
//        } else {
//            grade = "C"
//        }
//    }
}