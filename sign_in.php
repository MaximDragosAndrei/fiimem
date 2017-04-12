<!DOCTYPE HTML>
<html>  
<body>
<?php
  $postSurname = $_POST['surname'];
  $postFirstname = $_POST['firstname'];
  $postUsername = $_POST['username'] ;
  $postEmail = lower(  $_POST['email']);
  $postAddress = $_POST['addess'];
  $postPhone = $_POST['phone'] ;
  $postPassword = hash("sha256",$_POST['password']);
  mysql_query("INSERT INTO Members (surname,firstname,username,email,address,phone, password,ficitv, birthdate)
  VALUES($postSurname,$postFirstname,$postUsername, $postEmail,$postAddress,$postPhone,$postPassword,0,
  CAST('". $_POST['birth_date'] ."' AS DATE))") or die(mysql_error());
?>
  
</body>
</html>
