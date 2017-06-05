<!DOCTYPE html>
<html>
<head>
    <title>Admin page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand navbar-link" href="home.html">
                <img id="logo" src="logo1.png">
            </a>
        </div>
        <div id="navcol-1" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
               <li role="presentation">
                    <a href="about%20us.html">
                        <i class="fa  fa-sign-out"> <strong>Logout</strong> </i> </a>
                </li>
            </ul>
    </div>
        </div>
        </div>
</nav>
           <div style="padding-top:50px;"> </div>
        <div class="container">
          <div class="span3 well">
            <center>
            <a href="#aboutModal" data-toggle="modal" data-target="#myModal"><img src="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRbezqZpEuwGSvitKy3wrwnth5kysKdRqBW54cAszm_wiutku3R" name="aboutme" width="140" height="140" class="img-circle"></a>
            <h3>Admin</h3>
            </center>
          </div>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title" id="myModalLabel">Info</h4>
                        </div>
                    <div class="modal-body">
                        <center>
                        <img src="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRbezqZpEuwGSvitKy3wrwnth5kysKdRqBW54cAszm_wiutku3R" name="aboutme" width="140" height="140" border="0" class="img-circle"></a>
                        <h3 class="media-heading">Admin </h3>
                        </center>
                        <hr>
                        <center>
                        <p class="text-center"><i class="fa fa-home" aria-hidden="true"></i><strong> City: </strong><br>
              <i class="fa fa-birthday-cake" aria-hidden="true"></i><strong> Birthdate: </strong><br>
              <i class="fa fa-id-card-o" aria-hidden="true"></i><strong> Age: </strong><br>
              <i class="fa fa-mobile" aria-hidden="true"></i><strong> Phone: </strong><br>
              <i class="fa fa-user-circle" aria-hidden="true"></i><strong> Where you can find me: </strong><br>
              <i class="fa fa-sticky-note" aria-hidden="true"></i><strong> More about me: </strong>
                            </p>
                        <br>
                        </center>
                    </div>
                    <div class="modal-footer">
                        <center>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Add</button>
                        </center>
                    </div>
                </div>
            </div>
        </div>
       </div>



  <div class = "container form-signin">
      <?php
         $username = 'FIIMEM';
         $password = 'FIIMEM';
         $connection_string = 'localhost/xe';
         $conn = oci_connect($username, $password, $connection_string);
         // Parse the statement. Note there is no final semi-colon in the SQL statement
         $item = '';
         $pass = '';
         $msg = '';
         if(isset($_POST['create'])){
          $stid = oci_parse($conn, "create materialized view poze1 for update as select * from files where format in('.jpg','.gif','.tiff','.tga','.psd','.png','.bmp','.wmf')");
          oci_execute($stid);
          oci_commit($conn);  
          $msg = 'Tabela facuta';
         }
         else {
          if(isset($_POST['read'])){
            $stid = oci_parse($conn, "SELECT fid,membersmid,address,name,format FROM poze1 ");
            oci_execute($stid);  
            /*$nrows = oci_fetch_all($stid, $res);
            //echo "$nrows rows fetched<br>\n";
            foreach ($res as $key => $value) {
            // $arr[3] will be updated with each value from $arr...
              
              print_r($res["MID"]);
            }
          */
            echo "<table border='1'>\n";
           while ($row = oci_fetch_array($stid, OCI_ASSOC+OCI_RETURN_NULLS)) {
          echo "<tr>\n";
          foreach ($row as $item) {
          echo "    <td>" . ($item !== null ? htmlentities($item, ENT_QUOTES) : "&nbsp;") . "</td>\n";
          }
          echo "</tr>\n";
          }
      echo "</table>\n";
         }
         
         else if(isset($_POST['update'])){
       $name = $_POST['name'];
       $id = $_POST['id'];
          $stid = oci_parse($conn, " Update  set name = :name where fid = :id");
      oci_bind_by_name($stid,':name', $name);
      oci_bind_by_name($stid,':id', $id);
          oci_execute($stid);
          oci_commit($conn);
          $msg = 'yuppi';
      //echo $name;
      //echo $id;

         }
         else if(isset($_POST['delete'])){
       echo 'am ajuns aici'; 
       echo $_POST['id'];
       $id = $_POST['id'];
          $stid = oci_parse($conn, " Delete from poze1 where fid = :id");
      
      oci_bind_by_name($stid,':id', $id);
          oci_execute($stid);
          oci_commit($conn);
          $msg = 'yuppi';
     }
     else if(isset($_POST['insert'])){
       $tname = $_POST['tname'];
       $name = $_POST['name'];
       $id = $_POST['id'];
          $stid = oci_parse($conn, " Insert into :tname(id, name) values(:id, :name)");
      oci_bind_by_name($stid,':tname', $tname);
      oci_bind_by_name($stid,':name', $name);
      oci_bind_by_name($stid,':id', $id);
          oci_execute($stid);
          oci_commit($conn);
          $msg = 'yey';
        }
     }
      ?>
      </div> <!-- /container -->
   <div class = "container">
       <center>
       <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myCreate">Create table</button>
       <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myRead">Read table</button>
       <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myDrop">Drop table</button>
       <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myInsert">Insert record</button>
       <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myUpdate">Update record</button>
       <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myDelete">Delete record</button>
      </center>
            
           
            

     <div id="myCreate" class="modal fade" role="dialog">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Create Table</h4>
            </div>
            <div class="modal-body">
              
                <button class = "btn btn-lg btn-primary btn-block" type = "submit" 
               name = "create">Create </button>
              
            </div>
            
          </div>

        </div>
    </div>

    <div id="myRead" class="modal fade" role="dialog">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Read Table</h4>
            </div>
            <div class="modal-body">
              
                <button class = "btn btn-lg btn-primary btn-block" type = "submit" 
               name = "read">Read </button>
              
            </div>
            
          </div>

        </div>
    </div>

    <div id="myDrop" class="modal fade" role="dialog">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Drop Table</h4>
            </div>
            <div class="modal-body">
              
                <button class = "btn btn-lg btn-primary btn-block" type = "submit" 
               name = "drop">Drop </button>
              
            </div>
            
          </div>

        </div>
    </div>

    <div id="myInsert" class="modal fade" role="dialog">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Insert Record</h4>
            </div>
            <div class="modal-body">
              
                <form class = "form-signin" role = "form" 
            action = "<?php echo htmlspecialchars($_SERVER['PHP_SELF']); 
            ?>" method = "post">
        <div >
        <label><b>Table Name</b></label>
        <input type="text" placeholder="Enter Table Name" name="tname" required>
        <label><b>Name</b></label>
        <input type="text" placeholder="Enter Name" name="name" required>
        <label><b>Id</b></label>
        <input type="text" placeholder="Enter Id" name="id" required>
        <button type="submit" name = "insert">Insert</button>
         </div>
         </form>     
            </div>
            
          </div>

        </div>
    </div>

    <div id="myUpdate" class="modal fade" role="dialog">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Update Record</h4>
            </div>
            <div class="modal-body">
              
                <form class = "form-signin" role = "form" 
            action = "<?php echo htmlspecialchars($_SERVER['PHP_SELF']); 
            ?>" method = "post">
        <div class="container">
        <label><b>Name</b></label>
        <input type="text" placeholder="Enter Name" name="name" required>
        <label><b>Id</b></label>
        <input type="text" placeholder="Enter Id" name="id" required>
        <button type="submit" name = "update">Update</button>
        </div>
      </form>
              
            </div>
            
          </div>

        </div>
    </div>

    <div id="myDelete" class="modal fade" role="dialog">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Delete Record</h4>
            </div>
            <div class="modal-body">
              
                 <form class = "form-signin" role = "form" 
            action = "<?php echo htmlspecialchars($_SERVER['PHP_SELF']); 
            ?>" method = "post">
        <div class="container">
        <label><b>Id</b></label>
        <input type="text" placeholder="Enter Id" name="id" required>
        <button type="submit" name = "delete">Delete</button>
        </div>
      </form>
              
            </div>
            
          </div>

        </div>
    </div>


    <h4 class = "form-signin-heading"><?php echo $msg; ?></h4>
         
         Click here to clean <a href = "test2.php" tite = "Logout">Session.
         </a>
     </div>

      
       
</body>
</html>