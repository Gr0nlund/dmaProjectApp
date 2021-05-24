<?php
    
    $dbServername = 'web1.netgiganten.dk';
    $dbUsername = 'damibfko_josj';
    $dbPassword = 'Glubben13!';
    $dbName = 'damibfko_DMA';
  
    // Create connection
    $conn = new mysqli($dbServername, $dbUsername, $dbPassword, $dbName); 
    
    $User = $_GET['UserID'];
    $Dumpster_ID = $_GET['DumpsterID'];
    $Dumpster_Type = $_GET['DumpsterType'];

    if ($Dumpster_Type == "Pap "){
        $Dumpster_Type = "Pap & Papir";
    } else if ($Dumpster_Type == "Plast "){
        $Dumpster_Type = "Plast & Metal";
    }

    $Date = date("d-m-Y");
    
    $Response = 102;
    $RequestURL = "https://solskov-jensen.dk/API/clear.api.php";
    $DateTime = date("d-m-Y H:i:s");
    
    //https://www.solskov-jensen.dk/API/clear.api.php?User=1342&DumpsterID=1&DumpsterType=Rest
    if (mysqli_connect_errno()) {
        die('MySQL connection failed: ' . mysqli_connect_error());
    }

    if(isset($User) && isset($Dumpster_ID) && isset($Dumpster_Type)){

        $request = $conn->prepare('INSERT INTO DMA_Request (UserID, RequestURL,RequestTime, RequestResponse) VALUES (?, ?, ?, ?)');
        $request->bind_param('ssss', $User, $RequestURL, $DateTime, $Response);
        $request->execute();
        $request->close();

        $check = $conn->prepare('SELECT * FROM DMA_User_Reports WHERE Report_Date="' . $Date . '" AND Dumpster_ID = ? AND Dumpster_Type = ? AND User_ID = ?');
        $check->bind_param('sss', $Dumpster_ID, $Dumpster_Type, $User);
        $check->execute();
        $check->store_result();
        $rows = $check->num_rows;

        //Skal hedde $rows > 1
        if(true){
            $check->close();
            $insert = $conn->prepare('INSERT INTO DMA_User_Reports (User_ID, Dumpster_ID, Dumpster_Type, Report_Date) VALUES (?, ?, ?,"' . $Date . '")');
            $insert->bind_param('sss', $User, $Dumpster_ID, $Dumpster_Type);
            $insert->execute();
            $insert->close();
        
            $result = $conn->prepare('SELECT * FROM DMA_User_Reports WHERE Report_Date="' . $Date . '" AND Dumpster_ID = ? AND Dumpster_Type = ?');
            $result->bind_param('ss', $Dumpster_ID, $Dumpster_Type);
            $result->execute();
            $result->store_result();
            $row = $result->num_rows;
            $Response = 400;
            //The 3 limiter to be changed in final release
            if (true){
                $update = $conn->prepare('UPDATE DMA_Dumpsters_Types SET Dumpster_Full = 0 WHERE Dumpster_ID = ? AND Dumpster_Type = ?');
                $update->bind_param('ss', $Dumpster_ID, $Dumpster_Type);
                $update->execute();
                $update->close();
                $Response = 200;
            }
            $result->close();
        } else {
            $Response = 418;
        }
        echo "Rows= " . $rows . " and row = " . $row;
    }
    
    $update = $conn->prepare('UPDATE DMA_Request SET RequestResponse = ? WHERE UserID = ? AND RequestTime = ?');
    $update->bind_param('sss',$Response, $User, $DateTime);
    $update->execute();
    $update->close();
    
    $conn->close();
    http_response_code($Response);
?>