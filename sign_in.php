<?php
  mysql_query("INSERT INTO Members (surname,firstname,username,email,address,phone, password,ficitv, birthdate)
  VALUES('". $_POST['post_surname'] ."','". $_POST['post_firstname'] ."','". $_POST['post_username'] ."',
 lower( '". $_POST['post_email'] ."'),'". $_POST['post_addess'] ."','". $_POST['post_phone'] ."','". $_POST['post_password'] ."',0,
  CAST('". $date ."' AS DATE))") or die(mysql_error());
?>
