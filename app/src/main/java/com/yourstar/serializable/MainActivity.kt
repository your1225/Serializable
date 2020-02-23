package com.yourstar.serializable

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class MainActivity : AppCompatActivity() {

    companion object {
        private final val FILE_NAME: String = "myFile.data"
        private final val TAG: String = "hello"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener {
            val math: Int = etMath.text.toString().toInt()
            val english: Int = etEnglish.text.toString().toInt()
            val chinese: Int = etChinese.text.toString().toInt()
            val score: Score = Score(math, english, chinese)

            val name: String = etName.text.toString()
            val age: Int = etAge.text.toString().toInt()
            val student: Student = Student(name, age, score)

            try {
                val objectOutputStream: ObjectOutputStream =
                    ObjectOutputStream(this.openFileOutput(FILE_NAME, Context.MODE_PRIVATE))
                objectOutputStream.writeObject(student)
                objectOutputStream.flush()
                objectOutputStream.close()

                Toast.makeText(this, "Data saved!", Toast.LENGTH_SHORT).show()

                etMath.text.clear()
                etEnglish.text.clear()
                etChinese.text.clear()
                etName.text.clear()
                etAge.text.clear()
                tvGrade.text = ""
            } catch (e: IOException) {
                Log.e(TAG, "onClick", e)
            }
        }

        btnLoad.setOnClickListener {
            try {
                val objectInputStream: ObjectInputStream = ObjectInputStream(
                    this.openFileInput(
                        FILE_NAME
                    )
                )
                val student: Student = objectInputStream.readObject() as Student

                etName.setText(student.name)
                etAge.setText(student.age.toString())
                etMath.setText(student.score.math.toString())
                etEnglish.setText(student.score.english.toString())
                etChinese.setText(student.score.chinese.toString())
                tvGrade.setText(student.score.grade)
            } catch (e: IOException) {
                Log.e(TAG, "onClick", e)
            } catch (e: ClassNotFoundException) {
                Log.e(TAG, "ocClick", e)
            }
        }
    }
}
