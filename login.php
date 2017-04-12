<!DOCTYPE HTML>
<html>  
<body>
<?php
  mysql_query("SELECT COUNT(*) FROM Members where email = lower('" . $_POST['post_email'] . "')");
?>

</body>
</html>
