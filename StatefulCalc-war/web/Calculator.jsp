<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <title>Stateful Calculator</title>
    </head>
    <style>
        .kalkulator{
            border: 1px solid #9f6d4c;
            display: inline-block;
            margin-bottom: 30px;
            position: absolute;
            top: 37.5%;
            left: 50%;
            transform: translate(-50%,-30%);
            width: 400px;
            padding: 40px;
            background: url(image/kalku.jpg);
            box-sizing: border-box;
            box-shadow: 0 15px 25px rgba(0,0,0,.5);
            border-radius: 10px;
        }
        .kalkulator input .operation{
            background-color: transparent;
            border-color: black;
            padding: 5px 3px;
            color:#ff0000; 
        }
        
    </style>
    <body background = "image/bg2.png">
    <div>
        <center>
          <h1 style="color:black;">Stateful Calculator</h1>
          <h5 style="color:black;">Riwayat:</h5>
        <p style="color:black;">${history}</p>
        <h1 style="color:black;">${results}</h1>
        <form action="Calculator" class="kalkulator" method="post">
          <aside>
            <span style="color:white;">${operation}</span>
            <input class="hasil" type="text" style="width: 90%" name="nilai" value="${value}" disabled><br><br>
          </aside>
          <br>
          <table>
              <tr>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="tujuh" value="7"></td>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="delapan" value="8"></td>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="sembilan" value="9"> </td>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="div" value="/"><br> </td>
                  <td><h6 style="color:rgba(52, 235, 210,.0);">-------</h6></td>
                  <td> <input class="operation" style="width: 75px; height: 40px;" type="submit" name="delete" value="Del"> </td>
              </tr>
              <tr>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="empat" value="4"> </td>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="lima" value="5"> </td>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="enam" value="6"> </td>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="mul" value="x"><br> </td>
                  <td><h6 style="color:rgba(52, 235, 210,.0);">-------</h6></td>
                  <td> <input class="operation" style="width: 75px; height: 40px;" type="submit" name="reset" value="Clr"> </td>
              </tr>
              <tr>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="satu" value="1"> </td>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="dua" value="2"> </td>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="tiga" value="3"> </td>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="sub" value="-"><br> </td>
                  <td><h6 style="color:rgba(52, 235, 210,.0);">-------</h6></td>
                  <td> <input class="operation" style="width: 75px; height: 40px;" type="submit" name="equ" value="="> </td>
              </tr>
              <tr>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="nolnol" value="00"></td>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="nol" value="0"> </td>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="dot" value="."> </td>
                  <td> <input class="operation" style="width: 40px; height: 40px;" type="submit" name="add" value="+"> </td>
                  </tr>
            </table>
        </form>
          </center>
      </div>
    </body>
</html>