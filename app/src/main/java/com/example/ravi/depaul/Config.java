package com.example.ravi.depaul;

public class Config {
    public static final String TEACHER_URL = "http://basilravi.3eeweb.com/depaul/teacher.json";
    public static final String PHOTOS_URL="http://basilravi.3eeweb.com/depaul/gall.json";
    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String PHOTOS="http://basilravi.3eeweb.com/depaul/stud/";
    //URl to our logi.php
    public static final String LOGIN_URl = "http://basilravi.3eeweb.com/depaul/login.php";
    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_NAME="name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";
    public static final String LOGIN_FAILURE = "failure";
    public static final String NAME_SHARED_PREF = "name";
    public static final String Event_FEED = "http://basilravi.3eeweb.com/depaul/news.json";
    //We will use this to strore the boolean in sharedprefernce to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

}
