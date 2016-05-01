<?php
		require 'connect.php';
	
		session_start(); // Starting Session
		$error=''; // Variable To Store Error Message
		error_reporting(E_ERROR | E_PARSE);
		
		if (isset($_POST['submit'])) {
		if (empty($_POST['email']) || empty($_POST['name']) ) {
				 echo "Username or Password is invalid";
			}else			
				{
					echo "yeah";
					$email = $_POST['email'];
					$username = $_POST['name'];
					$query = "SELECT email FROM users WHERE username = '$username'";					
					$result = mysqli_query($con, $query);
					$count = mysqli_num_rows($result);
					echo lol;
					if(mysqli_query($con,$query))
							{	$result = mysqli_query($con, $query);
								if($count ==1)
								{
						           while($row = mysqli_fetch_array($result)) {
										$dbemail = $row['email'];
								   }
								}
						if($email == $dbemail)
						{
							$pass = rand();							
							$password = md5($pass);							
							$sql = "UPDATE users SET password = '$password' WHERE username = '$username'";
							mysqli_query($con, $sql);
							sendEmail();
							$msg ="Амжилттай солигдлоо";
							echo "<script language="javascript">alert('$msg')</script>";
					
						}
								header("location: index.html");
					}else
					{
						echo "Error";
					}				
					mysqli_close($con);
				}
		}
				else "lol";
		
		function sendEmail(){
			$msg = "Your new password = " + $pass;		
			$subject = "";		
			$headers = "From:Student Career";
			mail($email,$subject,$msg,$headers);		
		}

?>
 