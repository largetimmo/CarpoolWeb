<div class="login" style="margin-top: 3em">
    <div class="container">
        <form class="col-sm-6 loginform form" action="/fore_login" method="post">
            <h2 class="logintitle">Log in</h2>
            <div class="col-sm-10">
                <input class="form-control" type="text" name="username" id="username" placeholder="Username:" autocomplete="off">
            </div>
            <div class="col-sm-2">
                <a data-toggle="modal" data-target="#register">Register</a>
            </div>
            <div class="col-sm-10">
                <br>
                <input class="form-control" type="password" name = "password" id="password" placeholder="Password:" autocomplete="off">
            </div>
            <div class="col-sm-2">
                <br>
                <a href="/forgetpass.jsp">Forget Password?</a>
            </div>
            <div class="col-sm-12">
                <br>
                <button id="login" class="btn btn-primary center-block">Login</button>
                <br>
            </div>
        </form>
    </div>
</div>