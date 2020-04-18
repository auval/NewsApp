package com.auval.newsapp

import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.auval.newsapp.model.DataBindingAdapters
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BindingAdaptersInstrumentedTest {
    @Test
    fun testDateConversion() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val tv = TextView(appContext)
        DataBindingAdapters.convertDateFormat(tv, "2020-04-14T06:28:01Z")
        assertEquals("Tue, 14 Apr 2020 06:28:01 GMT", tv.text)

        // and now with milliseconds
        DataBindingAdapters.convertDateFormat(tv, "2020-04-16T16:28:02.2435234Z")
        assertEquals("Thu, 16 Apr 2020 16:28:02 GMT", tv.text)
    }
}
