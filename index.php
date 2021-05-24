<?php
    include "components/head.php";
    include "components/header.php";
?>
        <main class="main">
        <!-- http://dmst.dk/admin
        Her bruger du brugernavnet; 
        admin med koden: 
        fcL8rxRIbk^ATbTzn6 -->
            
            <section class="login">
                <div class="row">
                    <div class="login__form u-margin-bottom-medium u-margin-top-medium">
                        <form class="form" action="includes/opretSite.inc.php" method="post">
                            <div class="login-box">
                                <div class="u-margin-bottom-medium u-center-text">
                                    <h1 class="heading-secondary">
                                        Tilføj affalds site
                                    </h1>
                                </div>
                                <div class="login__group">
                                    <input type="text" name="longitude" class="login__input" placeholder="Longitude" id="longitude" required>
                                    <label for="longitude" class="login__label">Longitude</label>
                                </div>
                                <div class="login__group">
                                    <input type="text" name="latitude" class="login__input" placeholder="Latitude" id="latitude" required>
                                    <label for="latitude" class="login__label">Latitude</label>
                                </div>
                            </div>
                            <div class="login__group">
                                <input value="Opret" type="submit" name="login_submit" class="login__submit">
                            </div>
                            <a href="type.php">Opret typer</a>
                        </form>
                    </div>
                </div>
            </section>
        </main>

        <footer class="footer">

        </footer>


    </body>
</html>