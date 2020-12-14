package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.nhz.doctorapp.R
import com.nhz.doctorapp.fragments.GeneralQuestionsFragment
import com.nhz.doctorapp.fragments.SpecialityQuestionsFragment

class CaseSummaryFormActivity : AppCompatActivity() {

    private lateinit var vCaseSpeciality : View
    private lateinit var ivCaseSpeciality : ImageView

    companion object{

        const val SPECIALITY_ID = "SPECIALITY_ID"
        const val OLD_OR_NEW = "OLD_OR_NEW"

        fun newIntent(id : Int,oldOrNew : Boolean,context: Context) : Intent{
            return Intent(context,CaseSummaryFormActivity::class.java)
                .putExtra(SPECIALITY_ID,id)
                .putExtra(OLD_OR_NEW,oldOrNew)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_summary_form)

        vCaseSpeciality = findViewById(R.id.vCaseSpeciality)
        ivCaseSpeciality = findViewById(R.id.ivCaseSpeciality)

        val specialityId = intent.getIntExtra(SPECIALITY_ID,0)
        val oldOrNew = intent.getBooleanExtra(OLD_OR_NEW,false)

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,GeneralQuestionsFragment.newInstance(specialityId,oldOrNew)).commit()

    }
}