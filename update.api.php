<?php

    $dbServername = 'web1.netgiganten.dk';
    $dbUsername = 'damibfko_josj';
    $dbPassword = 'Glubben13!';
    $dbName = 'damibfko_DMA';
  
    // Create connection
    $conn = new mysqli($dbServername, $dbUsername, $dbPassword, $dbName); 
    
    // $User = $_GET['User'];
    // $Dumpster_ID = $_GET['DumpsterID'];
    // $Dumpster_Type = $_GET['DumpsterType'];
    $User = $_GET['User'];
    $Dumpster_ID = $_GET['DumpsterID'];
    $Dumpster_Type = $_GET['DumpsterType'];
    $Date = date("d-m-Y");
    if(isset($User) && isset($Dumpster_ID) && isset($Dumpster_Type)){
        if (mysqli_connect_errno()) {
            die('MySQL connection failed: ' . mysqli_connect_error());
        }
        $check = $conn->prepare('SELECT * FROM DMA_User_Reports WHERE Report_Date="' . $Date . '" AND Dumpster_ID = ? AND Dumpster_Type = ? AND User_ID = ?');
        $check->bind_param('sss', $Dumpster_ID, $Dumpster_Type, $User);
        $check->execute();
        $rows = $check->num_rows;
        
        if($rows < 1){
            $check->close();
            $insert = $conn->prepare('INSERT INTO DMA_User_Reports (User_ID, Dumpster_ID, Dumpster_Type, Report_Date) VALUES (?, ?, ?,"' . $Date . '")');
            $insert->bind_param('sss', $User, $Dumpster_ID, $Dumpster_Type);
            $insert->execute();
            $insert->close();
        
            $result = $conn->prepare('SELECT * FROM DMA_User_Reports WHERE Report_Date="' . $Date . '" AND Dumpster_ID = ? AND Dumpster_Type = ?');
            $result->bind_param('ss', $Dumpster_ID, $Dumpster_Type);
            $result->execute();
            $row = $result->num_rows;
            
            if ($row >= 3){
                $update = $conn->prepare('UPDATE DMA_Dumpsters_Types SET Dumpster_Full = 1 WHERE Dumpster_ID = ' . $Dumpster_ID . ' AND Dumpster_Type = ' . $Dumpster_Type);
                $update->execute();
                $update->close();
            }
            $result->close();
        } else {
            $check->close();
        }

        $conn->close();

    } else {
        header("location: update.api.php?error=emptyget");
    }
?>