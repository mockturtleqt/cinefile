$(document).ready(function () {
    var movieRatingValue = Math.round($("#movieRate").text());
    $("input[name=rating][value=" + movieRatingValue + "]").prop('checked', true);

    $(".rating").change(function () {
        if ($("#userId").val() == 0) {
            $("input[name=rating][value=" + movieRatingValue + "]").prop('checked', true);
            alert("You need to be logged in to rate this movie.");
        } else {
            this.form.submit();
        }
    });
});

