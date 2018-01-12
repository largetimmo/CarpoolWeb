<form action="resetpass.do" method="post">
    <div class=" container">
        <div class="col-sm-6">
            <h1> Hello, <%=request.getSession().getAttribute("USERNAME_TEMP")%></h1>
            <div class="col-sm-12">
                <br>
                <input id = "password" type="password" name="password" class="form-control" autocomplete="off" placeholder="Password(between 6 to 16 characters)">
                <br>
            </div>
            <div class="col-sm-12">
                <input type="submit" class = "center-block btn-primary">
            </div>

        </div>
    </div>
</form>