$(document).ready(function () {

    $(".edit-btn").click(function () {
        var reviewTitle = $("#title-" + this.id);
        var reviewBody = $("#body-" + this.id);

        var editBtn = $("#" + this.id);
        var saveBtn = $("#save-" + this.id);

        reviewTitle.hide();
        reviewBody.hide();

        editBtn.hide();
        saveBtn.show();

        var titleInput = $("<input required name='review-title-input' class='review-title-input'/>");
        var bodyInput = $("<textarea required cols='60' rows='5' name='review-body-input' class='review-body-input'/>");
        var editReviewForm = $("#edit-review-form-" + this.id);

        titleInput.val(reviewTitle.text());
        bodyInput.val(reviewBody.text());

        editReviewForm.append($("<br>"));
        editReviewForm.append(titleInput);
        editReviewForm.append(bodyInput);

    });

    $(".delete-btn").click(function(e){
        e.preventDefault();
        if (confirm("Are you sure you want to delete your review?")){
            $("form.delete-review-form").submit();
        }
    });

    $(".delete-rating-btn").click(function(e){
        e.preventDefault();
        if (confirm("Are you sure you want to delete your rating?")){
            $("form.delete-rating-form").submit();
        }
    });
});
