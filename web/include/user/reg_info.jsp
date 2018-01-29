<div class="container">
    <form action="/user_user_update" method="post">
        <div class="form-group">
            <label for="emailaddr">Email:</label>
            <input type="email" name ="email" id="emailaddr" class="form-control" value=${userinfo.email}>
        </div>
        <div class="form-group">
            <label for="cellnum">Cell:</label>
            <input id="cellnum" name="cell" type="number" class="form-control" value="${userinfo.cell}">
        </div>
        <div class="form-group">
            <label for="nickname">Nickname:</label>
            <input id="nickname" name = "nickname" class="form-control" type="text" value="${userinfo.nickname}">
        </div>
        <input type="submit" class="btn btn-primary">
    </form>

</div>