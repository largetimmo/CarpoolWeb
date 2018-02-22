<div id="register" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Register</h4>
            </div>
            <form action="/fore_register" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <input id="reg_username" class="username form-control" placeholder="Username (8 to 30 characters)"
                               autocomplete="off" spellcheck="off">
                        <br>
                        <input id="reg_password" class="password form-control" type="password" name="password"
                               placeholder="Password (6 to 16 characters)" autocomplete="off" spellcheck="off">
                        <br>
                        <input id="reg_email" class="email form-control" placeholder="Email" autocomplete="off"
                               name="email" spellcheck="off">
                        <br>
                        <input id="reg_nickname" class="form-control" placeholder="Nickname (8 to 30 characters)"
                               name="nickname" autocomplete="off" spellcheck="off">
                        <br>
                        <input id="reg_cell" class="form-control" placeholder="Cell Phone Number" autocomplete="off"
                               name="cell" spellcheck="off">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="submit" class="btn-primary btn">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>
    </div>
</div>