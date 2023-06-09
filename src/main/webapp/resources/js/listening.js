$(document).ready(function () {
    const X_VALUES = ['-3', '-2', '-1', '0', '1', '2', '3', '4', '5']

    $(".x").click(function (event) {
        event.preventDefault();
        $(".hidden_x").val($(this).val());
        $(".x").css("background-color", "");
        $(this).css("background-color", "#66ff99");
    })

});

function sendToServer(x, y, r) {
    let hiddenX = document.getElementById("hidden_form:graphic_x_hidden");
    let hiddenY = document.getElementById("hidden_form:graphic_y_hidden");
    let hiddenR = document.getElementById("hidden_form:graphic_r_hidden");

    hiddenX.setAttribute("value", x);
    hiddenY.setAttribute("value", y);
    hiddenR.setAttribute("value", r);

    console.log("Отправили на сервер... x =" + x + " y =" + y + " r =" + r)
    document.getElementById('hidden_form:graphic_button_hidden').click();
}
