package com.rainita.androidassignment

import com.rainita.androidassignment.utils.CommonUtils
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MyUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testIsEmailValid() {
        val testEmail = "rainitagurjar@gmail.com"
        Assert.assertThat(
            String.format("Email Validity Test failed for %s ", testEmail),
            CommonUtils.isValidEmail(testEmail),
            `is`(true)
        )
    }


    @Test
    fun testEmailValidityPartTwo() {
        val testEmail = "   anupamchugh@gmail.com  "
        Assert.assertThat(
            String.format(
                "Email Validity Test failed for %s ",
                testEmail
            ), CommonUtils.isValidEmail(testEmail), `is`(true)
        )
    }

    @Test
    fun testMobileNumberValidOrNot() {
        val testMobile = "+919548421544"
        Assert.assertThat(
            String.format(
                "Mobile Number Validity Test failed for %s ",
                testMobile
            ), CommonUtils.isValidPhoneNumber(testMobile), `is`(true)
        )
    }

    @Test
    fun testMobileNumberLength() {
        val testMobile = "954821544"
        Assert.assertThat(
            String.format(
                "Mobile Number Length Test failed for %s ",
                testMobile
            ), CommonUtils.isValidPhoneNumberLength(testMobile), `is`(true)
        )
    }

    @Test
    fun testNameContainsAdmin() {
        val testName = "4Admin"
        Assert.assertThat(
            String.format(
                "Your name %s contains Admin word.",
                testName
            ), CommonUtils.isNameContainsAdmin(testName), `is`(false)
        )
    }
}