<?php
error_reporting(0);
  session_start();
  include("db.php");
  $uname=$_POST['uid'];
  $password=$_POST['password'];
  $sql="select * from users where contact='".$uname."' and password='".$password."' and status =1";
  $result=mysqli_query($con,$sql);
  while($row=mysqli_fetch_assoc($result))
  $output[]=$row;
	$output2['login'] = $output;
  print(json_encode($output2));
  mysqli_close($con);
  
?>