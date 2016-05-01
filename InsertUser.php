<?php
	require 'connect.php';
	$name = $_POST['name'];
	$password = $_POST['password'];
	$questionNumber = $_POST['number'];
	$answer = $_POST['answer'];
	$passwordSecure=md5($password);
	$sql="INSERT INTO `User`(`Username`, `Password`, `QuestionNumber`, `Answer`) VALUES('$name','$passwordSecure',$questionNumber,'$answer')";
	if(mysqli_query($con,$sql)){
		echo "Data inserted";
	}else{
		echo "Data not inserted , Try Again";
	}
	mysqli_close($con);
?>