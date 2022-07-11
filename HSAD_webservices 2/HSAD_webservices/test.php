<?php
$data = $_POST['img'];
$contact = $_POST['contact'];
$data = base64_decode($data);
$con=mysqli_connect("localhost","root","","human_suspicious_activity_detection");
$im = imagecreatefromstring($data);
if ($im !== false) {
    header('Content-Type: image/jpeg');
	$path=genrate_name();
    imagejpeg($im,$path);
    imagedestroy($im);
	$res=mysqli_query($con,"insert into emergency_found(img,date_time) values('".$path."',now())");
	if(!$res)
	{
		echo 'error in query';
		die();
	}
	send_sms("Emergency found at shop.",$contact);
}
else {
    echo 'An error occurred.';
}


function genrate_name(){
         $image_name = md5(uniqid(rand(), true));
         $filename = $image_name . '.' . 'jpeg';
         $path = "upload/".$filename;

        return $path;
    }
	
function send_sms($msg,$mob)
	{
		$url = 'http://sms.cellthis24x7.com/API/WebSMS/Http/v1.0a/index.php';
        $params = array (
				        'username'=>'Bq9841107247',
				        'password'=>'Bq9841',
				        'sender'=>'TSFTSF',
					    'to'=>$mob,
					    'message'=>$msg,
					    'reqid'=>'1',
					    'format'=>'text',
					    'route_id'=>28
					     );
					
		$options = array(
			            CURLOPT_SSL_VERIFYHOST => 0,
			            CURLOPT_SSL_VERIFYPEER => 0
			       		 ); 
			 
		$defaults = array(
			            CURLOPT_URL => $url. (strpos($url, '?') 
			               === FALSE ? '?' : ''). http_build_query($params),
			            CURLOPT_HEADER => 0,
			            CURLOPT_RETURNTRANSFER => TRUE,
			            CURLOPT_TIMEOUT =>56
			       		 );
			 
		$ch = curl_init();
			  curl_setopt_array($ch, ($options + $defaults));
		$result = curl_exec($ch);
		if(!$result)
			{
			    trigger_error(curl_error($ch));
			    $flag=0;
			}
			else
			{                 
			    $flag=1;
			}
			curl_close($ch);
	}	
?>