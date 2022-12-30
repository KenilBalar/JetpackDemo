package com.example.jetpackpractice.utils

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.example.jetpackpractice.R

object utils {

    const val SHOW_DIALOG = "showDialog"
    const val HIDE_DIALOG = "hideDialog"
    const val LOGOUT = "logout"
    const val CREDENTIALS = "Credentials"
    const val PREFERENCE_KEY_EMAIL = "email"
    const val PREFERENCE_KEY_PASSWORD = "password"
    const val PREFERENCE_KEY_AUTH = "auth"
    const val PREFERENCE_KEY_REMEMBER_USER = "remember_user"

    private var dialog: ProgressDialog? = null

    /**
     * this function is use for Toast
     * */
    fun DebugToast(context: Context, message: String) {
        //if (BuildConfig.DEBUG) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        //}
    }

    fun String.ShowLog(value : Any){
        Log.e(this,"${value}")
    }

    fun ShowProgress(context: Context) {
        dialog = ProgressDialog(context, R.style.MyAlertDialogStyle);
        dialog!!.setMessage(context.getString(R.string.please_wait));
        dialog!!.setCancelable(false)
        dialog!!.show();
    }

    fun HideProgress() {
        if (dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

    fun isConnect(context: Context): Boolean {
        var isConnected = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        isConnected = true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        isConnected = true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        isConnected = true
                    }
                }
            }
        } else {
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            isConnected = activeNetwork?.isConnectedOrConnecting == true

        }
        if (!isConnected) {
            HideProgress()
            DebugToast(context, context.getString(R.string.check_internet))
        }
        return isConnected
    }
}
