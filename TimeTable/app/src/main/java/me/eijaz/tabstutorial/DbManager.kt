package me.eijaz.tabstutorial

class DbManager {

    // database name
    var dbName = "MyNotes"

    //table name
    var dbTable = "Notes"

    //columns
    var colID = "ID"
    var colTitle = "Title"
    var colDes = "Description"
    //database version
    var dbVersion = 1

    val sqlCreateTable = "CREATE TABLE"

}