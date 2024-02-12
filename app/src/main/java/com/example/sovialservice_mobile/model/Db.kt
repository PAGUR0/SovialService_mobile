package com.example.sovialservice_mobile.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.sovialservice_mobile.data.ApplicationData
import com.example.sovialservice_mobile.data.UserData

class Db(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Companion.SQL_CREATE_ENTRIES)
        db.execSQL(Companion.SQL_CREATE_ENTRIES_APP)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE ${UserEntry.TABLE_NAME}")
        db?.execSQL("DROP TABLE ${Application.TABLE_NAME}")
        onCreate(db!!)
    }

    override fun onOpen(db: SQLiteDatabase?) {
    }

    fun authUser(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val selection = "${UserEntry.COLUMN_NAME_SNILS} = ? AND ${UserEntry.COLUMN_NAME_PASSWORD} = ?"
        val selectionArgs = arrayOf(username, password)

        val cursor = db.query(
            UserEntry.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val count = cursor.count
        cursor.close()
        return count > 0
    }

    fun isSnils(snils: String): Boolean{
        val db = this.readableDatabase
        val selection = "${UserEntry.COLUMN_NAME_SNILS} = ?"
        val selectionArgs = arrayOf(snils)

        val cursor = db.query(
            UserEntry.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val userExists = cursor.moveToFirst()
        cursor.close()

        return !userExists
    }

    fun addUser(userData: UserData) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(UserEntry.COLUMN_NAME_SNILS, userData.snils)
        values.put(UserEntry.COLUMN_NAME_PASSWORD, userData.password)
        values.put(UserEntry.COLUMN_NAME_SURNAME, userData.surname)
        values.put(UserEntry.COLUMN_NAME_NAME, userData.name)
        values.put(UserEntry.COLUMN_NAME_PATRONYMIC, userData.patronymic)
        values.put(UserEntry.COLUMN_NAME_DATE, userData.birthday)
        values.put(UserEntry.COLUMN_NAME_PHONE, userData.phone)
        values.put(UserEntry.COLUMN_NAME_EMAIL, userData.email)
        values.put(UserEntry.COLUMN_NAME_DOCUMENT, userData.document)
        values.put(UserEntry.COLUMN_NAME_DOCUMENT_NUMBER, userData.documentNumber)
        values.put(UserEntry.COLUMN_NAME_REGION, userData.region)
        values.put(UserEntry.COLUMN_NAME_REGION_SMALL, userData.regionSmall)
        values.put(UserEntry.COLUMN_NAME_CITY, userData.city)
        values.put(UserEntry.COLUMN_NAME_STREET, userData.street)
        values.put(UserEntry.COLUMN_NAME_HOME, userData.home)
        values.put(UserEntry.COLUMN_NAME_APARTMENT, userData.appartment)

        db.insert(UserEntry.TABLE_NAME, null, values)
        db.close()
    }

    fun updateUser(userData: UserData) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(UserEntry.COLUMN_NAME_SURNAME, userData.surname)
        values.put(UserEntry.COLUMN_NAME_NAME, userData.name)
        values.put(UserEntry.COLUMN_NAME_PATRONYMIC, userData.patronymic)
        values.put(UserEntry.COLUMN_NAME_DATE, userData.birthday)
        values.put(UserEntry.COLUMN_NAME_PHONE, userData.phone)
        values.put(UserEntry.COLUMN_NAME_EMAIL, userData.email)
        values.put(UserEntry.COLUMN_NAME_DOCUMENT, userData.document)
        values.put(UserEntry.COLUMN_NAME_DOCUMENT_NUMBER, userData.documentNumber)
        values.put(UserEntry.COLUMN_NAME_PASSWORD, userData.password)
        values.put(UserEntry.COLUMN_NAME_REGION, userData.region)
        values.put(UserEntry.COLUMN_NAME_REGION_SMALL, userData.regionSmall)
        values.put(UserEntry.COLUMN_NAME_CITY, userData.city)
        values.put(UserEntry.COLUMN_NAME_STREET, userData.street)
        values.put(UserEntry.COLUMN_NAME_HOME, userData.home)
        values.put(UserEntry.COLUMN_NAME_APARTMENT, userData.appartment)

        val selection = "${UserEntry.COLUMN_NAME_SNILS} = ?"
        val selectionArgs = arrayOf(userData.snils)

        db.update(UserEntry.TABLE_NAME, values, selection, selectionArgs)
        db.close()
    }

    fun addApplication(application: ApplicationData) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(Application.COLUMN_NAME_SNILS, application.snils)
        values.put(Application.COLUMN_NAME_SOC_ORGANIZATION, application.socOrg)
        values.put(Application.COLUMN_NAME_FORM, application.form)
        values.put(Application.COLUMN_NAME_REASON, application.reason)
        values.put(Application.COLUMN_NAME_FAMILY, application.family)
        values.put(Application.COLUMN_NAME_LIVING, application.living)
        values.put(Application.COLUMN_NAME_INCOME, application.income)
        values.put(Application.COLUMN_NAME_DATE, application.date)
        values.put(Application.COLUMN_NAME_STATUS, application.status)

        db.insert(Application.TABLE_NAME, null, values)
        db.close()
    }

    fun getAllApplications(snils: String): List<ApplicationData> {
        val db = this.readableDatabase
        val applications = mutableListOf<ApplicationData>()

        val projection = arrayOf(
            Application.COLUMN_NAME_ID,
            Application.COLUMN_NAME_SNILS,
            Application.COLUMN_NAME_SOC_ORGANIZATION,
            Application.COLUMN_NAME_FORM,
            Application.COLUMN_NAME_REASON,
            Application.COLUMN_NAME_FAMILY,
            Application.COLUMN_NAME_LIVING,
            Application.COLUMN_NAME_INCOME,
            Application.COLUMN_NAME_DATE,
            Application.COLUMN_NAME_STATUS
        )

        val selection = "${Application.COLUMN_NAME_SNILS} = ?"
        val selectionArgs = arrayOf(snils)

        val cursor = db.query(
            Application.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            val application = ApplicationData(
                cursor.getInt(cursor.getColumnIndexOrThrow(Application.COLUMN_NAME_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(Application.COLUMN_NAME_SNILS)),
                cursor.getString(cursor.getColumnIndexOrThrow(Application.COLUMN_NAME_SOC_ORGANIZATION)),
                cursor.getString(cursor.getColumnIndexOrThrow(Application.COLUMN_NAME_FORM)),
                cursor.getString(cursor.getColumnIndexOrThrow(Application.COLUMN_NAME_REASON)),
                cursor.getString(cursor.getColumnIndexOrThrow(Application.COLUMN_NAME_FAMILY)),
                cursor.getString(cursor.getColumnIndexOrThrow(Application.COLUMN_NAME_LIVING)),
                cursor.getString(cursor.getColumnIndexOrThrow(Application.COLUMN_NAME_INCOME)),
                cursor.getString(cursor.getColumnIndexOrThrow(Application.COLUMN_NAME_DATE)),
                cursor.getString(cursor.getColumnIndexOrThrow(Application.COLUMN_NAME_STATUS))
            )

            applications.add(application)
        }

        cursor.close()
        return applications
    }

    fun updatePassword(snils: String, newPassword: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(UserEntry.COLUMN_NAME_PASSWORD, newPassword)

        val whereClause = "${UserEntry.COLUMN_NAME_SNILS} = ?"
        val whereArgs = arrayOf(snils)

        db.update(UserEntry.TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun getUser(snils: String): UserData? {
        val db = this.readableDatabase
        var userData: UserData? = null

        val projection = arrayOf(
            UserEntry.COLUMN_NAME_SNILS,
            UserEntry.COLUMN_NAME_SURNAME,
            UserEntry.COLUMN_NAME_NAME,
            UserEntry.COLUMN_NAME_PATRONYMIC,
            UserEntry.COLUMN_NAME_DATE,
            UserEntry.COLUMN_NAME_PHONE,
            UserEntry.COLUMN_NAME_EMAIL,
            UserEntry.COLUMN_NAME_DOCUMENT,
            UserEntry.COLUMN_NAME_DOCUMENT_NUMBER,
            UserEntry.COLUMN_NAME_PASSWORD,
            UserEntry.COLUMN_NAME_REGION,
            UserEntry.COLUMN_NAME_REGION_SMALL,
            UserEntry.COLUMN_NAME_CITY,
            UserEntry.COLUMN_NAME_STREET,
            UserEntry.COLUMN_NAME_HOME,
            UserEntry.COLUMN_NAME_APARTMENT
        )

        val selection = "${UserEntry.COLUMN_NAME_SNILS} = ?"
        val selectionArgs = arrayOf(snils)

        val cursor = db.query(
            UserEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            userData = UserData(
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_SNILS)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_SURNAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_PATRONYMIC)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_DATE)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_PHONE)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_EMAIL)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_DOCUMENT)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_DOCUMENT_NUMBER)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_PASSWORD)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_REGION)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_REGION_SMALL)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_CITY)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_STREET)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_HOME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_APARTMENT))
            )
        }

        cursor.close()
        return userData
    }

    object UserEntry : BaseColumns {
        const val TABLE_NAME = "user"
        const val COLUMN_NAME_SNILS = "snils"
        const val COLUMN_NAME_PASSWORD = "password"
        const val COLUMN_NAME_SURNAME = "surname"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_PATRONYMIC = "patronymic"
        const val COLUMN_NAME_DATE = "birthday"
        const val COLUMN_NAME_PHONE = "phone"
        const val COLUMN_NAME_EMAIL = "email"
        const val COLUMN_NAME_DOCUMENT = "document"
        const val COLUMN_NAME_DOCUMENT_NUMBER = "document_number"
        const val COLUMN_NAME_REGION = "region"
        const val COLUMN_NAME_REGION_SMALL = "regionSmall"
        const val COLUMN_NAME_CITY = "city"
        const val COLUMN_NAME_STREET = "street"
        const val COLUMN_NAME_HOME = "home"
        const val COLUMN_NAME_APARTMENT = "apartment"
    }

    object Application : BaseColumns{
        const val TABLE_NAME = "application"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_SNILS = "snils"
        const val COLUMN_NAME_SOC_ORGANIZATION = "socOrg"
        const val COLUMN_NAME_FORM = "form"
        const val COLUMN_NAME_REASON = "reason"
        const val COLUMN_NAME_FAMILY = "family"
        const val COLUMN_NAME_LIVING = "living"
        const val COLUMN_NAME_INCOME = "income"
        const val COLUMN_NAME_DATE = "date"
        const val COLUMN_NAME_STATUS = "status"
    }

    companion object {
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "Db.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${UserEntry.TABLE_NAME} (" +
                    "${UserEntry.COLUMN_NAME_SNILS} TEXT PRIMARY KEY," +
                    "${UserEntry.COLUMN_NAME_PASSWORD} TEXT," +
                    "${UserEntry.COLUMN_NAME_SURNAME} TEXT," +
                    "${UserEntry.COLUMN_NAME_NAME} TEXT," +
                    "${UserEntry.COLUMN_NAME_PATRONYMIC} TEXT," +
                    "${UserEntry.COLUMN_NAME_DATE} TEXT," +
                    "${UserEntry.COLUMN_NAME_PHONE} TEXT," +
                    "${UserEntry.COLUMN_NAME_EMAIL} TEXT," +
                    "${UserEntry.COLUMN_NAME_DOCUMENT} TEXT," +
                    "${UserEntry.COLUMN_NAME_DOCUMENT_NUMBER} TEXT," +
                    "${UserEntry.COLUMN_NAME_REGION} TEXT," +
                    "${UserEntry.COLUMN_NAME_REGION_SMALL} TEXT," +
                    "${UserEntry.COLUMN_NAME_CITY} TEXT," +
                    "${UserEntry.COLUMN_NAME_STREET} TEXT," +
                    "${UserEntry.COLUMN_NAME_HOME} TEXT," +
                    "${UserEntry.COLUMN_NAME_APARTMENT} TEXT" +
                    ")"

        private const val SQL_CREATE_ENTRIES_APP =
            "CREATE TABLE ${Application.TABLE_NAME} (" +
                    "${Application.COLUMN_NAME_ID} INTEGER PRIMARY KEY," +
                    "${Application.COLUMN_NAME_SNILS} TEXT," +
                    "${Application.COLUMN_NAME_SOC_ORGANIZATION} TEXT," +
                    "${Application.COLUMN_NAME_FORM} TEXT," +
                    "${Application.COLUMN_NAME_REASON} TEXT," +
                    "${Application.COLUMN_NAME_FAMILY} TEXT," +
                    "${Application.COLUMN_NAME_LIVING} TEXT," +
                    "${Application.COLUMN_NAME_INCOME} TEXT," +
                    "${Application.COLUMN_NAME_DATE} TEXT," +
                    "${Application.COLUMN_NAME_STATUS} TEXT" +
                    ")"
    }
}

