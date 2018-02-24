<form action="/fore_resetpass" method="post">
    <div class=" container">
        <div class="col-sm-6">
            <h1> Hello,${USERNAME_TEMP}</h1>

            <div class="col-sm-12">
                <label>Username:</label>
                <input name="username" contenteditable="false" value="${USERNAME_TEMP}" class="form-control">
                <br>
                <input id = "password" type="password" name="password" class="form-control" autocomplete="off" placeholder="Password(between 6 to 16 characters)">
                <br>
            </div>
            <div class="col-sm-12">
                <input type="submit" class = "center-block btn btn-primary">
            </div>

        </div>
    </div>
</form>