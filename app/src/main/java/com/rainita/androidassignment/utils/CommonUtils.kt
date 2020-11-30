package com.rainita.androidassignment.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresPermission
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.appcompat.app.AlertDialog
import android.telephony.TelephonyManager
import android.text.format.Formatter
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import com.rainita.androidassignment.R
import java.net.NetworkInterface
import java.util.regex.Pattern


object CommonUtils {

    /**
     * old method but working perfactly (https://stackoverflow.com/questions/3407256/height-of-status-bar-in-android)
     */
    fun getStatusBarHeight(mContext: Context): Int {
        var result = 0
        val resourceId = mContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = mContext.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }


    fun isInternetConnected(mContext: Context, isShowDialog: Boolean = false): Boolean {
        var isConnected = false
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm.activeNetworkInfo != null) {
            isConnected = cm.activeNetworkInfo.isConnected
        }
        if (!isConnected && isShowDialog) {
            showOkDialog(mContext, mContext.getString(R.string.app_name),
                    mContext.getString(R.string.default_internet_message))

        }
        return isConnected
    }


    fun showOkDialog(context: Context, title: String = context.getString(R.string.app_name), message: String?, isFinish: Boolean = false) {
        if (null == message)
            return
        val adb = AlertDialog.Builder(context)
        adb.setTitle(title)
        adb.setMessage(message)
        adb.setPositiveButton("Ok") { dialog, which ->
            if (isFinish) {
                val activity = context as Activity
                activity.finish()
            } else {
                dialog.dismiss()
            }

        }
        adb.show()
    }


    fun getAppVersion(context: Context): Int {
        try {
            return context.packageManager.getPackageInfo(context.packageName, 0).versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            // should never happen
            throw RuntimeException("Could not get package name: " + e)
        }
    }

    fun getDeviceId(mContext: Context): String {
        return Settings.Secure.getString(mContext.contentResolver, Settings.Secure.ANDROID_ID)
    }

    val deviceModel: String
        get() {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            return if (model.startsWith(manufacturer)) {
                capitalize(model)
            } else {
                capitalize(manufacturer) + " " + model
            }
        }

    fun capitalize(deviceModel: String?): String {
        if (deviceModel == null || deviceModel.length == 0) {
            return ""
        }
        val first = deviceModel[0]
        return if (Character.isUpperCase(first)) {
            deviceModel
        } else {
            Character.toUpperCase(first) + deviceModel.substring(1)
        }
    }

    val deviceOSVersion = Build.VERSION.SDK_INT.toString()


    //--- get IMEI number of Device
//    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
//    fun getIMEINumber(mContext: Context): String? {
//        val telephonyManager = mContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        return if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//
//            null
//        } else telephonyManager.deviceId.toString()
//
//    }

    //--- get ip address of mobile
    val ipAddress: String?
        @Throws(Exception::class)
        get() {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr = intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress) {
                        return Formatter.formatIpAddress(inetAddress.hashCode())
                    }
                }
            }
            return null
        }




    private fun showSettingsDialog(mContext: Context, settingsText: String? = "Settings", cancelText: String? = "Cancel", title: String, message: String) {
        val alertSettings = AlertDialog.Builder(mContext)
        alertSettings.setTitle(title)
        alertSettings.setMessage(message)
        alertSettings.setPositiveButton("Settings") { dialog, which ->
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", mContext.packageName, null)
            intent.data = uri
            mContext.startActivity(intent)
        }
        alertSettings.setNegativeButton("Cancel") { dialog, which -> dialog.dismiss() }
        alertSettings.show()
    }

    fun dpToPx(context: Context, dp: Int) = Math.round(dp * getPixelScaleFactor(context))

    fun pxToDp(context: Context, px: Int) = Math.round(px / getPixelScaleFactor(context))

    private fun getPixelScaleFactor(context: Context): Float {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT
    }


    fun hideSoftKeyboard(activity: Activity) {
        if (activity.currentFocus != null) {
            val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity!!.currentFocus?.windowToken, 0)
        }
    }

    fun showSoftKeyboard(activity: Activity, mView: View) {
        if (activity.currentFocus != null) {
            val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInputFromWindow(mView.applicationWindowToken, InputMethodManager.SHOW_FORCED, 0)
        }
    }

    fun showToast(mContext: Context, message: String) = Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

    fun showLongToast(mContext: Context, message: String) = Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()


    fun isValidEmail(editText: EditText): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")
        val matcher = pattern.matcher(editText.text.toString())
        return matcher.matches()
    }

    @VisibleForTesting
    fun isValidEmail(editText: String): Boolean {
        //val EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
        val pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")
        val matcher = pattern.matcher(editText)
        return matcher.matches()
    }

    @VisibleForTesting
    fun isValidPhoneNumber(editText: String): Boolean {
        val PHONE_REGEX = "^\\+(?:[0-9] ?){6,14}[0-9]\$"
        val pattern = Pattern.compile(PHONE_REGEX)
        val matcher = pattern.matcher(editText)
        return matcher.matches()
    }

    @VisibleForTesting
    fun isValidPhoneNumberLength(editText: String): Boolean {
        return editText.length >= 10
    }

    @VisibleForTesting
    fun isNameContainsAdmin(name: String): Boolean {
        return name == "Admin" || name == "admin"
    }


}
