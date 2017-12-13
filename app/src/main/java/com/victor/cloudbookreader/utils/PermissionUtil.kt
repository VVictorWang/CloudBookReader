package com.victor.cloudbookreader.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.AppOpsManagerCompat
import android.support.v4.content.ContextCompat

/**
 * @author victor
 * @date 12/11/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
fun Context.isPermissionGranted(permission: String): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        return true
    }
    val opPermission: String? = AppOpsManagerCompat.permissionToOp(permission)
    if (!opPermission.isNullOrEmpty()) {
        val result = AppOpsManagerCompat.noteProxyOp(this, opPermission!!, packageName)
        if (result == AppOpsManagerCompat.MODE_IGNORED) return false
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) return false
    }
    return true
}

fun Context.arePemissionGranted(vararg permission: String): Boolean =
        permission.all { isPermissionGranted(it) }

fun Activity.requestPermission(permissions: Array<out String>, requestCode: Int) =
        ActivityCompat.requestPermissions(this, permissions, requestCode)


fun handlePermissionResult(permissions: Array<out String>, grantResults: IntArray,
                           success: ((permissions: List<String>) -> Unit) = {},
                           fail: ((permissions: List<String>) -> Unit) = {}) {
    val deniedList = arrayListOf<String>()
    permissions.indices
            .filter { grantResults[it] != PackageManager.PERMISSION_GRANTED }
            .forEach { deniedList += permissions[it] }
    if (deniedList.isNotEmpty()) {
        fail(deniedList)
    } else {
        success(arrayListOf(*permissions))
    }
}