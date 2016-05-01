<?php
		require 'connect.php';
		
		session_start(); // Starting Session
		$error=''; // Variable To Store Error Message
		error_reporting(E_ERROR | E_PARSE);
		if (isset($_POST['submit'])) {
			if (empty($_POST['name']) || empty($_POST['password'])) {
				$error = "Username or Password is invalid";
			}else
				{
					
					$username = $_POST['name'];
					$password = $_POST['password'];	
			
					$password = md5($password);					
					$sql = "SELECT * FROM users WHERE Username = '$username' and Password = '$password'";											
					$result = mysqli_query($con,$sql);
					$count = mysqli_num_rows($result);
					if($count ==1)
					{
						$_SESSION['login_user']=$username; // Initializing Session
						header("location: mainPage.php");
					}else
					{
						$error = "Username or Password is invalid";
					}
					mysqli_close($con);
				}
		}

?>
 