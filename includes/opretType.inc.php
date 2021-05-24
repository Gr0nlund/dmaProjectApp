<?php
    
    $dbServername = 'web1.netgiganten.dk';
    $dbUsername = 'damibfko_josj';
    $dbPassword = 'Glubben13!';
    $dbName = 'damibfko_DMA';
  
    // Create connection
    $conn = new mysqli($dbServername, $dbUsername, $dbPassword, $dbName); 
    
    $Dumpster_ID = $_POST['ID'];
    $Dumpster_Type = $_POST['type'];
    
    //https://www.solskov-jensen.dk/API/update.api.php?User=1342&DumpsterID=1&DumpsterType=Rest
    if (mysqli_connect_errno()) {
        die('MySQL connection failed: ' . mysqli_connect_error());
    }

    if(isset($Dumpster_ID) && isset($Dumpster_Type)){

        $request = $conn->prepare('INSERT INTO DMA_Dumpsters_Types VALUES (?, ?, 0)');
        $request->bind_param('ss', $Dumpster_Type, $Dumpster_ID);
        $request->execute();
        $request->close();

        $conn->close();
        header ("Location: ../type.php?state=success");
    }
    
    $conn->close();
    header ("Location: ../type.php");
?>