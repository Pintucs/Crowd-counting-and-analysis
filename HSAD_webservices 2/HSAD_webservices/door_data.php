<?php
error_reporting(0);
  session_start();
  include("db.php");
  $sql="select * from emergency_found order by date_time desc";
  $result=mysqli_query($con,$sql);
  while($row=mysqli_fetch_assoc($result))
  $output[]=$row;
  $output2['door_data'] = $output;
  print(json_encode($output2));
  mysqli_close($con);
  ?>