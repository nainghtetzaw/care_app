package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.fragments.ConsultationHistoryFragment
import com.nhz.doctorapp.fragments.HomeFragment
import com.nhz.doctorapp.mvp.presenters.MainPresenter
import com.nhz.doctorapp.mvp.presenters.impls.MainPresenterImpl
import com.nhz.doctorapp.mvp.views.MainView

class MainActivity : AppCompatActivity(),MainView{

    private lateinit var fragment1 : HomeFragment
    private lateinit var fragment2 : ConsultationHistoryFragment
    private lateinit var btnNavi : BottomNavigationView
    private lateinit var mPresenter : MainPresenter

    companion object{
        private val CONSULTATION_ID = "CONSULTATION_ID"

        fun newIntent(consultationId : String,context: Context) : Intent{
            return Intent(context,MainActivity::class.java)
                    .putExtra(CONSULTATION_ID,consultationId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpPresenter()

        btnNavi = findViewById(R.id.btnNavi)

        val consultationId = intent.getStringExtra(CONSULTATION_ID)
        fragment1 = HomeFragment.newInstance(consultationId)
        fragment2 = ConsultationHistoryFragment()

        identifyFragments()

        mPresenter.onUiReady(this,this)
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(MainPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun identifyFragments(){
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer,fragment1)
                .commit()

        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer,fragment2)
                .hide(fragment2)
                .commit()

        btnNavigationEventListener(fragment1)
    }

    private fun btnNavigationEventListener(fragment : Fragment){
        var activeFragment : Fragment = fragment

        btnNavi.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menuHome -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
                    activeFragment = fragment1
                    true
                }
//                R.id.menuConsultation -> {
//                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
//                    activeFragment = fragment2
//                    true
//                }
                else -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
                    activeFragment = fragment1
                    true
                }
            }
        }
    }
}