package com.seanmiller.alpacadashboard

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPreferencesManager(
        /**
         * The class itself
         */
        private val context: Context
) {
    /**
     * SharedPreferences to store the settings. This way, they'll be available next time the user starts the app
     */
    private val sPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    /**
     * Editor to make changes on sharedPreferences
     */
    private var sEditor: SharedPreferences.Editor? = null
    private val editor: SharedPreferences.Editor
        private get() = sPreferences.edit()

    /**
     * Store a boolean value in sharedPreferences
     *
     * @param tag   identifies the value
     * @param value the value itself
     */
    fun storeBoolean(tag: String?, value: Boolean) {
        sEditor = editor
        sEditor!!.putBoolean(tag, value)
        sEditor!!.commit()
    }

    /**
     * Store a string in sharedPreferences
     *
     * @param tag identifies the value
     * @param str the string itself
     */
    fun storeString(tag: String?, str: String?) {
        sEditor = editor
        sEditor!!.putString(tag, str)
        sEditor!!.commit()
    }

    /**
     * @param tag      identifies the value
     * @param defValue default value
     * @return the stored or default value
     */
    fun retrieveBoolean(tag: String?, defValue: Boolean): Boolean {
        return sPreferences.getBoolean(tag, defValue)
    }

    /**
     * @param tag    identifies the string
     * @param defStr default string
     * @return the stored or default string
     */
    fun retrieveString(tag: String?, defStr: String?): String? {
        return sPreferences.getString(tag, defStr)
    }

    /**
     * @param tag      identifies the value
     * @param defValue default value
     * @return the stored or default value
     */
    fun retrieveInt(tag: String?, defValue: Int): Int {
        return sPreferences.getInt(tag, defValue)
    }

    /**
     * @param tag      identifies the value
     * @param defValue the value itself
     */
    fun storeInt(tag: String?, defValue: Int) {
        sEditor = editor
        sEditor!!.putInt(tag, defValue)
        sEditor!!.commit()
    }

}