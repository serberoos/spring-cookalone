$(document).ready(function () {
    $("#cbx_terms_chkAll").click(function () {
        if ($("#cbx_terms_chkAll").is(":checked")) $("input[name=terms_chk]").prop("checked", true);
        else $("input[name=terms_chk]").prop("checked", false);
    });

    $("input[name=terms_chk]").click(function () {
        var total = $("input[name=terms_chk]").length;
        var checked = $("input[name=terms_chk]:checked").length;

        if (total != checked) $("#cbx_terms_chkAll").prop("checked", false);
        else $("#cbx_terms_chkAll").prop("checked", true);
    });
});