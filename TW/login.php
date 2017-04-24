<?php
   ob_start();
   session_start();
?>

<?
   // error_reporting(E_ALL);
   // ini_set("display_errors", 1);
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body background="fiimem3.jpg">
<div id="header">
    <div id="menu">
        <ul>
            <li class="active"><a href="login.html">Login</a></li>
            <li><a href="register.html">Register</a></li>
            <li><a href="about us.html">About us</a></li>
        </ul>
    </div>
</div>

<h2>Enter Email and Password</h2>
<div class = "container form-signin">

    <?php
		$msg = '';
		$contor=0;
		if (isset($_POST['login'])) {
			$contor=0;
			$username = 'STUDENT';
			$password = 'STUDENT';
			$connection_string = 'localhost/xe';
			$conn = oci_connect($username, $password, $connection_string);
			// Parse the statement. Note there is no final semi-colon in the SQL statement
			$item = '';
			$pass = '';

			//$pass = hash("sha256",$_POST['password']);
			$pass = $_POST['password'];
			$email = $_POST['email'];
			// print_r($_POST);
			$stid = oci_parse($conn, "SELECT username FROM Members where lower(email) = lower( :email ) AND password = :parola ");

			oci_bind_by_name($stid, ":email", $email);
			oci_bind_by_name($stid, ":parola", $pass);

			 oci_execute($stid);

			while (($row = oci_fetch_array($stid))) {
			    echo $row['USERNAME'] . " <br>\n";
                $msg=$row['USERNAME'];
                $contor=1;
            }
            if($contor==1){
                echo "Thank you for your submit!" ;
                $doc = new DOMDocument();
                $doc->loadHTMLFile("myprofile.html");
                echo $doc->saveHTML();
            }
            else
               echo "Not in database. Please register!";
        }
    ?>
</div> <!-- /container -->
<div class = "container">

    <form class = "form-signin" role = "form"
          action = "<?php echo htmlspecialchars($_SERVER['PHP_SELF']);
            ?>" method = "post">
        <h4 class = "form-signin-heading"><?php echo $msg; ?></h4>
        <input type = "text" class = "form-control"
               name = "email" placeholder = "Email"
               required autofocus><br>
        <input type = "password" class = "form-control"
               name = "password" placeholder = "Password" required>
        <button class = "btn btn-lg btn-primary btn-block" type = "submit"
                name = "login">Login</button>
    </form>

</div>
</div>
</body>
</html>