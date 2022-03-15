package io.github.dwikiriyadi.sharedpreferences.prefs

import android.content.Context

class ExamplePreferences(context: Context) : BasePreferences(context, "TEST_PREFS") {

    var count : Int by propertyDelegate(0)
    var name  : String by propertyDelegate("")
    var isMyself : Boolean by propertyDelegate(false)
    var modFromCount: Float by propertyDelegate(0F)
    var myMoney: Long by propertyDelegate(0L)
}