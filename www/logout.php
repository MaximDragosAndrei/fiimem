<?php
   session_start();
   unset($_SESSION["email"]);
   unset($_SESSION["password"]);
   
   echo 'You have cleaned session';
   header('Refresh: 0.5; URL = welcome page.html');
?>
