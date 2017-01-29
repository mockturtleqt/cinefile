$(document).ready(function () {
    var rating = $("input[name=rating]");
    var movieRatingValue = $("#movieRatingP").text();
    rating.val(Math.round(movieRatingValue));

    $(".rating").change(function () {
        if ($("#userId").val() == 0) {
            alert("You need to be logged in to rate this movie.");
        } else {
            this.form.submit();
        }
    });
});

