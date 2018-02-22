<div id="content" class="container jumbotron">
    <h1 id="heading1" class="navtext">Title</h1>
    <p id="description" class="navtext">~~~~~~~Description~~~~~~~~</p>
    <div class="col-sm-3">
        <input id="from" type="text" class="form-control" placeholder="Departure Eg.Ottawa" autocomplete="off" spellcheck="off">
    </div>
    <div class="col-sm-3">
        <input id="to" type="text" class="form-control" placeholder="Destination Eg.Toronto" autocomplete="off" spellcheck="off">
    </div>
    <div class="col-sm-2">
        <input id="passenger" class="form-control" placeholder="Passenger(s)" autocomplete="off" spellcheck="off">
    </div>
    <div class="col-sm-3">
        <div class="container">
            <div class="row">
                <div class="input-group date" id="datepicker">
                    <input type="text" class="form-control" id="date" placeholder="Departure date"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function () {
                $('#datepicker').datetimepicker({
                    format: 'YYYY-MM-DD'
                });
            })
        </script>
    </div>
    <button id="send" class="btn btn-primary">Submit</button>
</div>
