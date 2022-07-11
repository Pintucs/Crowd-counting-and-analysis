<?php
error_reporting(0);
  session_start();
  include("db.php");

  $status=$_POST['status'];

  if ($status == 1) {
    $sql="update door_status set status = 1 where id =1";
    $result=mysqli_query($con,$sql);
    
  }elseif ($status == 0) {

    $filePath = "upload/img.jpg";
    
    $sql="update door_status set status = 0 where id =1";
    
    $result=mysqli_query($con,$sql);

    $sql1="delete FROM door_data";

    $result1=mysqli_query($con,$sql1);
    unlink($filePath);
    
  
  }
  

  if ($result && $result1) {
    # code...
    //unlink('http://localhost:8080/home_security_system/upload/1.jpg');


    $output['status']='200';
    $output['message']='SUCESS';
  }else{
    $output['status']='400';
    $output['message']='FAIL';
  }
  

  
	$output2['door_status'] = $output;
  print(json_encode($output2));
  mysqli_close($con);
  
?>