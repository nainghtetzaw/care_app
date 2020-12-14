package com.nhz.doctorapp.delegates

import com.nhz.shared.data.vos.SpecialitiesVO

interface HomeDelegates {

    fun onTapSpeciality(spciality : SpecialitiesVO,oldOrNew : Boolean)

}