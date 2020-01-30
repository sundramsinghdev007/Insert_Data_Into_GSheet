# Insert_Data_Into_GSheet
Using Volley and google sheet script insert the data into google sheet.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
```
Have knowledge of the app cloning from github.
Need a volley library
Google account
Copy and paste the provided google script in the script file.
```
### Volley Libaray 
```implementation 'com.android.volley:volley:1.1.1'```
### Google Sheet Script
```
var ss = SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1MXhOY8DoGITUlMhe0wHwM62djmwSswRrqtHWNzo0L8M/edit#gid=0");

var sheet = ss.getSheetByName('formdata');


function doPost(e){
var action = e.parameter.action;

if(action == 'submitData'){
  return submitData(e);
  
  }
}

function submitData(e){

var date =  new Date();

var id  =  "stu_"+sheet.getLastRow(); // Item1

var name = e.parameter.mobile;  
  
var mobile = e.parameter.mobile;

var email = e.parameter.email;

sheet.appendRow([id,name,mobile,email,date]);

   return ContentService.createTextOutput("Success").setMimeType(ContentService.MimeType.TEXT);

}
```
### Installing
Copy this link and sync with your android studio using git
```
https://github.com/Annu1Singh/Insert_Data_Into_GSheet.git
```
### See Your Inserted Data

After clone and build the app you see the inserted data at this link
```
https://docs.google.com/spreadsheets/d/1MXhOY8DoGITUlMhe0wHwM62djmwSswRrqtHWNzo0L8M/edit?usp=drive_web&ouid=115452785824137040249
```
