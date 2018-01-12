<div id="register" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Register</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <input id="reg_username" class="username form-control" placeholder="Username (8 to 30 characters)"
                           autocomplete="off" spellcheck="off">
                    <br>
                    <input id="reg_password" class="password form-control" type="password"
                           placeholder="Password (6 to 16 characters)" autocomplete="off" spellcheck="off">
                    <br>
                    <input id="reg_email" class="email form-control" placeholder="Email" autocomplete="off"
                           spellcheck="off">
                    <br>
                    <input id="reg_nickname" class="form-control" placeholder="Nickname (8 to 30 characters)"
                           autocomplete="off" spellcheck="off">
                    <br>
                    <input id="reg_cell" class="form-control" placeholder="Cell Phone Number" autocomplete="off"
                           spellcheck="off">
                    <label id="return_message" class="label-danger" style="display: none"></label>
                </div>
            </div>

            <div class="modal-footer">
                <button id="submit" class="btn-primary btn">Register</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>