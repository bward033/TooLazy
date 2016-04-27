package com.example.epiphanybox.toolazy;

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

    //Keys that will be used to send the request to php scripts
    public static final String KEY_First_Name = "First_Name";
    public static final String KEY_Last_Name = "Last_Name";
    public static final String KEY_Birth_date = "Birth_date";
    public static final String KEY_Phone_Number = "Phone_Number";
    public static final String KEY_Email = "Email";
    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_Account_ID = "account_ID";
    public static final String TAG_First_Name = "First_Name";
    public static final String TAG_Last_Name = "Last_Name";
    public static final String TAG_Email = "Email";
    public static final String TAG_Phone_Number = "Phone_Number";

    //employee id to pass with intent
    public static final String Account_ID = "Account_ID";
}

