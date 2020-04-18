package com.auval.newsapp

import com.auval.newsapp.model.NewsModel
import com.auval.newsapp.ui.main.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ViewModelUnitTest {
    @MockK
    lateinit var di: DIContainer

    @MockK
    lateinit var model: NewsModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        every { di.newsModel } returns model
    }

    @Test
    fun testSourceIsCorrect() {
        var viewModel = MainViewModel(di)
        every { model.source } returns "foobar"

        viewModel.fetchTheNews()

        verify { model.fetchTheNews("foobar", any() ) }
    }
}
