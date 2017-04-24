<!DOCTYPE html>
<html lang="en">
<?php
require_once('connection.php');
$id=$_SESSION['SESS_MEMBER_ID'];
$result3 = mysql_query("SELECT username FROM members where email='$email' and password='$password");
while($row3 = mysql_fetch_array($result3))
{
$username=$row3['username'];
}
?>
<head>
    <title>My profile</title>
    <link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body background="fiimem3.jpg">
<table width="398" border="0" align="center" cellpadding="0">
    <tr>
        <td height="26" colspan="2">Your Profile Information </td>
        <td><div align="right"><a href="logout.php">logout</a></div></td>
    </tr>
    <tr>
        <td width="90" valign="right"><div align="left">Username:</div></td>
        <td width="165" valign="right"><?php echo $username ?></td>
        <td width="129" rowspan="5"><img src="?php echo $picture ?>" width="250" height="250" alt="no image found"/></td>

    </tr>

</table>
<p align="right"><a href="logout.php"></a></p>
<div id="header">
    <div id="menu">
        <ul>
            <li class="active"><a href="myprofile.html">My profile</a></li>
            <li><a href="photos.html">Photos</a></li>
            <li><a href="videos.html">Videos</a></li>
            <li><a href="gentree.html">Gentree</a></li>
        </ul>
    </div>
</div>
</body>
</html>