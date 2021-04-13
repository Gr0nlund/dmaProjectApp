<?php
    $dbServername = 'web1.netgiganten.dk';
    $dbUsername = 'damibfko_josj';
    $dbPassword = 'Glubben13!';
    $dbName = 'damibfko_DMA';
  
    // Create connection
    $conn = new mysqli($dbServername, $dbUsername, $dbPassword, $dbName); 

    $DumpsterType = $_GET['DumpsterType'];

    if (mysqli_connect_errno()) {
        die('MySQL connection failed: ' . mysqli_connect_error());
    }
  
    $stmt = $conn->prepare('SELECT * FROM DMA_Dumpsters_Types WHERE Dumpster_ID = ?');
    $stmt->bind_param('s', $DumpsterType);
    $stmt->execute();
    $stmt->bind_result($Dumpster_ID, $Dumpster_Type, $Dumpster_Full);
    $Dumpsters = array();
    
    while ($stmt->fetch()){
        $temp = array();
        $temp['Dumpster_ID'] = $Dumpster_ID;
        $temp['Dumpster_Type'] = $Dumpster_Type;
        $temp['Dumpster_Full'] = $Dumpster_Full;

        array_push($Dumpsters, $temp);
    }
    echo json_encode($Dumpsters);
?>