<?php
  $postSurname = $_POST['post_surname'];
  $postFirstname = $_POST['post_firstname'];
  $postUsername = $_POST['post_username'] ;
  $postEmail = lower(  $_POST['post_email']);
  $postAddress = $_POST['post_addess'];
  $postPhone = $_POST['post_phone'] ;
  $postPassword = hash("sha256",$_POST['post_password']);
  mysql_query("INSERT INTO Members (surname,firstname,username,email,address,phone, password,ficitv, birthdate)
  VALUES($postSurname,$postFirstname,$postUsername, $postEmail,$postAddress,$postPhone,$postPassword,0,
  CAST('". $_POST['post_birth_date'] ."' AS DATE))") or die(mysql_error());
?>
