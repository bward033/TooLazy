package com.example.epiphanybox.toolazy;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by EpiphanyBox on 4/19/16.
 */
public class Config {
    //Address of our scripts of the CRUD
    public static final String URL_ADD="http://cs370group.com/addAccounts.php";
    public static final String URL_ALL_ACCOUNTS="http://cs370group.com/allAccounts.php";
    public static final String URL_UPDATE_ACCOUNT="http://cs370group.com/updateAccounts.php";
    public static final String URL_DELETE_ACCOUNT="http://cs370group.com/deleteAccounts.php";
    public static final String URL_GET_ACCOUNT="http://cs370group.com/getAccount.php";
    public static final String URL_ALL_TASKS="http://cs370group.com/gettasks.php";
    public static final String URL_ADD_TASKS="http://cs370group.com/addtask.php";
    public static final String URL_GET_TASK="http://cs370group.com/gettask.php/?task_id=";
    public static final String URL_GET_MAP_ADDRESS="http://cs370group.com/pulladdress.php/?city=";
    public static final String URL_ACCEPT_TASK="http://cs370group.com/accepttask.php/?task_id=";


    //Keys that will be used to send the request to php scripts
    public static final String KEY_First_Name = "First_Name";
    public static final String KEY_Last_Name = "Last_Name";
    public static final String KEY_Birth_date = "Birth_date";
    public static final String KEY_Phone_Number = "Phone_Number";
    public static final String KEY_Email = "Email";
    public static final String KEY_CATOGORY = "catogory";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_PRICE = "price";
    public static final String KEY_TITLE = "title";
    public static final String KEY_ZIP = "zip";
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_CITY = "city";
    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_Account_ID = "account_ID";
    public static final String TAG_Task_ID = "task_id";
    public static final String TAG_First_Name = "First_Name";
    public static final String TAG_Last_Name = "Last_Name";
    public static final String TAG_Email = "Email";
    public static final String TAG_Phone_Number = "Phone_Number";
    public static final String TAG_CATOGORY = "catogory";
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_PRICE = "price";
    public static final String TAG_TITLE = "title";
    public static final String TAG_ZIP = "zip";
    public static final String TAG_PASSWORD = "Password";
    public static final String TAG_ADDRESS = "address";
    public static final String TAG_CITY = "city";
    public static final String TAG_STATUS = "status";


    //employee id to pass with intent
    public static final String Account_ID = "account_ID";
    public static final String Task_ID = "task_id";
}

